package com.saintek.mdh.harahjawoetranslation.ui

import androidx.lifecycle.ViewModel
import com.saintek.mdh.harahjawoetranslation.data.repository.AppRepository

class MainViewModel(private val appRepository: AppRepository): ViewModel() {

    fun checkIsFirstTimeLaunch(): Boolean {
        return appRepository.checkIsFirstTimeLaunch()
    }

}