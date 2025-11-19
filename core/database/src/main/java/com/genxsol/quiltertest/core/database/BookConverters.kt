package com.genxsol.quiltertest.core.database

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class BookConverters {
    private val moshi = Moshi.Builder().build()
    private val listType = Types.newParameterizedType(List::class.java, String::class.java)
    private val adapter = moshi.adapter<List<String>>(listType)

    @TypeConverter
    fun fromJson(json: String?): List<String> {
        if (json.isNullOrBlank()) return emptyList()
        return adapter.fromJson(json).orEmpty()
    }

    @TypeConverter
    fun toJson(values: List<String>?): String {
        return adapter.toJson(values.orEmpty())
    }
}

