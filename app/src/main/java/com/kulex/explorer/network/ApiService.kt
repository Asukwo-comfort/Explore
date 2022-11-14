package com.kulex.explorer.network

import com.kulex.explorer.models.CountryItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("all")
    suspend fun getAllCountries() : Response<CountryItem>
    @GET("name/{country_name}")
    suspend fun searchCountry(
        @Path("country_name") countryName:String
    ) : Response<CountryItem>

    @GET("name/{country_name}")
    suspend fun getCountryDetails(
        @Path("country_name") countryName:String
    ) : Response<CountryItem>



}