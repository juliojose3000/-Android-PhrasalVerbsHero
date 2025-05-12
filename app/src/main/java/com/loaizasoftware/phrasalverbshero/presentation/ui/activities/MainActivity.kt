package com.loaizasoftware.phrasalverbshero.presentation.ui.activities

//import com.loaizasoftware.phrasalverbshero.presentation.viewmodel.VerbViewModelFactory
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.StrictMode
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.loaizasoftware.core_ui.base.BaseActivity
import com.loaizasoftware.phrasalverbshero.BuildConfig
import com.loaizasoftware.phrasalverbshero.core.receiver.AirplaneModeReceiver
import com.loaizasoftware.phrasalverbshero.presentation.ui.screens.PhrasalVerbsScreen
import com.loaizasoftware.phrasalverbshero.presentation.ui.screens.VerbsScreen
import com.loaizasoftware.core_ui.theme.PhrasalVerbsHeroTheme
import com.loaizasoftware.phrasalverbshero.presentation.ui.screens.DefinitionsScreen
import com.loaizasoftware.phrasalverbshero.presentation.viewmodel.PhrasalVerbsViewModel
import com.loaizasoftware.phrasalverbshero.presentation.viewmodel.VerbViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint //Allows Dagger to inject dependencies into Android classes such as Activities, Fragments, and Services
class MainActivity: BaseActivity() {

    //When an object is annotated with @Inject it means that it will be provided by Dagger
    //verbViewModel is a dependency of the MainActivity class
    //The MainActivity class depends on verbViewModel to handle the data and UI logic
    //@Inject
    private val verbViewModel: VerbViewModel by viewModels()

    //@Inject
    private val phrasalVerbsViewModel: PhrasalVerbsViewModel by viewModels()

    private lateinit var receiver: AirplaneModeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //(application as PhrasalVerbsHeroApplication).appComponent.inject(this)// Get the application component and inject the MainActivity's dependencies

        enableEdgeToEdge()
        setContent {
            PhrasalVerbsHeroTheme {
                PhrasalVerbsApplication(verbViewModel, phrasalVerbsViewModel)
            }
        }

        initStrictMode()
        initReceivers()
        initObservables()

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver) // ✅ Important, always unregister the receivers
    }

    private fun initReceivers() {

        receiver = AirplaneModeReceiver()
        val filter = IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED) // Event

        registerReceiver(receiver, filter) // Register the receiver to receive the events

    }

    private fun initObservables() {

        /*verbViewModel.error.observe(this) { errorMessage ->
            showError(errorMessage)
        }*/

        CoroutineScope(Dispatchers.Main).launch {
            verbViewModel.events.collect { message ->
                showError(message)
            }
        }

        phrasalVerbsViewModel.error.observe(this) { errorMessage ->
            showError(errorMessage)
        }

    }

    /**
     * StrictMode is not required for LeakCanary to work, because LeakCanary automatically detects memory leaks in Activities, Fragments, and Views without additional configuration.
     *
     * However, using StrictMode together with LeakCanary can help detect more issues like:
     * - Leaked BroadcastReceiver or Service
     * - Incorrect disk or network operations on the main thread
     * - Leaked Cursor objects
     */
    private fun initStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectLeakedClosableObjects()
                    .detectActivityLeaks()
                    .penaltyLog()
                    .penaltyDeath()
                    .build()
            )
        }
    }


}


@Composable
fun PhrasalVerbsApplication(verbViewModel: VerbViewModel, phrasalVerbsViewModel: PhrasalVerbsViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "verbs_screen") {
        composable("verbs_screen") {

            //The loadPhrasalVerbs call is a side effect. It should not be called directly within
            //the composable. Use LaunchedEffect to ensure it's called only once when the composable
            //is first composed or when a specific key changes
            LaunchedEffect(key1 = true) {

                if(verbViewModel.filteredVerbs.value.isEmpty()) {
                    verbViewModel.loadVerbs()
                }

            }

            VerbsScreen(verbViewModel, navController)
        }
        composable(route = "phrasal_verbs/{verbId}", arguments = listOf(navArgument("verbId") {
            type = NavType.LongType
        })) {

            val verbId = it.arguments!!.getLong("verbId")
            val verbName = verbViewModel.getVerbById(verbId)!!.name

            //The loadPhrasalVerbs call is a side effect. It should not be called directly within
            //the composable. Use LaunchedEffect to ensure it's called only once when the composable
            //is first composed or when a specific key changes
            LaunchedEffect(key1 = verbId) {

                if(phrasalVerbsViewModel.selectedVerbId != verbId) {
                    phrasalVerbsViewModel.loadPhrasalVerbs(verbId)
                }

            }

            PhrasalVerbsScreen(
                viewModel = phrasalVerbsViewModel,
                verb = verbName,
                navHostController = navController,
                getString = { stringId ->
                    BaseActivity.getInstance().getString(stringId)
                },
                practiceBtnOnClick = {
                    BaseActivity.getInstance().showFunctionNotImplementedYetToast()
                },
                quizBtnOnClick = {
                    BaseActivity.getInstance().showFunctionNotImplementedYetToast()
                }
            )

        }


        composable(route = "phrasal_verbs/{phrasalVerbId}/definitions", arguments = listOf(navArgument("phrasalVerbId") {
            type = NavType.LongType
        })) {

            val phrasalVerbId = it.arguments!!.getLong("phrasalVerbId")
            val phrasalVerb = phrasalVerbsViewModel.getPhrasalVerbById(phrasalVerbId)!!

            //The loadPhrasalVerbs call is a side effect. It should not be called directly within
            //the composable. Use LaunchedEffect to ensure it's called only once when the composable
            //is first composed or when a specific key changes
            LaunchedEffect(key1 = phrasalVerbId) {

                if(phrasalVerbsViewModel.selectedPhrasalVerb?.id != phrasalVerbId) {
                    phrasalVerbsViewModel.loadPhrasalVerbDefinitions(phrasalVerb.id)
                }

            }

            DefinitionsScreen(phrasalVerbsViewModel, phrasalVerb, navController) { stringId ->
                BaseActivity.getInstance().getString(stringId)
            }
        }


    }
}


/*@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PhrasalVerbsHeroTheme {
        //HomeScreen(verbViewModel)
    }
}*/