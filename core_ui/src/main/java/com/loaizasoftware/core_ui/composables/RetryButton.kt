package com.loaizasoftware.core_ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.loaizasoftware.core_ui.R
import com.loaizasoftware.core_ui.general.PHButton


@Composable
fun RetryButton(action: () -> Unit) {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        PHButton(text = LocalContext.current.getString(R.string.retry_button), onClick = {
            action()
        })

    }

}

@Preview
@Composable
fun PreviewRetryButton() {
    RetryButton(action = {})
}