package com.loaizasoftware.phrasalverbshero.domain.model

import com.squareup.moshi.Json

//import com.google.gson.annotations.SerializedName

data class Verb(
    val id: Long,
    //@SerializedName("verb") val name: String,
    @Json(name = "verb") val name: String,
    val phrasalVerbs: List<PhrasalVerb>
)