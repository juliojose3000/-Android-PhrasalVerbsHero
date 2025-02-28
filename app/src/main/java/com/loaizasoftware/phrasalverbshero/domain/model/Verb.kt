package com.loaizasoftware.phrasalverbshero.domain.model

import com.google.gson.annotations.SerializedName

data class Verb(
    val id: Long,
    @SerializedName("verb") val name: String,
    val phrasalVerbs: ArrayList<PhrasalVerb> = ArrayList()
)