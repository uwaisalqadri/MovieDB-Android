package com.uwaisalqadri.muvi_app.di

import android.content.Context
import androidx.room.Room
import com.uwaisalqadri.muvi_app.data.repository.MovieRepositoryImpl
import com.uwaisalqadri.muvi_app.data.source.local.AppDatabase
import com.uwaisalqadri.muvi_app.data.source.remote.ApiService
import com.uwaisalqadri.muvi_app.domain.repository.MovieRepository
import com.uwaisalqadri.muvi_app.domain.usecase.MovieInteractor
import com.uwaisalqadri.muvi_app.domain.usecase.MovieUseCase
import com.uwaisalqadri.muvi_app.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Uwais Alqadri on April 05, 2021
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApi(): ApiService {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        Constants.DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideDao(db: AppDatabase) = db.movieDao()

    @Singleton
    @Provides
    fun provideRepository(apiService: ApiService): MovieRepository {
        return MovieRepositoryImpl(apiService)
    }

    @Singleton
    @Provides
    fun provideUseCase(movieRepository: MovieRepository): MovieUseCase {
        return MovieInteractor(movieRepository)
    }
}













