package com.swj.quicksearchplace.model

data class NidUserInfoResponse(
    val resultcode:String,
    val message:String,
    val response:NidUser
)

data class NidUser(
    val id:String,
    val email:String
)
