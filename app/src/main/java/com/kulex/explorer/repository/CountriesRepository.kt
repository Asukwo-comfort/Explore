package com.kulex.explorer.repository

import com.kulex.explorer.network.ApiService
import javax.inject.Inject

class CountriesRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getAllCountries() = apiService.getAllCountries()
    suspend fun searchCountry(name:String) = apiService.searchCountry(name)
    suspend fun getCountryDetails(name:String) = apiService.getCountryDetails(name)
}