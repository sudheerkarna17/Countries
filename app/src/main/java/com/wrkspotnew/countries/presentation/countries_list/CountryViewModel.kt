package com.wrkspotnew.countries.presentation.countries_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wrkspotnew.countries.data.models.Country
import com.wrkspotnew.countries.data.repository.CountryRepository
import com.wrkspotnew.countries.domain.utils.ResultState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {
    private val repository = CountryRepository()

    private val _countryState = MutableStateFlow(CountriesListState())
    val countryState = _countryState

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery

    private var allCountries: List<Country> = emptyList()

    var dropDownItemsList = listOf(
        DropDownItem(text = "Population < 1M"),
        DropDownItem(text = "Population < 5M"),
        DropDownItem(text = "Population < 10M"),
        DropDownItem(text = "Clear Filters")
    )

    init {
        loadCountries()
    }


    //Load Countries
    private fun loadCountries() {
        _countryState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            when (val result = repository.fetchCountries()) {
                is ResultState.Success -> {
                    allCountries = result.data
                    _countryState.update { it.copy(isLoading = false, countries = allCountries) }
                }

                is ResultState.Error -> {
                    _countryState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.message
                        )
                    }
                }

                is ResultState.Loading -> {
                    _countryState.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    //Query Search
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        applyFilters()
    }

    //Population Filter
    fun filterCountriesByPopulation(selectedDropDownItem: DropDownItem?) {
        if (selectedDropDownItem?.text == "Clear Filters") {
            clearFilters()
            return
        }
        applyFilters(selectedDropDownItem)
    }

    private fun clearFilters() {
        val filteredList = if (_searchQuery.value.isNotEmpty()) {
            allCountries.filter { country ->
                country.name.contains(_searchQuery.value, ignoreCase = true)
            }
        } else {
            allCountries
        }
        _countryState.update { it.copy(countries = filteredList) }
    }

    private fun applyFilters(selectedDropDownItem: DropDownItem? = null) {
        val maxPopulation = when (selectedDropDownItem?.text) {
            "Population < 1M" -> 1000000
            "Population < 5M" -> 5000000
            "Population < 10M" -> 10000000
            else -> null
        }

        val filteredList = allCountries
            .filter { country -> maxPopulation == null || country.population < maxPopulation }
            .filter { country -> country.name.contains(searchQuery.value, ignoreCase = true) }

        _countryState.update { it.copy(countries = filteredList) }
    }
}



