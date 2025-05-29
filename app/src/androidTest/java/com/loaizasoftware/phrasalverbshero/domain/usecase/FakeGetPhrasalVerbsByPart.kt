package com.loaizasoftware.phrasalverbshero.domain.usecase

import com.loaizasoftware.phrasalverbshero.data.repository.FakePhrasalVerbRepositoryImpl
import com.loaizasoftware.phrasalverbshero.domain.model.Definition
import com.loaizasoftware.phrasalverbshero.domain.model.PhrasalVerb
import com.loaizasoftware.phrasalverbshero.presentation.ui.verbs.FakeApiService
import io.reactivex.Single

class FakeGetPhrasalVerbsByPart : GetPhrasalVerbsByPart(
    FakePhrasalVerbRepositoryImpl(
        FakeApiService()
    )
) {
    override fun run(params: String): Single<List<PhrasalVerb>> {
        return Single.just(emptyList()) // Not used in UI test)
    }
}