package com.saintek.mdh.harahjawoetranslation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saintek.mdh.harahjawoetranslation.data.database.UserEntity
import com.saintek.mdh.harahjawoetranslation.data.repository.AppRepository
import kotlinx.coroutines.launch

class MainViewModel(private val appRepository: AppRepository): ViewModel() {

    val _isFirstTimeLaunch = MutableLiveData<Boolean>()
    val isFirstTimeLaunch: LiveData<Boolean> = _isFirstTimeLaunch

    fun setUserFirstTime(userEntity: UserEntity){
        viewModelScope.launch {
            appRepository.isFirstTimeFalse()
            appRepository.insertUserFirstime(userEntity)
        }
    }
    fun checkFirstTimeLaunch() {
        viewModelScope.launch{
            val isFirstTime = appRepository.isFirstTime()
            _isFirstTimeLaunch.value = isFirstTime
        }
    }

}