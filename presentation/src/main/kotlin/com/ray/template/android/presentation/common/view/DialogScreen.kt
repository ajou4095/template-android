package com.ray.template.android.presentation.common.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.ray.template.android.presentation.R
import com.ray.template.android.presentation.common.theme.Body0
import com.ray.template.android.presentation.common.theme.Headline0
import com.ray.template.android.presentation.common.view.confirm.ConfirmButton
import com.ray.template.android.presentation.common.view.confirm.ConfirmButtonProperties
import com.ray.template.android.presentation.common.view.confirm.ConfirmButtonSize
import com.ray.template.android.presentation.common.view.confirm.ConfirmButtonType

@Composable
fun DialogScreen(
    isCancelable: Boolean = true,
    title: String = "",
    message: String = "",
    confirmMessage: String = stringResource(id = R.string.dialog_confirm),
    cancelMessage: String = stringResource(id = R.string.dialog_cancel),
    onConfirm: () -> Unit = {},
    onCancel: (() -> Unit)? = null,
    onDismissRequest: (() -> Unit),
) {
    Dialog(
        properties = DialogProperties(
            dismissOnBackPress = isCancelable,
            dismissOnClickOutside = isCancelable
        ),
        onDismissRequest = {
            onDismissRequest()
        }
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = White),
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 25.dp, start = 20.dp, end = 20.dp, bottom = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    style = Headline0
                )

                if (message.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = message,
                        style = Body0,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(modifier = Modifier.wrapContentSize()) {
                    if (onCancel != null) {
                        ConfirmButton(
                            properties = ConfirmButtonProperties(
                                size = ConfirmButtonSize.Large,
                                type = ConfirmButtonType.Secondary
                            ),
                            modifier = Modifier.weight(1f),
                            onClick = {
                                onCancel()
                                onDismissRequest()
                            }
                        ) { style ->
                            Text(
                                text = cancelMessage,
                                style = style
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))
                    }

                    ConfirmButton(
                        properties = ConfirmButtonProperties(
                            size = ConfirmButtonSize.Large,
                            type = ConfirmButtonType.Primary
                        ),
                        modifier = Modifier.weight(1f),
                        onClick = {
                            onConfirm()
                            onDismissRequest()
                        }
                    ) { style ->
                        Text(
                            text = confirmMessage,
                            style = style
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DialogScreenPreview1() {
    DialogScreen(
        title = "제목",
        message = "내용\n여러줄 넘어가면 이렇게 됨.\n가가가가가가가가가가가가가가가가가가가가가가가",
        onCancel = {},
        onDismissRequest = {}
    )
}

@Preview
@Composable
fun DialogScreenPreview2() {
    DialogScreen(
        title = "제목",
        onDismissRequest = {}
    )
}
