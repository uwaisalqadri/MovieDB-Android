package com.uwaisalqadri.muvi_app.data.source.local

import androidx.room.*
import com.uwaisalqadri.muvi_app.data.source.local.entity.MovieEntity
import com.uwaisalqadri.muvi_app.domain.model.Movie
import io.reactivex.Single

/**
 * Created by Uwais Alqadri on April 05, 2021
 */
@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)

    @Query("SELECT * FROM favorite_movie ORDER BY id=:movieId")
    fun getMovieById(movieId: String): Single<List<MovieEntity>>

    @Query("SELECT * FROM favorite_movie")
    fun getFavoriteMovies(): Single<List<MovieEntity>>
}