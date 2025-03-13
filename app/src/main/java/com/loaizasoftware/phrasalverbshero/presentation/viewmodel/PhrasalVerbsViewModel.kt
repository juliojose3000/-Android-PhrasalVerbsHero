package com.loaizasoftware.phrasalverbshero.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import com.loaizasoftware.phrasalverbshero.domain.model.PhrasalVerb
import com.loaizasoftware.phrasalverbshero.domain.usecase.GetPhrasalVerbsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhrasalVerbsViewModel @Inject constructor(private val getPhrasalVerbsUseCase: GetPhrasalVerbsUseCase): BaseViewModel() {

    val phrasalVerbsState = mutableStateOf(emptyList<PhrasalVerb>())

    @SuppressLint("CheckResult")
    fun loadPhrasalVerbs(verbId: Long) {

        getPhrasalVerbsUseCase.run(verbId)
            .subscribeOn(Schedulers.io()) // Perform network operation on IO thread
            .observeOn(AndroidSchedulers.mainThread()) // Update UI on main thread
            .doOnSubscribe{
                isLoading.value = true
            }
            .doFinally {
                isLoading.value = false
            }
            .subscribe({
                phrasalVerbsState.value = it
            }, {
                error.value = it
                Timber.e(it.cause)
            })

    }

}