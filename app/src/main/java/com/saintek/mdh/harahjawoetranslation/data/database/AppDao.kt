package com.saintek.mdh.harahjawoetranslation.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHistory(result: HistoryEntity)

    @Query("SELECT * FROM historyentity where historyId = :historyId")
    fun getHistory(historyId: Int): LiveData<List<HistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInitUser(userEntity: UserEntity)

    @Query("SELECT * FROM userentity where id = :id")
    suspend fun getUser(id: Int): UserEntity

    @Query("UPDATE userentity SET name = :name, age = :age, city = :city WHERE id = :id")
    suspend fun updateUser(name: String, age: String, city: String, id: Int)
}
