package com.uwaisalqadri.muvi_app.data.mapper.response

import com.uwaisalqadri.muvi_app.data.mapper.BaseMapper
import com.uwaisalqadri.muvi_app.data.source.remote.response.MovieItem
import com.uwaisalqadri.muvi_app.domain.model.Movie

/**
 * Created by Uwais Alqadri on April 05, 2021
 */
class MovieResponseMapper : BaseMapper<MovieItem, Movie> {
    override fun mapToDomain(model: MovieItem): Movie {
        return Movie(
            model.adult,
            model.backdrop_path,
            model.genres,
            model.id,
            model.original_language,
            model.original_title,
            model.overview,
            model.popularity,
            model.poster_path,
            model.release_date,
            model.title,
            model.video,
            model.vote_average,
            model.vote_count
        )
    }

    override fun mapToModel(domain: Movie): MovieItem {
        return MovieItem(
            domain.adult,
            domain.backdrop_path,
            domain.genres,
            domain.id,
            domain.original_language,
            domain.original_title,
            domain.overview,
            domain.popularity,
            domain.poster_path,
            domain.release_date,
            domain.title,
            domain.video,
            domain.vote_average,
            domain.vote_count
        )
    }
}