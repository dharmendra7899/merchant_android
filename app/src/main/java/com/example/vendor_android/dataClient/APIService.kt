package com.example.vendor_android.dataClient

import okhttp3.MultipartBody
import retrofit2.http.*

interface ApiService {

    @GET
    suspend fun <T> get(@Url url: String): T

    // POST request with a generic return type T
    @POST
    suspend fun <T> post(@Url url: String, @Body body: Any): T

    // PUT request with a generic return type T
    @PUT
    suspend fun <T> put(@Url url: String, @Body body: Any): T

    // DELETE request with a generic return type T
    @DELETE
    suspend fun <T> delete(@Url url: String): T

    // PATCH request with a generic return type T
    @PATCH
    suspend fun <T> patch(@Url url: String, @Body body: Any): T

    // Multipart file upload with a generic return type T
    @Multipart
    @POST
    suspend fun <T> uploadFile(@Url url: String, @Part part: MultipartBody.Part): T
}
