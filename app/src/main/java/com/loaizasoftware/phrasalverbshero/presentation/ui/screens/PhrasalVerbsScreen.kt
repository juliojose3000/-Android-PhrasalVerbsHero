package com.loaizasoftware.phrasalverbshero.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
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
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // LazyVerticalGrid in the background
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 96.dp)
        ) {
            items(viewModel.phrasalVerbsState.value) { phrasalVerb ->

                //The bottom lines allows us to detect the last item and add a different padding to avoid
                //the bottom buttons to overlap the cards
                val isTheLast = phrasalVerb.id == viewModel.phrasalVerbsState.value.last().id
                val paddingBottom = if (isTheLast) 150.dp else 16.dp

                CardView(text = phrasalVerb.phrasalVerb, id = phrasalVerb.id, paddingBottom = paddingBottom) {
                    navHostController.navigate("phrasal_verbs/${phrasalVerb.id}/definitions")
                }
            }

        }

        // BottomButtons in the foreground
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .zIndex(1f)  // Ensures the buttons are drawn on top
                .background(Color.Transparent)
        ) {

            BottomButtons(getString, practiceBtnOnClick, quizBtnOnClick)

        }
    }
}


@Composable
fun BottomButtons(
    getString: (Int) -> String,
    practiceBtnOnClick: () -> Unit,
    quizBtnOnClick: () -> Unit
) {
    Column {

        // ✅ Transparent gradient bar at the top of the buttons
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp) // Height for the transparent effect
                .background(
                    brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,               // Top: Fully transparent
                            Color.White.copy(alpha = 1f)   // Bottom: Semi-transparent white
                        )
                    )
                )
        )

        Box(modifier = Modifier
            .background(Color.White)
            .padding(16.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    //.padding(16.dp)
                    .background(Color.White),
                horizontalArrangement = Arrangement.Center
            ) {

                val modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(0.5f)

                PHButton(text = getString(R.string.pv_button_practice), modifier = modifier) {
                    practiceBtnOnClick()
                }
                Spacer(modifier = Modifier.width(16.dp))
                PHButton(text = getString(R.string.pv_button_quiz), modifier = modifier) {
                    quizBtnOnClick()
                }
            }

        }

    }
}


