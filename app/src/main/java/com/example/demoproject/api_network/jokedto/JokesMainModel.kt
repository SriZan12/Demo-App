package com.example.demoproject.api_network.jokedto


import com.google.gson.annotations.SerializedName


data class JokesMainModel(

    @SerializedName("error")
    var error: Boolean? = null,

    @SerializedName("amount")
    val amount: Int? = null,

    @SerializedName("jokes")
    val jokesList: List<JokesSecondaryModel>? = null
) {

}


