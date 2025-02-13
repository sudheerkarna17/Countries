package com.wrkspotnew.countries.presentation.countries_list.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.wrkspotnew.R
import com.wrkspotnew.countries.data.models.Country

@Composable
fun CountryListItem(
    modifier: Modifier = Modifier,
    country: Country
) {

    val contentColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }

    Card(
        modifier = modifier
            .padding(start = 10.dp, end = 10.dp, top = 2.dp, bottom = 2.dp)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                //Country Flag
                AsyncImage(
                    model = country.media.flag,
                    contentDescription = country.name,
                    modifier = Modifier
                        .size(50.dp)
                )

                //Name
                Text(
                    text = country.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = contentColor
                )
            }


            //Price and Percentage Change
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.End
            ) {

                //Capital
                Text(
                    text = "${stringResource(R.string.capital)}: ${country.capital}",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    color = contentColor
                )
                Spacer(modifier = Modifier.height(2.dp))

                //Currency
                Text(
                    text = "${stringResource(R.string.currency)}: ${country.currency}",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    color = contentColor
                )
                Spacer(modifier = Modifier.height(2.dp))
                //Population
                Text(
                    text = "${stringResource(R.string.population)}: ${country.population}",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal,
                    color = contentColor
                )
            }
        }
    }

}