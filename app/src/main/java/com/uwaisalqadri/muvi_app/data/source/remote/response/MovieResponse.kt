package com.uwaisalqadri.muvi_app.data.source.remote.response

data class MovieResponse(
    val page: Int,
    val results: List<MovieItem>,
)