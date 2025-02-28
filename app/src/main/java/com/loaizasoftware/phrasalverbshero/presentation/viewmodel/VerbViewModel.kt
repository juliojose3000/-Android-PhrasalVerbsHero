package com.loaizasoftware.phrasalverbshero.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loaizasoftware.phrasalverbshero.data.local.DummyData
import com.loaizasoftware.phrasalverbshero.domain.model.Verb
import com.loaizasoftware.phrasalverbshero.domain.usecase.GetVerbsUseCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VerbViewModel(private val getVerbsUseCase: GetVerbsUseCase? = null) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    val verbsState = mutableStateOf(emptyList<Verb>())

    init {
        if(getVerbsUseCase == null) {
            verbsState.value = DummyData.getVerbs()
        }
    }

    fun fetchVerbs() {

        getVerbsUseCase?.execute()?.enqueue(object : Callback<List<Verb>> {
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
        })
    }
}