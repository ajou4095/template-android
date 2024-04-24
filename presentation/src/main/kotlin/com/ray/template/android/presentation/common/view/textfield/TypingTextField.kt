package com.ray.template.android.presentation.common.view.textfield

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ray.template.android.presentation.R
import com.ray.template.android.presentation.common.theme.Body1
import com.ray.template.android.presentation.common.theme.Gray400
import com.ray.template.android.presentation.common.theme.Gray900
import com.ray.template.android.presentation.common.theme.Red900
import com.ray.template.android.presentation.common.theme.Shapes
import com.ray.template.android.presentation.common.theme.Transparent
import com.ray.template.android.presentation.common.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypingTextField(
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hintText: String = "",
    isError: Boolean = false,
    isEnabled: Boolean = true,
    maxLines: Int = 1,
    maxTextLength: Int = 100,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIconContent: (@Composable () -> Unit) = {},
    trailingIconContent: (@Composable () -> Unit) = {},
    onTextFieldFocusChange: (Boolean) -> Unit = {}
) {
    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
    var isTextFieldFocused by remember { mutableStateOf(false) }

    val currentColor = when {
        isError -> Red900
        isTextFieldFocused -> Gray900
        else -> Gray400
    }
    val currentColorState = animateColorAsState(
        targetValue = currentColor,
        label = "color state"
    )

    Box(
        modifier = modifier
            .background(
                color = White,
                shape = Shapes.medium
            )
            .border(
                width = 1.dp,
                shape = Shapes.medium,
                color = currentColorState.value
            )
            .onFocusChanged {
                isTextFieldFocused = it.isFocused
                onTextFieldFocusChange(it.isFocused)
            }
            .padding(horizontal = 15.dp, vertical = 5.dp)
    ) {
        Row(
            modifier = Modifier.align(Alignment.TopCenter),
            verticalAlignment = Alignment.CenterVertically
        ) {
            leadingIconContent()
            BasicTextField(
                value = text,
                onValueChange = {
                    if (maxTextLength >= it.length) {
                        onValueChange(it)
                    }
                },
                enabled = isEnabled,
                modifier = Modifier.weight(1f),
                textStyle = Body1.merge(Gray900),
                singleLine = maxLines == 1,
                maxLines = maxLines,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                cursorBrush = SolidColor(value = currentColorState.value),
                interactionSource = interactionSource,
            ) { textField ->
                TextFieldDefaults.DecorationBox(
                    value = text,
                    innerTextField = textField,
                    enabled = isEnabled,
                    singleLine = maxLines == 1,
                    visualTransformation = visualTransformation,
                    interactionSource = interactionSource,
                    placeholder = {
                        Text(
                            text = hintText,
                            style = Body1.merge(Gray900)
                        )
                    },
                    contentPadding = PaddingValues(0.dp),
                    colors = OutlinedTextFieldDefaults.colors().copy(
                        focusedIndicatorColor = Transparent,
                        unfocusedIndicatorColor = Transparent,
                    )
                )
            }
            trailingIconContent()
        }
    }
}

@Preview
@Composable
private fun TypingTextField1Preview() {
    TypingTextField(
        text = "잘못 된 이름",
        hintText = "이름을 입력하세요.",
        modifier = Modifier.fillMaxWidth(),
        onValueChange = {},
        isError = true,
    )
}

@Preview
@Composable
private fun TypingTextField2Preview() {
    TypingTextField(
        text = "이름",
        hintText = "이름을 입력하세요.",
        modifier = Modifier.fillMaxWidth(),
        onValueChange = {},
        isError = false,
    )
}

@Preview
@Composable
private fun TypingTextField3Preview() {
    TypingTextField(
        text = "",
        hintText = "이름을 입력하세요.",
        modifier = Modifier.fillMaxWidth(),
        onValueChange = {},
        isError = false,
        trailingIconContent = {
            Icon(
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = "bottom icon",
                modifier = Modifier.size(20.dp)
            )
        },
        leadingIconContent = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "bottom icon",
                modifier = Modifier.size(20.dp)
            )
        }
    )
}

@Preview
@Composable
private fun TypingTextField4Preview() {
    TypingTextField(
        text = "엄청나게 긴 텍스트입니다. 엄청나게 긴 텍스트입니다. 엄청나게 긴 텍스트입니다. 엄청나게 긴 텍스트입니다. 엄청나게 긴 텍스트입니다.",
        modifier = Modifier.heightIn(min = 100.dp),
        hintText = "",
        maxLines = Int.MAX_VALUE,
        onValueChange = {}
    )
}
