package com.natlwd.compose.data.remote

import com.natlwd.compose.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("/todos/{userId}")
    suspend fun getUser(
        @Path("userId") userId: String
    ): Response<UserResponse?>
}