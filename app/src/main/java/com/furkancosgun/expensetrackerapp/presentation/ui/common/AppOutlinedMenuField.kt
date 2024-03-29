package com.furkancosgun.expensetrackerapp.presentation.ui.common

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.furkancosgun.expensetrackerapp.domain.model.KeyValue

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppOutlinedMenuField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    dropDownList: List<KeyValue<String, String>> = listOf(),
    onValueChange: (KeyValue<String, String>) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            OutlinedTextField(
                modifier = modifier.menuAnchor(),
                value = value,
                onValueChange = {},
                readOnly = true,
                label = {
                    Text(text = label)
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Category,
                        contentDescription = label
                    )
                },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                singleLine = true
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                dropDownList.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item.value) },
                        onClick = {
                            onValueChange.invoke(item)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}