package com.wrkspotnew.countries.presentation.countries_list.components

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.wrkspotnew.countries.presentation.countries_list.DropDownItem

@Composable
fun DropDownMenuComponent(
    modifier: Modifier = Modifier,
    dropDownItems: List<DropDownItem>,
    onItemClick: (DropDownItem) -> Unit
) {


}