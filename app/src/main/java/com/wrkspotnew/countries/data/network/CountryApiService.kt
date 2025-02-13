package com.wrkspotnew.countries.data.network

import com.wrkspotnew.countries.data.models.Country
import retrofit2.http.GET

// retrofit API Service
interface CountryApiService {
    @GET("countries/countries")
    suspend fun getCountries(): List<Country>
}