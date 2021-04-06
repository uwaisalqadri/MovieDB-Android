package com.uwaisalqadri.muvi_app.data.source.local

import androidx.room.TypeConverter
import com.uwaisalqadri.muvi_app.data.source.local.entity.GenreEntity
import com.uwaisalqadri.muvi_app.data.source.local.entity.MovieEntity
import com.uwaisalqadri.muvi_app.data.source.remote.response.GenreItem

/**
 * Created by Uwais Alqadri on April 07, 2021
 */
class Converters {

	@TypeConverter
	fun fromGenre(genre: GenreEntity): String {
		return genre.name
	}

	@TypeConverter
	fun toGenre(name: String): GenreEntity {
		return GenreEntity(0, name)
	}
}