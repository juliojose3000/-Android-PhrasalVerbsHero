package com.loaizasoftware.phrasalverbshero

//import com.loaizasoftware.phrasalverbshero.presentation.viewmodel.VerbViewModelFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.loaizasoftware.phrasalverbshero.presentation.ui.home.HomeScreen
import com.loaizasoftware.phrasalverbshero.presentation.ui.theme.PhrasalVerbsHeroTheme
import com.loaizasoftware.phrasalverbshero.presentation.viewmodel.VerbViewModel
import timber.log.Timber
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    //When an object is annotated with @Inject it means that it will be provided by Dagger
    //verbViewModel is a dependency of the MainActivity class
    //The MainActivity class depends on verbViewModel to handle the data and UI logic

    @Inject
    lateinit var verbViewModel: VerbViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        // Get the application component and inject the MainActivity's dependencies
        (application as PhrasalVerbsHeroApplication).appComponent.inject(this)

        verbViewModel.error.observe(this) { errorMessage ->
            Timber.tag("MyTAG").e(errorMessage)
        }

        enableEdgeToEdge()
        setContent {
            PhrasalVerbsHeroTheme {
                HomeScreen(verbViewModel)
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