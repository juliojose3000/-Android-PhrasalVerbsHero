package com.loaizasoftware.phrasalverbshero

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.loaizasoftware.phrasalverbshero.data.api.ApiClient
import com.loaizasoftware.phrasalverbshero.data.repository.VerbRepository
import com.loaizasoftware.phrasalverbshero.presentation.ui.home.HomeScreen
import com.loaizasoftware.phrasalverbshero.presentation.ui.theme.PhrasalVerbsHeroTheme
import com.loaizasoftware.phrasalverbshero.presentation.viewmodel.VerbViewModel
import com.loaizasoftware.phrasalverbshero.presentation.viewmodel.VerbViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var verbViewModel: VerbViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        ApiClient.getInstance().createRetrofit(this)

        val repository = VerbRepository(ApiClient.getInstance().retrofit)

        val factory = VerbViewModelFactory(repository)
        verbViewModel = ViewModelProvider(this, factory)[VerbViewModel::class.java]

        verbViewModel.error.observe(this) { errorMessage ->
            Log.e("MyTAG", errorMessage)
        }

        // Fetch verbs when activity starts
        verbViewModel.fetchVerbs()

        enableEdgeToEdge()
        setContent {
            PhrasalVerbsHeroTheme {
                HomeScreen(verbViewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PhrasalVerbsHeroTheme {
        //HomeScreen(verbViewModel)
    }
}