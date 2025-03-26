package com.loaizasoftware.phrasalverbshero.presentation.ui.verbs

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.loaizasoftware.phrasalverbshero.core.None
import com.loaizasoftware.phrasalverbshero.data.api.ApiService
import com.loaizasoftware.phrasalverbshero.data.repository.VerbRepository
import com.loaizasoftware.phrasalverbshero.domain.model.PhrasalVerb
import com.loaizasoftware.phrasalverbshero.domain.model.Verb
import com.loaizasoftware.phrasalverbshero.domain.usecase.GetVerbsUseCase
import com.loaizasoftware.phrasalverbshero.presentation.viewmodel.VerbViewModel
import io.reactivex.Single
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

    @Before
    fun setup() {
        fakeViewModel = FakeVerbViewModel()
    }

    @Test
    fun verbs_are_displayed_correctly() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        composeTestRule.setContent {
            navController.setLifecycleOwner(LocalLifecycleOwner.current)
            VerbsScreen(viewModel = fakeViewModel, navController = navController)
        }

        composeTestRule.onAllNodesWithText("Go").assertCountEquals(1)
        composeTestRule.onAllNodesWithText("Come").assertCountEquals(1)
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
}

class FakeVerbRepository : VerbRepository(FakeApiService())

class FakeGetVerbsUseCase : GetVerbsUseCase(FakeVerbRepository()) {
    override fun run(params: None): Single<List<Verb>> {
        return Single.just(emptyList()) // Not used in UI test
    }
}

class FakeVerbViewModel : VerbViewModel(FakeGetVerbsUseCase()) {
    init {
        isLoading.value = false
        verbsState.value = listOf(
            Verb(id = 1L, name = "Go", phrasalVerbs = emptyList()),
            Verb(id = 2L, name = "Come", phrasalVerbs = emptyList())
        )
    }
}