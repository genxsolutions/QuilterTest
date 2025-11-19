package com.genxsol.quiltertest.core.network

import com.genxsol.quiltertest.core.network.dto.WantToReadResponseDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenLibraryService {

    @GET("people/mekBot/books/want-to-read.json")
    fun fetchWantToRead(
        @Query("page") page: Int = 1
    ): Single<WantToReadResponseDto>
}

