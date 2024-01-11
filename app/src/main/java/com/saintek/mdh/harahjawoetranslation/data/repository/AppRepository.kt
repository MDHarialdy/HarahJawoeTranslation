package com.saintek.mdh.harahjawoetranslation.data.repository

import com.saintek.mdh.harahjawoetranslation.data.database.AppDao
import com.saintek.mdh.harahjawoetranslation.data.database.HistoryEntity

class AppRepository private constructor(
    private val appDao: AppDao,
){

    suspend fun insertHistory(history: HistoryEntity) = appDao.insertHistory(history)

    fun getHistory(imageId: Int) = appDao.getHistory(imageId)

    companion object {
        @Volatile
        private var INSTANCE: AppRepository? = null

        fun getInstance(
            appDao: AppDao
        ): AppRepository =
            INSTANCE ?: synchronized(this){
                INSTANCE?: AppRepository(appDao)
            }.also { INSTANCE = it}
    }
}