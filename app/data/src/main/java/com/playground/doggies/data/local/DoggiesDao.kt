package com.playground.doggies.data.local

import androidx.room.*
import com.playground.doggies.domain.model.Breed
import com.playground.doggies.domain.model.PictureUrl
import kotlinx.coroutines.flow.Flow

@Dao
abstract class DoggiesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(obj: FavoritePictureEntity) : Long

    @Delete
    abstract suspend fun delete(obj: FavoritePictureEntity)

    @Query("SELECT * FROM Pictures ORDER BY breed")
    abstract fun getFavoritePics():Flow<List<FavoritePictureEntity>>
}

@Entity(tableName = "Pictures")
data class FavoritePictureEntity(
    @PrimaryKey(autoGenerate = false)
    val url: PictureUrl,
    val breed: Breed
)