package com.loaizasoftware.phrasalverbshero.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun HomeView() {

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.LightGray
    ) {

        LazyColumn(
            contentPadding = it,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {



        }

    }

}

@Preview
@Composable
fun PreviewHomeView() {
    HomeView()
}