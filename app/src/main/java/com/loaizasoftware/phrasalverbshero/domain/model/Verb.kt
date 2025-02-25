package com.loaizasoftware.phrasalverbshero.domain.model

data class Verb(
    val id: Long,
    val name: String,
    val phrasalVerbs: ArrayList<PhrasalVerb> = ArrayList()
)