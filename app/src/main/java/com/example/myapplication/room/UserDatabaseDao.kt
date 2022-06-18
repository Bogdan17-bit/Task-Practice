package com.example.myapplication.room

import androidx.room.*
import com.example.myapplication.models.database.UserDatabase
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDatabaseDao {

    @Query("SELECT * FROM user")
    suspend fun getAllUsers() : List<UserDatabase>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(vararg users : List<UserDatabase>)

    @Update
    suspend fun updateUsers(vararg users : List<UserDatabase>)

}