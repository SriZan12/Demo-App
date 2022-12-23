package com.example.demoproject.api_network.jokedto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

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