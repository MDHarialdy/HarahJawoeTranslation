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


}
