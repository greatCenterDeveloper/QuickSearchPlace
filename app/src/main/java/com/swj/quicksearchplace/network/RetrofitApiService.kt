package com.swj.quicksearchplace.network

import com.swj.quicksearchplace.model.KakaoSearchPlaceResponse
import com.swj.quicksearchplace.model.NidUserInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitApiService {
    // 네아로 사용자 정보 .API
    @GET("/v1/nid/me")
    fun getNidUserInfo(@Header("Authorization") authorization:String):Call<NidUserInfoResponse>

    // 카카오 키워드 검색 API
    @Headers("Authorization: KakaoAK ebf2837b4fbe78ead8ea627515a28ff7")
    @GET("/v2/local/search/keyword.json")
    fun searchPlace(@Query("query") query:String,
                    @Query("y") latitude:String,
                    @Query("x") longitude:String
    ):Call<KakaoSearchPlaceResponse>
}