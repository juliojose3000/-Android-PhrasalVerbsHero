package com.loaizasoftware.phrasalverbshero.presentation.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.mutableStateOf
import com.loaizasoftware.phrasalverbshero.core.network.ApiResult
import com.loaizasoftware.phrasalverbshero.domain.model.Definition
import com.loaizasoftware.phrasalverbshero.domain.model.PhrasalVerb
import com.loaizasoftware.phrasalverbshero.domain.usecase.GetDefinitionsUseCase
import com.loaizasoftware.phrasalverbshero.domain.usecase.GetPhrasalVerbsByPart
import com.loaizasoftware.phrasalverbshero.domain.usecase.GetPhrasalVerbsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
open class PhrasalVerbsViewModel
@Inject constructor(
    private val getPhrasalVerbsUseCase: GetPhrasalVerbsUseCase,
    private val getDefinitionsUseCase: GetDefinitionsUseCase,
    private val getPhrasalVerbsByPart: GetPhrasalVerbsByPart
): BaseViewModel() {

    val phrasalVerbsState = mutableStateOf(emptyList<PhrasalVerb>())
    val phrasalVerbDefinitions = mutableStateOf(emptyList<Definition>())
    var selectedPhrasalVerb: PhrasalVerb? = null
    //var selectedVerbId: Long? = null
    var selectedPhrasalVerbFilter: String? = null

    val isLoadingPhrasalVerbs = mutableStateOf(false)
    val isLoadingDefinitions = mutableStateOf(false)

    fun loadPhrasalVerbs(verbId: Long) {
        //loadPhrasalVerbsNormal(verbId)
        loadPhrasalVerbsSafely(verbId)
    }

    @SuppressLint("CheckResult")
    fun loadPhrasalVerbsNormal(verbId: Long) {

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
                Timber.e(it)
            })

    }

    @SuppressLint("CheckResult")
    fun loadPhrasalVerbs(phrasalVerbPart: String) {

        selectedPhrasalVerbFilter = phrasalVerbPart

        getPhrasalVerbsByPart.run(phrasalVerbPart)
            .subscribeOn(Schedulers.io()) // Perform network operation on IO thread
            .observeOn(AndroidSchedulers.mainThread()) // Update UI on main thread
            .doOnSubscribe{
                isLoadingPhrasalVerbs.value = true
            }
            .doFinally {
                isLoadingPhrasalVerbs.value = false
            }
            .subscribe({
                phrasalVerbsState.value = it
            }, {
                error.value = it
                Timber.e(it)
            })

    }

    @SuppressLint("CheckResult")
    fun loadPhrasalVerbsSafely(verbId: Long) {

        //selectedVerbId = verbId

        getPhrasalVerbsUseCase.get(verbId)
            .subscribeOn(Schedulers.io()) // Perform network operation on IO thread
            .observeOn(AndroidSchedulers.mainThread()) // Update UI on main thread
            .doOnSubscribe{
                isLoadingPhrasalVerbs.value = true
            }
            .doFinally {
                isLoadingPhrasalVerbs.value = false
            }
            .subscribe({ result ->

                when (result) {
                    is ApiResult.Success -> phrasalVerbsState.value = result.data
                    is ApiResult.HttpError -> httpError.value = result
                    is ApiResult.NetworkError -> error.value = result.throwable
                    is ApiResult.UnknownError -> error.value = result.throwable
                }

            }, {
                error.value = it
                Timber.e(it)
            })

    }

    @SuppressLint("CheckResult")
    fun loadPhrasalVerbDefinitions(phrasalVerbId: Long) {

        selectedPhrasalVerb = getPhrasalVerbById(phrasalVerbId)

        getDefinitionsUseCase.run(phrasalVerbId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoadingDefinitions.value = true }
            .doFinally { isLoadingDefinitions.value = false }
            .subscribe({
                phrasalVerbDefinitions.value = it
            }, {
                error.value = it
                Timber.e(it)
            })

    }

    fun getPhrasalVerbById(phrasalVerbId: Long): PhrasalVerb? {

        return phrasalVerbsState.value.find { it.id == phrasalVerbId }

    }

}