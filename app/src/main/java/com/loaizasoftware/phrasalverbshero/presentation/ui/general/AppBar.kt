package com.loaizasoftware.phrasalverbshero.presentation.ui.general

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.loaizasoftware.phrasalverbshero.presentation.ui.theme.Purple40
import com.loaizasoftware.phrasalverbshero.presentation.ui.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(title: String, iconAppBar: ImageVector, iconClickAction: () -> Unit) {

    TopAppBar(
        title = {
            Text(title)
        },
        navigationIcon = {
            Icon(imageVector = iconAppBar,
                contentDescription = "",
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .clickable { iconClickAction() })
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Purple40,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White
        )

    )

}

@Preview
@Composable
fun PreviewAppBar() {
    AppBar(title = "Hello World", Icons.Default.Home, {})
}