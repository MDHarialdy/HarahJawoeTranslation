package com.saintek.mdh.harahjawoetranslation.ui.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saintek.mdh.harahjawoetranslation.data.database.UserEntity
import com.saintek.mdh.harahjawoetranslation.data.repository.AppRepository
import kotlinx.coroutines.launch

class OnBoardingViewModel(private val appRepository: AppRepository): ViewModel() {

    fun insertUserFirstTime(userEntity: UserEntity){
        viewModelScope.launch {
            appRepository.insertUserFirstime(userEntity)
        }
    }

    fun setFirstTimeLaunchToFalse() {
        appRepository.setFirstTimeLaunchToFalse()
    }

}