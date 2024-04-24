package com.ray.template.android.presentation.common.view.dropdown

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ray.template.android.presentation.common.theme.Body1
import com.ray.template.android.presentation.common.theme.Gray900
import com.ray.template.android.presentation.common.theme.Space20
import com.ray.template.android.presentation.common.theme.Space80
import com.ray.template.android.presentation.common.theme.White

@Composable
fun <T> TextDropdownMenu(
    items: List<T>,
    label: (T) -> String = { it.toString() },
    isExpanded: Boolean,
    onDismissRequest: () -> Unit,
    onClick: (T) -> Unit,
) {
    val localConfiguration = LocalConfiguration.current

    DropdownMenu(
        modifier = Modifier
            .requiredSizeIn(maxHeight = localConfiguration.screenHeightDp.dp - Space80)
            .width(localConfiguration.screenWidthDp.dp * 2 / 5)
            .background(White),
        expanded = isExpanded,
        onDismissRequest = onDismissRequest,
    ) {
        items.forEach { item ->
            DropdownMenuItem(
                text = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = Space20),
                        text = label(item),
                        style = Body1.merge(Gray900)
                    )
                },
                onClick = {
                    onClick(item)
                    onDismissRequest()
                },
                contentPadding = PaddingValues(0.dp)
            )
        }
    }
}

@Preview
@Composable
private fun TextDropdownMenuPreview() {
    TextDropdownMenu(
        items = listOf("Item1", "Item2", "Item3"),
        isExpanded = true,
        onDismissRequest = {},
        onClick = {}
    )
}
