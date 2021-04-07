package com.uwaisalqadri.muvi_app.data.source.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.uwaisalqadri.muvi_app.data.source.remote.response.GenreItem

/**
 * Created by Uwais Alqadri on April 07, 2021
 */
class GenreConverters {

	@TypeConverter
	fun fromGenre(genres: List<GenreItem>): String {
		return Gson().toJson(genres)
	}

	@TypeConverter
	fun toGenre(name: String): List<GenreItem> {
		val listType = object : TypeToken<GenreItem>() {}.type
		return Gson().fromJson(name, listType)
	}
}