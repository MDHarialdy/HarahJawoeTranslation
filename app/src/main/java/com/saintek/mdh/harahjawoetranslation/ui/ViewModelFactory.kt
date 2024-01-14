package com.saintek.mdh.harahjawoetranslation.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saintek.mdh.harahjawoetranslation.data.di.DataInjection
import com.saintek.mdh.harahjawoetranslation.data.repository.AppRepository
import com.saintek.mdh.harahjawoetranslation.ui.history.HistoryViewModel
import com.saintek.mdh.harahjawoetranslation.ui.onboarding.OnBoardingViewModel
import com.saintek.mdh.harahjawoetranslation.ui.profile.ProfileViewModel
import com.saintek.mdh.harahjawoetranslation.ui.profile_edit.FragmentEditViewModel
import com.saintek.mdh.harahjawoetranslation.ui.scanner.ScannerViewModel
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: AppRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(ScannerViewModel::class.java) -> {
                ScannerViewModel(repository) as T
            }
            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> {
                HistoryViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(repository) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }
            modelClass.isAssignableFrom(FragmentEditViewModel::class.java) -> {
                FragmentEditViewModel(repository) as T
            }
            modelClass.isAssignableFrom(OnBoardingViewModel::class.java) -> {
                OnBoardingViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel Class: "+ modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null){
                synchronized(this){
                    INSTANCE = ViewModelFactory(DataInjection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}