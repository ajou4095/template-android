package com.ray.template.android.presentation.common.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialog
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialogProperties
import com.ray.template.android.presentation.common.theme.Body0
import com.ray.template.android.presentation.common.theme.Radius12
import com.ray.template.android.presentation.common.view.confirm.ConfirmButton
import com.ray.template.android.presentation.common.view.confirm.ConfirmButtonProperties
import com.ray.template.android.presentation.common.view.confirm.ConfirmButtonSize
import com.ray.template.android.presentation.common.view.confirm.ConfirmButtonType

@Composable
fun BottomSheetScreen(
    onDismissRequest: () -> Unit,
    properties: BottomSheetDialogProperties = BottomSheetDialogProperties(),
    content: @Composable () -> Unit
) {

    BottomSheetDialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = Radius12,
                        topEnd = Radius12
                    )
                )
                .fillMaxWidth()
                .background(White)
        ) {
            content()
        }
    }
}

@Preview(apiLevel = 34)
@Composable
private fun BottomSheetScreenPreview() {
    var isShowing by remember { mutableStateOf(true) }
    if (isShowing) {
        BottomSheetScreen(
            onDismissRequest = { isShowing = false },
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "제목",
                    style = Body0
                )

                Spacer(
                    modifier = Modifier.height(60.dp)
                )

                ConfirmButton(
                    modifier = Modifier.fillMaxWidth(),
                    properties = ConfirmButtonProperties(
                        size = ConfirmButtonSize.Large,
                        type = ConfirmButtonType.Primary
                    ),
                    onClick = {
                        isShowing = false
                    }
                ) { style ->
                    Text(
                        text = "확인",
                        style = style
                    )
                }
            }
        }
    }
}
