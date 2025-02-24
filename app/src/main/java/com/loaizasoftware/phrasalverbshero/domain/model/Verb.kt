package com.loaizasoftware.phrasalverbshero.domain.model

data class Verb(
    val id: Long,
    val verb: String,
    val phrasalVerbs: ArrayList<PhrasalVerb> = ArrayList()
)