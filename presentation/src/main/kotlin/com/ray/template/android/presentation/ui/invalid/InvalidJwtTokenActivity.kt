package com.ray.template.android.presentation.ui.invalid

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ray.template.android.presentation.R
import com.ray.template.android.presentation.common.theme.TemplateTheme
import com.ray.template.android.presentation.common.view.DialogScreen
import com.ray.template.android.presentation.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InvalidJwtTokenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InvalidJwtTokenScreen()
        }
    }

    @Composable
    fun InvalidJwtTokenScreen(
        viewModel: InvalidJwtTokenViewModel = hiltViewModel()
    ) {
        val context = LocalContext.current
        TemplateTheme {
            DialogScreen(
                title = stringResource(R.string.invalid_jwt_token_dialog_title),
                message = stringResource(R.string.invalid_jwt_token_dialog_content),
                onConfirm = {
                    val intent = Intent(context, MainActivity::class.java)
                    context.startActivity(intent)
                    finishAfterTransition()
                },
                onDismissRequest = {}
            )
        }
    }
}
