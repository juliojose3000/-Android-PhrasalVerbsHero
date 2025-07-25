package com.loaizasoftware.phrasalverbshero.presentation.ui.phrasalverbs


import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.loaizasoftware.phrasalverbshero.core.network.ApiResult
import com.loaizasoftware.phrasalverbshero.core.network.safeApiCall
import com.loaizasoftware.phrasalverbshero.data.api.ApiService
import com.loaizasoftware.phrasalverbshero.data.repository.FakePhrasalVerbRepositoryImpl
import com.loaizasoftware.phrasalverbshero.data.repository.PhrasalVerbRepositoryImpl
import com.loaizasoftware.phrasalverbshero.domain.model.PhrasalVerb
import com.loaizasoftware.phrasalverbshero.domain.repository.PhrasalVerbRepository
import com.loaizasoftware.phrasalverbshero.domain.usecase.GetPhrasalVerbsUseCase
import com.loaizasoftware.phrasalverbshero.presentation.ui.screens.PhrasalVerbsScreen
import com.loaizasoftware.phrasalverbshero.presentation.ui.verbs.FakeApiService
import com.loaizasoftware.phrasalverbshero.presentation.viewmodel.FakePhrasalVerbViewModel
import com.loaizasoftware.phrasalverbshero.presentation.viewmodel.PhrasalVerbsViewModel
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PhrasalVerbsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var fakeViewModel: FakePhrasalVerbViewModel

    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        fakeViewModel = FakePhrasalVerbViewModel()
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.navigatorProvider.addNavigator(ComposeNavigator())
    }

    @Test
    fun phrasal_verbs_are_displayed_correctly() {
        composeTestRule.setContent {
            navController.setLifecycleOwner(LocalLifecycleOwner.current)
            PhrasalVerbsScreen(
                viewModel = fakeViewModel,
                verb = "go",
                navHostController = navController,
                getString = { _ -> "" }
            )
        }

        composeTestRule.onAllNodesWithText("Go off").assertCountEquals(1)
        composeTestRule.onAllNodesWithText("Go on").assertCountEquals(1)
    }

    /*@Test
    fun navigate_to_phrasal_verbs_screen() {
        composeTestRule.setContent {
            navController.setLifecycleOwner(LocalLifecycleOwner.current)

            NavHost(navController = navController, startDestination = "verbs_screen") {
                composable("verbs_screen") {
                    //VerbsScreen(viewModel = fakeViewModel, navController = navController)
                }
                composable(
                    "phrasal_verbs/{verbId}",
                    arguments = listOf(navArgument("verbId") { type = NavType.LongType })
                ) {
                    // No-op fake destination
                }
            }
        }

        composeTestRule.onNodeWithText("Go").performClick()
        composeTestRule.waitForIdle()

        // ✅ Fix: assert the destination pattern AND check actual value passed
        assertEquals("phrasal_verbs/{verbId}", navController.currentBackStackEntry?.destination?.route)
        assertEquals(1L, navController.currentBackStackEntry?.arguments?.getLong("verbId"))
    }*/
}