package com.saintek.mdh.harahjawoetranslation.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.saintek.mdh.harahjawoetranslation.data.database.HistoryEntity
import com.saintek.mdh.harahjawoetranslation.data.repository.AppRepository

class HistoryViewModel(val appRepository: AppRepository): ViewModel() {
    fun getAllHistory(historyId: Int): LiveData<List<HistoryEntity>> = appRepository.getHistory(historyId)

    fun getUser(id: Int){

    }
}