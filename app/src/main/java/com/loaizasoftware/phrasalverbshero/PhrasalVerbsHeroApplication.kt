package com.loaizasoftware.phrasalverbshero

import android.app.Application
//import com.loaizasoftware.phrasalverbshero.di.component.AppComponent
// com.loaizasoftware.phrasalverbshero.di.component.DaggerAppComponent
import com.loaizasoftware.phrasalverbshero.di.module.AppModule
import dagger.hilt.android.HiltAndroidApp

/**
 * The Application class for the Phrasal Verbs Hero app.
 *
 * This class is responsible for initializing Dagger's dependency injection
 * framework at the application level. It creates and holds a reference to the
 * `AppComponent`, which provides dependencies to the rest of the application.
 *
 * ## Purpose of Dagger in this Class
 * - **Centralized Dependency Management**: Ensures that dependencies are provided
 *   from a single source, avoiding manual instantiation across different parts of the app.
 * - **Application-Wide Dependencies**: Provides shared instances of dependencies
 *   such as repositories, database instances, and networking components.
 * - **Loose Coupling**: Improves modularity and makes testing easier by allowing
 *   dependencies to be injected rather than manually created.
 *
 * ## Implementation Details
 * - Uses Dagger to initialize the `AppComponent`, which is built using the
 *   `AppModule`.
 * - The `AppModule` is passed the application context to provide app-scoped
 *   dependencies.
 * - The `appComponent` can be accessed throughout the app to inject dependencies
 *   into activities, fragments, and other components.
 *
 * Example usage in an Activity:
 * ```kotlin
 * class MainActivity : AppCompatActivity() {
 *     @Inject lateinit var myDependency: MyDependency
 *
 *     override fun onCreate(savedInstanceState: Bundle?) {
 *         (application as PhrasalVerbsHeroApplication).appComponent.inject(this)
 *         super.onCreate(savedInstanceState)
 *     }
 * }
 * ```
 */

@HiltAndroidApp
class PhrasalVerbsHeroApplication : Application() {

    /**
     * The Dagger component that provides dependencies to the application.
     */
    //lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        // Initialize Dagger component and provide the application context
        /*appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this)) // Pass the application context here
            .build()*/
    }
}
