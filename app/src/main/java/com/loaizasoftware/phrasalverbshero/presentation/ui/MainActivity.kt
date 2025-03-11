package com.loaizasoftware.phrasalverbshero.presentation.ui

//import com.loaizasoftware.phrasalverbshero.presentation.viewmodel.VerbViewModelFactory
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.loaizasoftware.phrasalverbshero.PhrasalVerbsHeroApplication
import com.loaizasoftware.phrasalverbshero.presentation.ui.core.BaseActivity
import com.loaizasoftware.phrasalverbshero.presentation.ui.home.HomeScreen
import com.loaizasoftware.phrasalverbshero.presentation.ui.theme.PhrasalVerbsHeroTheme
import com.loaizasoftware.phrasalverbshero.presentation.viewmodel.VerbViewModel
import timber.log.Timber
import javax.inject.Inject

class MainActivity: BaseActivity() {

    //When an object is annotated with @Inject it means that it will be provided by Dagger
    //verbViewModel is a dependency of the MainActivity class
    //The MainActivity class depends on verbViewModel to handle the data and UI logic
    @Inject
    lateinit var verbViewModel: VerbViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as PhrasalVerbsHeroApplication).appComponent.inject(this)// Get the application component and inject the MainActivity's dependencies

        enableEdgeToEdge()
        setContent {
            PhrasalVerbsHeroTheme {
                HomeScreen(verbViewModel)
            }
        }

        initObservables()

    }

    private fun initObservables() {

        verbViewModel.error.observe(this) { errorMessage ->
            showError(errorMessage)
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