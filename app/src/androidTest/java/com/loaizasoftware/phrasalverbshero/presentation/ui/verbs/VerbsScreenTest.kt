package com.loaizasoftware.phrasalverbshero.presentation.ui.verbs


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
import com.loaizasoftware.phrasalverbshero.data.repository.VerbRepositoryImpl
import com.loaizasoftware.phrasalverbshero.domain.model.Definition
import com.loaizasoftware.phrasalverbshero.domain.model.PhrasalVerb
import com.loaizasoftware.phrasalverbshero.domain.model.Verb
import com.loaizasoftware.phrasalverbshero.domain.usecase.GetVerbsUseCase
import com.loaizasoftware.phrasalverbshero.presentation.ui.screens.VerbsScreen
import com.loaizasoftware.phrasalverbshero.presentation.viewmodel.VerbViewModel
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Call


@RunWith(AndroidJUnit4::class)
class VerbsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var fakeViewModel: FakeVerbViewModel

    private lateinit var navController: TestNavHostController

    @Before
    fun setup() {
        fakeViewModel = FakeVerbViewModel()
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        navController.navigatorProvider.addNavigator(ComposeNavigator())
    }

    @Test
    fun verbs_are_displayed_correctly() {
        composeTestRule.setContent {
            navController.setLifecycleOwner(LocalLifecycleOwner.current)
            VerbsScreen(viewModel = fakeViewModel, navController = navController)
        }

        composeTestRule.onAllNodesWithText("Go").assertCountEquals(1)
        composeTestRule.onAllNodesWithText("Come").assertCountEquals(1)
    }

    @Test
    fun navigate_to_phrasal_verbs_screen() {
        composeTestRule.setContent {
            navController.setLifecycleOwner(LocalLifecycleOwner.current)

            NavHost(navController = navController, startDestination = "verbs_screen") {
                composable("verbs_screen") {
                    VerbsScreen(viewModel = fakeViewModel, navController = navController)
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
    }
}


class FakeApiService : ApiService {
    override fun getVerbs(): Call<List<Verb>> {
        throw UnsupportedOperationException("Not used in UI test")
    }

    override fun getVerbsSingle(): Single<List<Verb>> {
        throw UnsupportedOperationException("Not used in UI test")
    }

    override fun getPhrasalVerbs(verbId: Long): Single<List<PhrasalVerb>> {
        throw UnsupportedOperationException("Not used in this test")
    }

    override fun getPhrasalVerbDefinitions(phrasalVerbId: Long): Single<List<Definition>> {
        throw UnsupportedOperationException("Not used in this test")
    }
}

class FakeVerbRepositoryImpl : VerbRepositoryImpl(FakeApiService())

class FakeGetVerbsUseCase : GetVerbsUseCase(FakeVerbRepositoryImpl()) {
    override fun run(params: None): Single<List<Verb>> {
        return Single.just(emptyList()) // Not used in UI test
    }
}

class FakeVerbViewModel : VerbViewModel(FakeGetVerbsUseCase()) {
    init {
        isLoading.value = false
        filteredVerbs.value = listOf(
            Verb(id = 1L, name = "Go", phrasalVerbs = emptyList()),
            Verb(id = 2L, name = "Come", phrasalVerbs = emptyList())
        )
    }
}