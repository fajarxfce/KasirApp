package com.fajar.template.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ExampleResponse(
    @field:SerializedName("code")
    val code: Int,

    @field:SerializedName("data")
    val data: Data,

    @field:SerializedName("message")
    val message: String
)
data class Data(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("description")
    val description: String
)