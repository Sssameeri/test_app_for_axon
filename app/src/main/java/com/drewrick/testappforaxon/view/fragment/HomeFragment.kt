package com.drewrick.testappforaxon.view.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.WorkInfo
import com.drewrick.testappforaxon.R
import com.drewrick.testappforaxon.databinding.FragmentHomeBinding
import com.drewrick.testappforaxon.model.models.Person
import com.drewrick.testappforaxon.model.network.NetworkState
import com.drewrick.testappforaxon.utils.Constants
import com.drewrick.testappforaxon.view.adapter.PersonAdapter
import com.drewrick.testappforaxon.viewmodel.PersonsViewModel

class HomeFragment : Fragment(), PersonAdapter.OnItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    private lateinit var personViewModel: PersonsViewModel
    private lateinit var personsAdapter: PersonAdapter
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        personViewModel = ViewModelProvider(requireActivity()).get(PersonsViewModel::class.java)
        personsAdapter = PersonAdapter(this)
        sharedPreferences = requireActivity().getSharedPreferences(
            Constants.SHARED_PREF_FILE_NAME,
            Context.MODE_PRIVATE
        )
        navController = findNavController()

        inflateAdapter()
    }

    private fun inflateAdapter() {
        binding.recyclerViewPersons.layoutManager = setLayoutType(checkLayoutType())

        personViewModel.personsList.observe(viewLifecycleOwner, {
            personsAdapter.submitList(it)
        })

        personViewModel.networkState.observe(viewLifecycleOwner, {
            if (binding.swipeToRefreshLayout.isRefreshing)
                binding.swipeToRefreshLayout.isRefreshing = it == NetworkState.LOADING

            personsAdapter.setNetworkState(it)
        })


        binding.recyclerViewPersons.adapter = personsAdapter

        binding.swipeToRefreshLayout.setOnRefreshListener {
            personViewModel.invalidateDataSource()
        }
    }

    private fun checkLayoutType() = if (sharedPreferences.getInt(
            Constants.LAYOUT_TYPE,
            Constants.LINEAR_TYPE
        ) == Constants.LINEAR_TYPE
    ) {
        Constants.LINEAR_TYPE
    } else {
        Constants.GRID_TYPE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_bar_manu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.change_list_view) {
            if (sharedPreferences.getInt(
                    Constants.LAYOUT_TYPE,
                    Constants.LINEAR_TYPE
                ) == Constants.LINEAR_TYPE
            ) {
                binding.recyclerViewPersons.layoutManager = GridLayoutManager(requireActivity(), 2)
                editLayoutType(Constants.GRID_TYPE)
            } else {
                binding.recyclerViewPersons.layoutManager = LinearLayoutManager(requireActivity())
                editLayoutType(Constants.LINEAR_TYPE)
            }

            return true
        }

        return false
    }

    private fun setLayoutType(type: Int) = when (type) {
        Constants.LINEAR_TYPE -> {
            LinearLayoutManager(requireActivity())
        }
        else -> GridLayoutManager(requireActivity(), 2)
    }

    private fun editLayoutType(type: Int) {
        with(sharedPreferences.edit()) {
            putInt(Constants.LAYOUT_TYPE, type)
            apply()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(person: Person?) {
        person?.let {
            personViewModel.selectedPerson(it)
            navController.navigate(R.id.action_homeFragment_to_detailsFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment()
    }
}