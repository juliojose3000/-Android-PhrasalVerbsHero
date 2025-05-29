package com.loaizasoftware.phrasalverbshero.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.loaizasoftware.phrasalverbshero.data.repository.VerbRepositoryImpl
import com.loaizasoftware.phrasalverbshero.domain.usecase.GetVerbsUseCase
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.TestScheduler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: VerbRepositoryImpl

    //@Mock
    private lateinit var getVerbsUseCase: GetVerbsUseCase

    private lateinit var viewModel: MainViewModel

    private val testDispatcher = StandardTestDispatcher()

    private val testScheduler = TestScheduler() // RxJava TestScheduler

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)

        // Override RxJava schedulers for testing
        RxJavaPlugins.setIoSchedulerHandler { testScheduler }
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
        RxJavaPlugins.setNewThreadSchedulerHandler { testScheduler }

        // ✅ Override RxJava main thread scheduler for unit testing
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

        getVerbsUseCase = GetVerbsUseCase(repository)
        viewModel = MainViewModel(getVerbsUseCase)

    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        RxAndroidPlugins.reset()
        //Mockito.reset(repository) // Reset mocks
    }


    /**
     * This test verifies that when loadVerbs() is called, the VerbViewModel correctly updates the state (verbsState) with the list of verbs returned from the repository.getVerbsSingle() method.
     */
    /*@Test
    fun `loadVerbs success`() = runTest {
        // Given
        val verbs = listOf(Verb(1, "come", listOf(PhrasalVerb(1, "come in", listOf()))))
        `when`(repository.getVerbsSingle()).thenReturn(Single.just(verbs)) //This simulates a successful API/database response using RxJava (Single).

        // When
        viewModel.loadVerbs()
        testDispatcher.scheduler.advanceUntilIdle() //ensures all coroutines complete execution before moving to assertions.

        // Then
        println("Verbs: "+viewModel.verbsState.value)
        assertEquals(verbs, viewModel.verbsState.value)
    }


    @Test
    fun `loadVerbs error`() = runTest {
        // Given
        val errorMessage = "Error loading verbs"
        `when`(repository.getVerbsSingle()).thenReturn(Single.error(RuntimeException(errorMessage))) //This simulates a failure API/database response.

        // When
        viewModel.loadVerbs()
        testDispatcher.scheduler.advanceUntilIdle() //ensures all coroutines complete execution before moving to assertions.

        // Then
        //assertEquals("Error loading verbs: $errorMessage", viewModel.error.value)
        println("Error: "+viewModel.error.value)
        assertTrue(viewModel.error.value is Throwable)

    }*/

}