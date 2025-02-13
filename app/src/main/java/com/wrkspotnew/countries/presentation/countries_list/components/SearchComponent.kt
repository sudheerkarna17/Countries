package com.wrkspotnew.countries.presentation.countries_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import com.wrkspotnew.R

@Composable
fun SearchComponent(
    modifier: Modifier = Modifier,
    searchDisplay: String = "",
    onSearchDisplayChanged: (String) -> Unit,
    onSearchDisplayClosed: () -> Unit,
    tint: Color = MaterialTheme.colorScheme.primary,
    onExpandedChanged: (Boolean) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val textFieldFocusRequester = remember { FocusRequester() }

    var textFieldValue by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(searchDisplay, TextRange(searchDisplay.length)))
    }

    LaunchedEffect(searchDisplay) {
        if (searchDisplay != textFieldValue.text) {
            textFieldValue = TextFieldValue(searchDisplay, TextRange(searchDisplay.length))
        }
    }

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(textFieldFocusRequester),
        value = textFieldValue,
        onValueChange = {
            textFieldValue = it
            onSearchDisplayChanged(it.text)
        },
        trailingIcon = {
            if (textFieldValue.text.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.clear_search),
                    tint = tint,
                    modifier = Modifier
                        .clickable {
                            textFieldValue = TextFieldValue("")
                            onSearchDisplayChanged("")
                            onSearchDisplayClosed()
                        }
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search_icon),
                    tint = tint,
                    contentDescription = null
                )
            }
        },
        label = {
            Text(text = stringResource(R.string.search_by_country), color = tint)
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                onExpandedChanged(false)
            }
        ),
        singleLine = true
    )
}
