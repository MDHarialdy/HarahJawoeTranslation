package com.saintek.mdh.harahjawoetranslation.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saintek.mdh.harahjawoetranslation.data.database.HistoryEntity
import com.saintek.mdh.harahjawoetranslation.data.database.UserEntity
import com.saintek.mdh.harahjawoetranslation.data.repository.AppRepository
import kotlinx.coroutines.launch

class HistoryViewModel(private val appRepository: AppRepository): ViewModel() {

    private val _responseUser = MutableLiveData<UserEntity>()
    val responseUser: LiveData<UserEntity> = _responseUser
    fun getAllHistory(historyId: Int): LiveData<List<HistoryEntity>> = appRepository.getHistory(historyId)

    fun getUser(id: Int) {
        viewModelScope.launch {
            val response = appRepository.getUser(id)
            _responseUser.value = response
        }
    }
}