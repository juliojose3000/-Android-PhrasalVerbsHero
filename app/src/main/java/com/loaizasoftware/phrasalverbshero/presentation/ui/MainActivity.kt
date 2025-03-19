package com.loaizasoftware.phrasalverbshero.presentation.ui

//import com.loaizasoftware.phrasalverbshero.presentation.viewmodel.VerbViewModelFactory
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.loaizasoftware.phrasalverbshero.PhrasalVerbsHeroApplication
import com.loaizasoftware.phrasalverbshero.presentation.ui.core.BaseActivity
import com.loaizasoftware.phrasalverbshero.presentation.ui.phrasalverbs.PhrasalVerbsScreen
import com.loaizasoftware.phrasalverbshero.presentation.ui.verbs.HomeScreen
import com.loaizasoftware.phrasalverbshero.presentation.ui.theme.PhrasalVerbsHeroTheme
import com.loaizasoftware.phrasalverbshero.presentation.viewmodel.PhrasalVerbsViewModel
import com.loaizasoftware.phrasalverbshero.presentation.viewmodel.VerbViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

class MainActivity: BaseActivity() {

    //When an object is annotated with @Inject it means that it will be provided by Dagger
    //verbViewModel is a dependency of the MainActivity class
    //The MainActivity class depends on verbViewModel to handle the data and UI logic
    @Inject
    lateinit var verbViewModel: VerbViewModel

    @Inject
    lateinit var phrasalVerbsViewModel: PhrasalVerbsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as PhrasalVerbsHeroApplication).appComponent.inject(this)// Get the application component and inject the MainActivity's dependencies

        enableEdgeToEdge()
        setContent {
            PhrasalVerbsHeroTheme {
                PhrasalVerbsApplication(verbViewModel, phrasalVerbsViewModel)
            }
        }

        initObservables()

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
                verbViewModel.loadVerbs()
            }

            HomeScreen(verbViewModel, navController)
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
                phrasalVerbsViewModel.loadPhrasalVerbs(verbId)
            }

            PhrasalVerbsScreen(phrasalVerbsViewModel, verbName, navController) { stringId ->
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