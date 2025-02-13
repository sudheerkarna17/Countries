package com.wrkspotnew.countries.presentation.countries_list

import ShimmerEffect
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.wrkspotnew.R
import com.wrkspotnew.countries.presentation.countries_list.components.CountryListItem
import com.wrkspotnew.countries.presentation.countries_list.components.SearchComponent
import com.wrkspotnew.countries.presentation.countries_list.components.TopHeaderView

@Composable
fun CountriesListScreen(
    modifier: Modifier = Modifier,
    countriesListState: CountriesListState,
    searchDisplay: String = "",
    dropDownList: List<DropDownItem>,
    onSearchQueryChanged: (String) -> Unit,
    onFilterSelected: (DropDownItem) -> Unit
) {
    var isContextMenuVisible by rememberSaveable { mutableStateOf(false) }

    if (countriesListState.isLoading) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            //Shimmer
            ShimmerEffect()
        }
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            // Top Header
            TopHeaderView(modifier = Modifier.fillMaxWidth())

            // SearchView and Filter
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // SearchView
                SearchComponent(
                    modifier = Modifier.weight(8f),
                    searchDisplay = searchDisplay,
                    onSearchDisplayChanged = onSearchQueryChanged,
                    onSearchDisplayClosed = { onSearchQueryChanged("") },
                    onExpandedChanged = {

                    }
                )

                Spacer(modifier = Modifier.size(10.dp))

                // Filter Icon
                Box {
                    Icon(
                        modifier = Modifier
                            .size(30.dp)
                            .clickable { isContextMenuVisible = true },
                        painter = painterResource(id = R.drawable.ic_filter_icon),
                        tint = MaterialTheme.colorScheme.primary,
                        contentDescription = stringResource(R.string.filter_by_population)
                    )

                    DropdownMenu(
                        expanded = isContextMenuVisible,
                        onDismissRequest = { isContextMenuVisible = false },
                    ) {
                        dropDownList.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item.text) },
                                onClick = {
                                    onFilterSelected(item)
                                    isContextMenuVisible = false
                                }
                            )
                        }
                    }
                }
            }

            // Countries List
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (countriesListState.countries.isEmpty()) {
                    item {
                        //No Data Found
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.no_countries_found),
                            )
                        }
                    }
                } else {
                    items(countriesListState.countries) { country ->
                        CountryListItem(
                            modifier = Modifier.fillMaxWidth(),
                            country = country
                        )
                    }
                }
            }
        }
    }
}

