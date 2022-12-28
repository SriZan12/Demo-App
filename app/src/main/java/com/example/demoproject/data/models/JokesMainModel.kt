package com.example.demoproject.data.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class JokesMainModel(

    @SerializedName("error")
    var error: Boolean? = null,

    @SerializedName("amount")
    val amount: Int? = null,

    @SerializedName("jokes")
    val jokesList: List<JokesSecondaryModel>? = null
) {

    @Entity(tableName = "JokesTable")
    data class JokesSecondaryModel(

        @PrimaryKey(autoGenerate = true)
        var id: Int? = null,

        @SerializedName("category")
        var Category: String? = null,

        @SerializedName("type")
        val type: String? = null,

        @SerializedName("setup")
        val setUp: String? = null,

        @SerializedName("delivery")
        val delivery: String? = null,


        @SerializedName("joke")
        val Jokes: String? = null,

        @SerializedName("safe")
        val safe: Boolean = false,

        @SerializedName("lang")
        val language: String? = null
    ) {
    }

}


