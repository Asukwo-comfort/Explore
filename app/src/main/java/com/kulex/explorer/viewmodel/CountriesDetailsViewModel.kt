package com.kulex.explorer.viewmodel

import androidx.lifecycle.*
import com.kulex.explorer.models.CountryItem
import com.kulex.explorer.repository.CountriesRepository
import com.kulex.explorer.util.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class CountryDetailsViewModel @Inject constructor(
    private val repository: CountriesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val countryName = savedStateHandle.get<String>("countryName")

    private val _countryDetailsObservable = MutableLiveData<LoadingState<CountryItem>>()
    val countryDetailsObservable : LiveData<LoadingState<CountryItem>> = _countryDetailsObservable


    fun fetchCountryDetails(countryName:String) = viewModelScope.launch {

        try {
            _countryDetailsObservable.postValue(LoadingState.Loading())
            val result = repository.getCountryDetails(countryName)


            if (!result.isSuccessful){
                _countryDetailsObservable.postValue(LoadingState.Error("An error happened. Please retry"))
            }

            else{
                _countryDetailsObservable.postValue(LoadingState.Success(result.body()!!))
            }


        }catch (e:Exception){
            when(e){
                is IOException ->{
                    _countryDetailsObservable.postValue(LoadingState.Error("Ensure you have an active internet connection"))
                }
            }
        }
    }


    init {
        fetchCountryDetails(countryName!!)
    }
}