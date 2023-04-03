package com.swj.quicksearchplace.network

import com.swj.quicksearchplace.model.NidUserInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface RetrofitApiService {
    // 네아로 사용자 정보 .API
    @GET("/v1/nid/me")
    fun getNidUserInfo(@Header("Authorization") authorization:String):Call<NidUserInfoResponse>
}