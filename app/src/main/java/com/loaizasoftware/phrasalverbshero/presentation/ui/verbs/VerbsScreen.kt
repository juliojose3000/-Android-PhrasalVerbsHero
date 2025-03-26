package com.loaizasoftware.phrasalverbshero.presentation.ui.verbs

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
import androidx.navigation.NavHostController
import com.loaizasoftware.phrasalverbshero.R
import com.loaizasoftware.phrasalverbshero.presentation.ui.general.AppBar
import com.loaizasoftware.phrasalverbshero.presentation.ui.general.LoadingIndicator
import com.loaizasoftware.phrasalverbshero.presentation.ui.general.SearchBar
import com.loaizasoftware.phrasalverbshero.presentation.viewmodel.VerbViewModel

@Composable
fun VerbsScreen(viewModel: VerbViewModel, navController: NavHostController) {

    Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.White, topBar = {

        AppBar(
            title = stringResource(R.string.app_bar_title_home_view),
            iconAppBar = Icons.Default.Home
        ) {
            navController.navigateUp()
        }

    }) { contentPadding ->

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
        ) {

            SearchBar("", {}, {})

            if (viewModel.isLoading.value) {
                LoadingIndicator()
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2), // Two columns
                    modifier = Modifier.fillMaxSize()
                ) {

                    items(viewModel.verbsState.value) { verb ->
                        VerbCardView(verb = verb) { verbId ->
                            navController.navigate("phrasal_verbs/$verbId")
                        }
                    }
                }
            }
        }

    }

}

/*@Preview
@Composable
fun PreviewHomeView() {
    HomeScreen(VerbViewModel())
}*/