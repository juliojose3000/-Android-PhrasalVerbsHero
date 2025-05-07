package com.loaizasoftware.phrasalverbshero.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.loaizasoftware.phrasalverbshero.core.None
import com.loaizasoftware.phrasalverbshero.domain.model.Verb
import com.loaizasoftware.phrasalverbshero.domain.usecase.GetVerbsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
open class VerbViewModel @Inject constructor(private val getVerbsUseCase: GetVerbsUseCase) :
    BaseViewModel() {

    private val _verbsState = mutableStateOf(emptyList<Verb>())
    val verbsState: MutableState<List<Verb>> = _verbsState
    val onErrorResponse: MutableState<String?> = mutableStateOf(null)

    fun loadVerbs() {
        loadVerbsUsingRxJava()
        //loadVerbsUsingRetrofit()
    }

    @SuppressLint("CheckResult")
    private fun loadVerbsUsingRxJava() {

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
                onErrorResponse.value = it.message
                sendEvent("Error: ${it.message}")
                Timber.e(it.cause)
            })

    }

    private fun loadVerbsUsingRetrofit() {

        //Get verbs from API using Retrofit
        getVerbsUseCase.execute().enqueue(object : Callback<List<Verb>> {

            init {
                isLoading.value = true
            }

            override fun onResponse(call: Call<List<Verb>>, response: Response<List<Verb>>) {

                isLoading.value = false

                if (response.isSuccessful) {
                    verbsState.value = response.body() ?: emptyList()
                } else {
                    sendEvent("Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Verb>>, t: Throwable) {
                isLoading.value = true
                sendEvent("Error: ${t.message}")
            }

        })

    }

    fun getVerbById(verbId: Long): Verb? {
        return verbsState.value.find { it.id == verbId }
    }

}