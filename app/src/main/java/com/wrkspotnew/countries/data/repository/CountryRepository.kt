package com.wrkspotnew.countries.data.repository

import com.wrkspotnew.countries.data.models.Country
import com.wrkspotnew.countries.data.network.RetrofitInstance
import com.wrkspotnew.countries.domain.utils.ResultState

class CountryRepository {
    suspend fun fetchCountries(): ResultState<List<Country>> {
        return try {
            val response = RetrofitInstance.api.getCountries()
            ResultState.Success(response)
        } catch (e: Exception) {
            ResultState.Error(e.localizedMessage ?: "Error occurred")
        }
    }
}