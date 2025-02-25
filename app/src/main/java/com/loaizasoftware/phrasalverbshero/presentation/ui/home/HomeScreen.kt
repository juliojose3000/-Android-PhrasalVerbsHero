package com.loaizasoftware.phrasalverbshero.presentation.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.loaizasoftware.phrasalverbshero.R
import com.loaizasoftware.phrasalverbshero.data.local.DummyData
import com.loaizasoftware.phrasalverbshero.presentation.ui.general.AppBar
import com.loaizasoftware.phrasalverbshero.presentation.ui.general.SearchBar

@Composable
fun HomeView() {

    Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.White, topBar = {

        AppBar(
            title = stringResource(R.string.app_bar_title_home_view),
            iconAppBar = Icons.Default.Home
        ) {
            //navController?.navigateUp()
        }

    }) { contentPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {

            SearchBar("", {}, {})

            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // Two columns
                modifier = Modifier.fillMaxSize()
            ) {
                items(DummyData.getVerbs()) { verb ->

                    VerbCardView(verbName = verb.name)

                }
            }

        }

    }

}

@Preview
@Composable
fun PreviewHomeView() {
    HomeView()
}