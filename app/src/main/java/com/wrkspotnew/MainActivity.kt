package com.wrkspotnew

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.wrkspotnew.countries.presentation.countries_list.CountriesListScreen
import com.wrkspotnew.countries.presentation.countries_list.CountryViewModel
import com.wrkspotnew.ui.theme.WrkspotTheme

class MainActivity : ComponentActivity() {
    private val viewModel: CountryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WrkspotTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val state by viewModel.countryState.collectAsState()
                    val searchQuery by viewModel.searchQuery.collectAsState()

                    CountriesListScreen(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(innerPadding),
                        countriesListState = state,
                        searchDisplay = searchQuery,
                        dropDownList = viewModel.dropDownItemsList,
                        onSearchQueryChanged = { query ->
                            viewModel.updateSearchQuery(query)
                        },
                        onFilterSelected = { selectedDropDownItem ->
                            viewModel.filterCountriesByPopulation(selectedDropDownItem)
                        }
                    )
                }
            }
        }
    }
}

