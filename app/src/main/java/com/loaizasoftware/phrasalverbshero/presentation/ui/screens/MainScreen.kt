package com.loaizasoftware.phrasalverbshero.presentation.ui.screens

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
import com.loaizasoftware.core_ui.composables.CardView
import com.loaizasoftware.phrasalverbshero.R
import com.loaizasoftware.core_ui.general.AppBar
import com.loaizasoftware.core_ui.general.LoadingIndicator
import com.loaizasoftware.core_ui.general.SearchBar
import com.loaizasoftware.core_ui.composables.ContainerWithAnim
import com.loaizasoftware.core_ui.composables.RetryButton
import com.loaizasoftware.phrasalverbshero.presentation.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavHostController) {

    Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color.White, topBar = {

        AppBar(
            title = stringResource(R.string.app_bar_title_home_view),
            iconAppBar = Icons.Default.Home
        ) {
            navController.navigateUp()
        }

    }) { contentPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {

            LoadingIndicator(isVisible = viewModel.isLoading.value)

            if (!viewModel.isLoading.value) {

                ContainerWithAnim(durationMillis = 1000) {

                    Column {

                        SearchBar(
                            query = viewModel.searchVerb.value,
                            onQueryChange = {
                                viewModel.searchVerbs(it)
                            },
                            onClear = {
                                viewModel.searchVerbs("")
                            }
                        )

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2), // Two columns
                            modifier = Modifier.fillMaxSize()
                        ) {

                            items(viewModel.filteredVerbs.value) { phrasalVerbPart ->

                                CardView(text = phrasalVerbPart) {
                                    navController.navigate("phrasal_verbs/$phrasalVerbPart")
                                }

                            }
                        }

                    }

                }

            } else if (viewModel.onErrorResponse.value != null) {

                RetryButton(action = {
                    viewModel.loadVerbs()
                    viewModel.onErrorResponse.value = null
                })

            }

        }

    }

}

/*@Preview
@Composable
fun PreviewHomeView() {
    HomeScreen(VerbViewModel())
}*/