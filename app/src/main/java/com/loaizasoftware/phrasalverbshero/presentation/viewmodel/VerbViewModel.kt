package com.loaizasoftware.phrasalverbshero.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.loaizasoftware.core_ui.base.BaseViewModel
import com.loaizasoftware.phrasalverbshero.core.None
import com.loaizasoftware.phrasalverbshero.domain.model.Verb
import com.loaizasoftware.phrasalverbshero.domain.usecase.GetVerbsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
open class VerbViewModel @Inject constructor(private val getVerbsUseCase: GetVerbsUseCase) : BaseViewModel() {

    private val _verbsState = mutableStateOf(emptyList<Verb>())
    val verbsState: MutableState<List<Verb>> = _verbsState

    init {
        //loadVerbs()
    }
//9739982
    @SuppressLint("CheckResult")
    fun loadVerbs() {

        //Get verbs from API using RxJava
        getVerbsUseCase.run(None())
            .subscribeOn(Schedulers.io()) // Perform network operation on IO thread
            .observeOn(AndroidSchedulers.mainThread()) // Update UI on main thread
            .doOnSubscribe{
                isLoading.value = true  
            }
            .doFinally {
                isLoading.value = false 
            }
            .subscribe({
                verbsState.value = it
            }, {
                //error.value = it
                sendEvent("Error: ${it.message}")
                Timber.e(it.cause)
            })

        //Get verbs from API using Retrofit
        /*getVerbsUseCase?.execute()?.enqueue(object : Callback<List<Verb>> {
            override fun onResponse(call: Call<List<Verb>>, response: Response<List<Verb>>) {

                if (response.isSuccessful) {
                    verbsState.value = response.body() ?: emptyList()
                    Log.v("MyTAG", "${response.body()}")
                } else {
                    _error.value = "Error: ${response.code()}"
                }
            }

            override fun onFailure(call: Call<List<Verb>>, t: Throwable) {
                _error.value = "Network error: ${t.message}"
            }

        })*/

    }

    fun getVerbById(verbId: Long): Verb? {
        return verbsState.value.find { it.id == verbId }
    }

}