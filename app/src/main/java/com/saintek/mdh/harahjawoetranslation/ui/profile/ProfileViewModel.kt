package com.saintek.mdh.harahjawoetranslation.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saintek.mdh.harahjawoetranslation.data.database.UserEntity
import com.saintek.mdh.harahjawoetranslation.data.repository.AppRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val appRepository: AppRepository): ViewModel() {

    fun getUserComplete(){
        return appRepository.getUserComplete()
    }

    fun updateUser(user: UserEntity){
        viewModelScope.launch {
            appRepository.updateUser(user)
        }
    }
}