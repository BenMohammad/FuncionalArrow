package com.benmohammad.funcionalarrow.data.remote

import com.benmohammad.funcionalarrow.GatewayApiService
import com.benmohammad.funcionalarrow.data.model.Entity
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

internal interface GatewayApi {

    @GET("lights/{roomId}")
    suspend fun getLights(@Path("roomId") roomId : String): retrofit2.Response<Entity>

    @GET("system")
    suspend fun getSystemDetails(): Response<Entity>

    companion object {

        fun create(baseUrl : String) : GatewayApi = Retrofit.Builder()
            .baseUrl(baseUrl)
            .build()
            .create(GatewayApi::class.java)
    }
}