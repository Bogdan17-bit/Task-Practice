package com.example.myapplication.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.models.database.UserDatabase
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDatabaseDao {

    @Query("SELECT * FROM user_table")
    fun getAllUsers() : LiveData<List<UserDatabase>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user : UserDatabase)

    //@Update
    //suspend fun updateUsers(vararg users : List<UserDatabase>)

}