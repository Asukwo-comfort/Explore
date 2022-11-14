package com.kulex.explorer.view.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.switchmaterial.SwitchMaterial
import com.kulex.explorecountries.R
import com.kulex.explorecountries.databinding.FragmentCountryListBinding
import com.kulex.explorer.R
import com.kulex.explorer.adapter.CountriesAdapter
import com.kulex.explorer.databinding.FragmentCountryListBinding
import com.kulex.explorer.util.LoadingState
import com.kulex.explorer.viewmodel.CountriesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*


@AndroidEntryPoint
class CountryListFragment : Fragment(R.layout.fragment_country_list) {
    private lateinit var themeSwitchMaterial: SwitchMaterial
    private var _binding: FragmentCountryListBinding? = null

    private lateinit var countriesAdapter: CountriesAdapter
    private val binding get() = _binding!!

    private val viewModel: CountriesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        /*	themeSwitchMaterial = binding.switch1
            themeSwitchMaterial.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }*/

        //handling light/dark mode
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){

        }


        getCountries()
        searchCountries()
        showAlertDialog()
    }

    private fun showAlertDialog() {

        // initialise the list items for the alert dialog
        val listItems = arrayOf("Africa", "Americas", "Asia", "Europe", "Oceania")
        val checkedItems = BooleanArray(listItems.size)

        // copy the items from the main list to the selected item list for the preview
        // if the item is checked then only the item should be displayed for the user
        val selectedItems = mutableListOf(*listItems)

        binding.btnFilter.setOnClickListener{
            val builder = AlertDialog.Builder(requireContext())

            // set the title for the alert dialog
            builder.setTitle("Choose Region")

            // set the icon for the alert dialog
            builder.setIcon(R.drawable.ic_baseline_filter_alt_24)

            // now this is the function which sets the alert dialog for multiple item selection ready
            builder.setMultiChoiceItems(listItems, checkedItems) { dialog, which, isChecked ->
                checkedItems[which] = isChecked
                val currentItem = selectedItems[which]
            }

            // alert dialog shouldn't be cancellable
            builder.setCancelable(false)

            // handle the positive button of the dialog
            builder.setPositiveButton("Done") { dialog, which ->
                for (i in checkedItems.indices) {
                    if (checkedItems[i]) {
                        //tvSelectedItemsPreview.text = String.format("%s%s, ", tvSelectedItemsPreview.text, selectedItems[i])
                        /*Toast.makeText(requireContext(), selectedItems[i], Toast.LENGTH_SHORT).show()
                        Log.i("Selected region", selectedItems[i])
                        countriesAdapter.notifyDataSetChanged()
                        countriesAdapter.filter.filter(selectedItems[i].lowercase())*/


                    }
                }
            }

            // handle the negative button of the alert dialog
            builder.setNegativeButton("CANCEL") { dialog, which -> }

            // handle the neutral button of the dialog to clear the selected items boolean checkedItem
            builder.setNeutralButton("CLEAR ALL") { dialog: DialogInterface?, which: Int ->
                Arrays.fill(checkedItems, false)
            }

            // create the builder
            builder.create()

            // create the alert dialog with the alert dialog builder instance
            val alertDialog = builder.create()
            alertDialog.show()


        }

    }

    private fun searchCountries() {
        var job: Job? = null

        binding.searchView.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                editable?.let {
                    if (editable.trim().toString().isNotBlank()) {
                        viewModel.searchCountry(editable.toString())
                    }

                    //if the user has not entered a search input,just display all countries.
                    //
                    else if (editable.toString().isEmpty()) {
                        viewModel.fetchAllCountries()
                    }

                }
            }
        }

        viewModel.countriesSearchObservable.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoadingState.Success -> {
                    countriesAdapter.asyncListDiffer.submitList(state.data)
                    binding.countriesRv.visibility = View.VISIBLE

                }
                is LoadingState.Loading -> {
                    binding.textView.visibility = View.GONE
                }
                is LoadingState.Error -> {
                    binding.textView.visibility = View.VISIBLE
                    binding.textView.text = state.message
                    binding.countriesRv.visibility = View.GONE
                }
            }

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountryListBinding.inflate(inflater, container, false)
        return binding.root
    }


    private fun getCountries() {
        countriesAdapter = CountriesAdapter()
        binding.countriesRv.apply {
            adapter = countriesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.countriesObservable.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoadingState.Loading -> {
                    binding.countriesRv.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                    binding.textView.visibility = View.GONE
                }

                is LoadingState.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.countriesRv.visibility = View.VISIBLE
                    countriesAdapter.asyncListDiffer.submitList(state.data)

                }
                is LoadingState.Error -> {
                    binding.countriesRv.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    binding.textView.visibility = View.VISIBLE
                    binding.textView.text = state.message
                }
            }
        }
    }
}