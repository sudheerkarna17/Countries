package com.wrkspotnew.countries.presentation.countries_list.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wrkspotnew.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun TopHeaderView(modifier: Modifier = Modifier) {

    val contentColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        Color.Black
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        //Name
        Text(
            text = stringResource(R.string.app_name),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = contentColor
        )

        //Time
        Text(
            text = formatTime(System.currentTimeMillis()),
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal,
            color = contentColor
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_user_icon),
            tint = MaterialTheme.colorScheme.primary,
            contentDescription = null
        )
    }
}

fun formatTime(timeInMilliss: Long): String {
    val calendar = Calendar.getInstance().apply { timeInMillis = timeInMilliss }
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val daySuffix = getDaySuffix(day)
    val dateFormat = SimpleDateFormat("MMMM hh:mm a", Locale.getDefault())
    val formattedDate = dateFormat.format(Date(timeInMilliss))

    return "$day$daySuffix $formattedDate"
}

fun getDaySuffix(day: Int): String {
    return when {
        day in 11..13 -> "th"
        day % 10 == 1 -> "st"
        day % 10 == 2 -> "nd"
        day % 10 == 3 -> "rd"
        else -> "th"
    }
}