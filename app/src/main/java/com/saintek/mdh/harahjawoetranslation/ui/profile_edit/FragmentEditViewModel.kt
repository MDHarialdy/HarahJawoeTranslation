package com.saintek.mdh.harahjawoetranslation.ui.profile_edit

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saintek.mdh.harahjawoetranslation.data.repository.AppRepository
import kotlinx.coroutines.launch

class FragmentEditViewModel(val appRepository: AppRepository) : ViewModel() {

    fun updateUser(name: String, age: Int, city: String, id: Int){
        viewModelScope.launch{
            try {
                appRepository.updateUser(name, age, city, id)
            }catch (e: Error) {
                Log.d("EditViewModel", e.message.toString())
            }
        }
    }
}