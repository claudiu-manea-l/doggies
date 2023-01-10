package com.playground.doggies.data.local

import androidx.room.Delete
import androidx.room.Insert

interface BaseDao<T> {

    @Insert
    suspend fun insert(obj: T) : Long

    @Insert
    suspend fun insertAll(vararg obj: T) : List<Long>

    @Insert
    suspend fun insertAll(objs: MutableList<T>) : List<Long>

    @Delete
    suspend fun delete(obj: T)
}
