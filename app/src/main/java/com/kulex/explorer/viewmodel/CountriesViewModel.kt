package com.kulex.explorer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kulex.explorer.models.CountryItem
import com.kulex.explorer.repository.CountriesRepository
import com.kulex.explorer.util.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val repository: CountriesRepository
) : ViewModel() {

    private var _countriesObservable = MutableLiveData<LoadingState<CountryItem>>()
    val countriesObservable: LiveData<LoadingState<CountryItem>> = _countriesObservable

    private var _countriesSearchObservable = MutableLiveData<LoadingState<CountryItem>>()
    val countriesSearchObservable: LiveData<LoadingState<CountryItem>> =
        _countriesSearchObservable

    fun searchCountry(countryName: String) = viewModelScope.launch {
        try {
            _countriesSearchObservable.postValue(LoadingState.Loading())
            val result = repository.searchCountry(countryName)
            if (result.body().isNullOrEmpty()) {
                _countriesSearchObservable.postValue(LoadingState.Error("No results found"))
            } else {
                _countriesSearchObservable.postValue(LoadingState.Success(result.body()!!))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            when (e) {
                is IOException -> _countriesSearchObservable.postValue(LoadingState.Error("Ensure you have an active internet connection"))
            }
        }
    }

    fun fetchAllCountries() = viewModelScope.launch {

        try {
            _countriesObservable.postValue(LoadingState.Loading())
            val result = repository.getAllCountries()

            if (result.isSuccessful && result.body()?.isNotEmpty()!!) {
                _countriesObservable.postValue(LoadingState.Success(result.body()!!))
            }


        } catch (e: Exception) {
            e.printStackTrace()
            when (e) {
                is IOException -> _countriesObservable.postValue(LoadingState.Error("No internet connection"))
            }
        }

    }




    init {

        fetchAllCountries()
    }
}