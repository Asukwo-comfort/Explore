package com.kulex.explorer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.kulex.explorer.R
import com.kulex.explorer.databinding.CountryItemBinding

class CountriesAdapter : RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>(), Filterable {
    private var countryList: ArrayList<CountryItem> = ArrayList()
    private var countryListFiltered: ArrayList<CountryItem> = ArrayList()

    override fun getFilter(): Filter {

        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()?: ""
                if (charString.isEmpty()) countryListFiltered = countryList else{
                    val filteredList = ArrayList<CountryItem>()
                    countryList
                        .filter {
                            (it.name.official.contains(constraint!!)) or (it.name.official.contains(constraint))
                        }
                        .forEach{filteredList.add(it)}
                    countryListFiltered = countryList
                }
                return FilterResults().apply { values = countryListFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                countryListFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<CountryItem>
                notifyDataSetChanged()

            }
        }
    }
    class CountryViewHolder(val binding: CountryItemBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = CountryItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val currentCountry = asyncListDiffer.currentList[position]
        with(holder) {
            binding.countryFlag.load(currentCountry.flags.png) {
                transformations(RoundedCornersTransformation(16f))
                placeholder(R.mipmap.ic_launcher)
            }
            binding.countryName.text = currentCountry.name.official
            binding.capitalCity.text = currentCountry.capital?.get(0).toString()

        }

        holder.itemView.setOnClickListener {
            /*val direction = FavoriteMoviesFragmentDirections.actionFavoriteMoviesFragmentToMovieDetailsFragment(currentMovie.id!!)
            it.findNavController().navigate(direction)
        */
            val direction = CountryListFragmentDirections.actionCountryListFragmentToCountryDetailsFragment(currentCountry.name.official)
            it.findNavController().navigate(direction)
        }

    }

    override fun getItemCount(): Int {
        return asyncListDiffer.currentList.size
    }

    private val diffUtil = object : DiffUtil.ItemCallback<CountryItem>() {
        override fun areItemsTheSame(oldItem: CountryItem, newItem: CountryItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CountryItem, newItem: CountryItem): Boolean {
            return oldItem == newItem
        }
    }

    val asyncListDiffer = AsyncListDiffer(this, diffUtil)



}