package com.drewrick.testappforaxon.view.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.drewrick.testappforaxon.databinding.FragmentDetailsBinding
import com.drewrick.testappforaxon.viewmodel.PersonsViewModel

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var personViewModel: PersonsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        personViewModel = ViewModelProvider(requireActivity()).get(PersonsViewModel::class.java)

        personViewModel.selectedPerson.observe(viewLifecycleOwner, { person ->
            binding.person = person
        })

        binding.textLayoutPersonPhone.editText?.setOnClickListener {
            callUser(binding.textLayoutPersonPhone.editText?.text.toString())
        }

        binding.textLayutPersonCellPhone.editText?.setOnClickListener {
            callUser(binding.textLayutPersonCellPhone.editText?.text.toString())
        }

        binding.textLayoutPersonEmail.editText?.setOnClickListener {
            sendEmail(binding.textLayoutPersonEmail.editText?.text.toString())
        }
    }

    private fun callUser(phone: String) {
        val callUserIntent = Intent(Intent.ACTION_CALL).apply {
            data = Uri.parse("tel: $phone")
        }
        startActivity(callUserIntent)
    }

    private fun sendEmail(email: String) {
        val sendEmailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        }
        startActivity(sendEmailIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DetailsFragment()
    }
}