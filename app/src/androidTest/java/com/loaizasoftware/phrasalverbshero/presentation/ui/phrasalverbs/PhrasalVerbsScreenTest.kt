package com.loaizasoftware.phrasalverbshero.presentation.ui.phrasalverbs


import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavType
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.loaizasoftware.phrasalverbshero.core.None
import com.loaizasoftware.phrasalverbshero.data.api.ApiService
import com.loaizasoftware.phrasalverbshero.data.repository.PhrasalVerbRepository
import com.loaizasoftware.phrasalverbshero.data.repository.VerbRepository
import com.loaizasoftware.phrasalverbshero.domain.model.PhrasalVerb
import com.loaizasoftware.phrasalverbshero.domain.model.Verb
import com.loaizasoftware.phrasalverbshero.domain.usecase.GetPhrasalVerbsUseCase
import com.loaizasoftware.phrasalverbshero.domain.usecase.GetVerbsUseCase
import com.loaizasoftware.phrasalverbshero.presentation.ui.verbs.FakeApiService
import com.loaizasoftware.phrasalverbshero.presentation.ui.verbs.VerbsScreen
import com.loaizasoftware.phrasalverbshero.presentation.viewmodel.PhrasalVerbsViewModel
import com.loaizasoftware.phrasalverbshero.presentation.viewmodel.VerbViewModel
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Call


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


class FakePhrasalVerbRepository : PhrasalVerbRepository(FakeApiService())

class FakeGetPhrasalVerbsUseCase : GetPhrasalVerbsUseCase(FakePhrasalVerbRepository()) {
    override fun run(params: Long): Single<List<PhrasalVerb>> {
        return Single.just(emptyList()) // Not used in UI test
    }
}

class FakePhrasalVerbViewModel : PhrasalVerbsViewModel(FakeGetPhrasalVerbsUseCase()) {
    init {
        isLoading.value = false
        phrasalVerbsState.value = listOf(
            PhrasalVerb(id = 1L, phrasalVerb = "Go off", meanings = emptyList()),
            PhrasalVerb(id = 2L, phrasalVerb = "Go on", meanings = emptyList())
        )
    }
}