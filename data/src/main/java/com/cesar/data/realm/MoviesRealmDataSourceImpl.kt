package com.cesar.data.realm

import com.cesar.domain.model.Movie
import com.cesar.domain.repository.MoviesRealmDataSource
import io.realm.Realm
import javax.inject.Inject

class MoviesRealmDataSourceImpl @Inject constructor() : MoviesRealmDataSource {

    override fun saveMovieByCategory(category: String, movies: List<Movie>) {
        val realm = Realm.getDefaultInstance()
        realm.use { realmInstance ->
            realmInstance.executeTransaction {
                movies.forEach {
                    realmInstance.copyToRealmOrUpdate(
                        MovieEntity(
                            it.popularity, it.voteCount, it.video,
                            it.posterPath, it.id, it.title, it.overview, it.releaseDate, category
                        )
                    )
                }
            }
        }
    }

    override fun getMoviesByCategory(category: String): List<Movie> {
        val realm = Realm.getDefaultInstance()
        val movieList = mutableListOf<Movie>()
        realm.use { RealmInstance ->
            val moviesRealmResult = RealmInstance.where(MovieEntity::class.java)
                .equalTo(MovieEntity.CATEGORY, category)
                .findAll()
            moviesRealmResult.forEach {
                movieList.add(
                    Movie(
                        it.popularity,
                        it.voteCount,
                        it.video,
                        it.posterPath,
                        it.id,
                        it.title,
                        it.overview,
                        it.releaseDate
                    )
                )
            }

        }
        return movieList
    }


}