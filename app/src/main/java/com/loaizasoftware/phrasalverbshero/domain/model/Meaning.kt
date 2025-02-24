package com.loaizasoftware.phrasalverbshero.domain.model

data class Meaning(
    val id: Long,
    val meaning: String,
    val examples: ArrayList<Example> = ArrayList()
)