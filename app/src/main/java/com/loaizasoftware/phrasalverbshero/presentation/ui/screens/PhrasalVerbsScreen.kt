package com.loaizasoftware.phrasalverbshero.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.loaizasoftware.core_ui.composables.CardView
import com.loaizasoftware.phrasalverbshero.R
import com.loaizasoftware.core_ui.general.AppBar
import com.loaizasoftware.core_ui.general.LoadingIndicator
import com.loaizasoftware.core_ui.general.PHButton
import com.loaizasoftware.core_ui.composables.ContainerWithAnim
import com.loaizasoftware.phrasalverbshero.presentation.viewmodel.PhrasalVerbsViewModel

@Composable
fun PhrasalVerbsScreen(
    viewModel: PhrasalVerbsViewModel,
    verb: String,
    navHostController: NavHostController,
    getString: (Int) -> String,
    practiceBtnOnClick: () -> Unit,
    quizBtnOnClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = { // ✅ Move AppBar inside Scaffold’s `topBar`
            AppBar(
                title = "${getString(R.string.pv_screen_title)} $verb",
                iconAppBar = Icons.AutoMirrored.Filled.ArrowBack
            ) {
                navHostController.navigateUp()
            }
        }
    ) { contentPadding ->

        LoadingIndicator(isVisible = viewModel.isLoadingPhrasalVerbs.value)

        if (!viewModel.isLoadingPhrasalVerbs.value) {

            PhrasalVerbCards(
                contentPadding,
                viewModel,
                navHostController,
                getString,
                practiceBtnOnClick,
                quizBtnOnClick
            )

        }

    }


}


@Composable
fun PhrasalVerbCards(
    contentPadding: PaddingValues,
    viewModel: PhrasalVerbsViewModel,
    navHostController: NavHostController,
    getString: (Int) -> String,
    practiceBtnOnClick: () -> Unit,
    quizBtnOnClick: () -> Unit
) {

    ContainerWithAnim {

        Column(
            modifier = Modifier
                .fillMaxSize() // ✅ Fix: Use fillMaxSize() instead of fillMaxHeight()
                .padding(contentPadding),
            verticalArrangement = Arrangement.SpaceBetween // ✅ Ensures the buttons stay at the bottom
        ) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .weight(1f) // ✅ Fix: Allows buttons to take remaining space
                    .padding(top = 16.dp)
            ) {
                items(viewModel.phrasalVerbsState.value) { phrasalVerb ->
                    CardView(text = phrasalVerb.phrasalVerb, id = phrasalVerb.id) {
                        navHostController.navigate("phrasal_verbs/${phrasalVerb.id}/meanings")
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                PHButton(text = getString(R.string.pv_button_practice)) {
                    practiceBtnOnClick()
                }
                Spacer(modifier = Modifier.width(16.dp))
                PHButton(text = getString(R.string.pv_button_quiz)) {
                    quizBtnOnClick()
                }
            }

        }

    }

}

