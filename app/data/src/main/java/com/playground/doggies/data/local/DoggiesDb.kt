package com.playground.doggies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.playground.doggies.domain.model.Breed
import com.playground.doggies.domain.model.PictureUrl

@Database(
    entities = [
        FavoritePictureEntity::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(DoggiesConverters::class)
abstract class DoggiesDb : RoomDatabase() {
    abstract fun doggiesDao(): DoggiesDao
}


class DoggiesConverters {
    @TypeConverter
    fun toPictureUrl(value: String): PictureUrl {
        return PictureUrl(value)
    }

    @TypeConverter
    fun fromPictureUrl(picUrl: PictureUrl): String {
        return picUrl.link
    }

    @TypeConverter
    fun toBreed(value: String): Breed {
        return if (value.contains(".")) {
            with(value.split(".")) {
                Breed(name = this[0], subBreed = this[1])
            }
        } else {
            Breed(value)
        }
    }

    @TypeConverter
    fun fromBreed(breed: Breed): String {
        return if (breed.subBreed.isNotEmpty()) {
            "${breed.name}.${breed.subBreed}"
        } else {
            breed.name
        }
    }
}