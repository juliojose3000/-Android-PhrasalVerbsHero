package com.loaizasoftware.phrasalverbshero.domain.usecase

import com.loaizasoftware.phrasalverbshero.data.repository.FakePhrasalVerbRepositoryImpl
import com.loaizasoftware.phrasalverbshero.domain.model.Meaning
import com.loaizasoftware.phrasalverbshero.presentation.ui.verbs.FakeApiService
import io.reactivex.Single

class FakeGetMeaningsUseCase : GetMeaningsUseCase(
    FakePhrasalVerbRepositoryImpl(
        FakeApiService()
    )
) {
    override fun run(params: Long): Single<List<Meaning>> {
        return Single.just(emptyList()) // Not used in UI test
    }
}