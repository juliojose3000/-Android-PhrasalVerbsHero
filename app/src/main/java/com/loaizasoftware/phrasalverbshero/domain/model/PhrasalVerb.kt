package com.loaizasoftware.phrasalverbshero.domain.model

data class PhrasalVerb(
    val id: Long,
    val phrasalVerb: String,
    val meanings: ArrayList<Meaning> = ArrayList()
)