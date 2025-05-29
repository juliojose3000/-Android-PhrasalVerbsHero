package com.loaizasoftware.phrasalverbshero.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.loaizasoftware.phrasalverbshero.core.None
import com.loaizasoftware.phrasalverbshero.domain.model.Verb
import com.loaizasoftware.phrasalverbshero.domain.usecase.GetPrepsAdverbsUseCase
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
open class MainViewModel @Inject constructor(
    private val getVerbsUseCase: GetVerbsUseCase,
    private val getPrepsAdverbsUseCase: GetPrepsAdverbsUseCase
) :
    BaseViewModel() {

    //private val _verbsState = mutableStateOf(emptyList<Verb>())
    private val _cardsTextState = mutableStateOf(emptyList<String>())

    private val _filteredCards = mutableStateOf(emptyList<String>())

    val filteredVerbs: MutableState<List<String>> = _filteredCards

    val onErrorResponse: MutableState<String?> = mutableStateOf(null)
    val searchVerb: MutableState<String> = mutableStateOf("")

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
                //_verbsState.value = it
                filteredVerbs.value = it.map { verb -> verb.name }
            }, {
                //error.value = it
                onErrorResponse.value = it.message
                sendEvent("Error: ${it.message}")
                Timber.e(it.cause)
            })

    }

    @SuppressLint("CheckResult")
    fun loadPrepsAdverbs() {

        getPrepsAdverbsUseCase.run(None())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.value = true }
            .doFinally { isLoading.value = false }
            .subscribe({
                _cardsTextState.value = it
                filteredVerbs.value = it
            }, {
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
                    //_verbsState.value = response.body() ?: emptyList()
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

    /*fun getVerbById(verbId: Long): Verb? {
        return _verbsState.value.find { it.id == verbId }
    }*/

    fun searchVerbs(searchedVerb: String) {
        searchVerb.value = searchedVerb
        filteredVerbs.value = _cardsTextState.value.filter { it.contains(searchedVerb, ignoreCase = true) }
    }

}