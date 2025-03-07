package com.loaizasoftware.phrasalverbshero.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loaizasoftware.phrasalverbshero.core.None
import com.loaizasoftware.phrasalverbshero.data.local.DummyData
import com.loaizasoftware.phrasalverbshero.domain.model.Verb
import com.loaizasoftware.phrasalverbshero.domain.usecase.GetVerbsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class VerbViewModel(private val getVerbsUseCase: GetVerbsUseCase? = null) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    val verbsState = mutableStateOf(emptyList<Verb>())

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading = _isLoading

    init {
        if(getVerbsUseCase == null) {
            verbsState.value = DummyData.getVerbs()
        }
    }

    @SuppressLint("CheckResult")
    fun loadVerbs() {

        //Get verbs from API using RxJava
        getVerbsUseCase!!.run(None())
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
                _error.value = it.message ?: "Unknown error"
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
}