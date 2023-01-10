package com.playground.doggies.data.local

import com.playground.doggies.domain.DoggiesLocalStore
import com.playground.doggies.domain.model.DogPicture
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DoggiesRoomStore @Inject constructor(
    private val doggiesDao: DoggiesDao
) : DoggiesLocalStore {

    override fun getFavorites(): Flow<List<DogPicture>> {
        return doggiesDao.getFavoritePics()
            .map { rows ->
                rows.map { item -> item.toDomain() }
            }
    }

    override suspend fun addFavorite(picture: DogPicture) {
        doggiesDao.insert(
            FavoritePictureEntity(
                url = picture.picture,
                breed = picture.breed
            )
        )
    }

    override suspend fun removeFavorite(picture: DogPicture) {
        doggiesDao.delete(
            FavoritePictureEntity(
                url = picture.picture,
                breed = picture.breed
            )
        )
    }
}

private fun FavoritePictureEntity.toDomain(): DogPicture {
    return DogPicture(
        breed = breed,
        picture = url,
        isFavorite = true
    )
}