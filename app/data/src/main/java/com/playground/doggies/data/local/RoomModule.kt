package com.playground.doggies.data.local

import android.content.Context
import androidx.room.Room
import com.playground.doggies.domain.DoggiesLocalStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RoomModule {

    @Binds
    abstract fun provideLocalStore(localStore: DoggiesRoomStore): DoggiesLocalStore

    companion object {
        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext context: Context): DoggiesDb {
            return Room.databaseBuilder(context, DoggiesDb::class.java, "Doggies")
                .fallbackToDestructiveMigration()
                .build()
        }

        @Provides
        fun provideDoggiesDao(db: DoggiesDb): DoggiesDao {
            return db.doggiesDao()
        }
    }
}