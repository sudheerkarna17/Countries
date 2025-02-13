package com.wrkspotnew.countries.presentation.countries_list

import com.wrkspotnew.countries.data.models.Country

data class CountriesListState(
    val isLoading: Boolean = false,
    val countries: List<Country> = emptyList(),
    val errorMessage: String = String()
)
