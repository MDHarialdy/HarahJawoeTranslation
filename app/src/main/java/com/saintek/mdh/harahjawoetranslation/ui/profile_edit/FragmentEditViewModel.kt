package com.saintek.mdh.harahjawoetranslation.ui.profile_edit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saintek.mdh.harahjawoetranslation.data.database.UserEntity
import com.saintek.mdh.harahjawoetranslation.data.repository.AppRepository
import kotlinx.coroutines.launch

class FragmentEditViewModel(val appRepository: AppRepository) : ViewModel() {

    fun updateUser(name: String, age: String, city: String, id: Int){
        viewModelScope.launch{
            try {
                appRepository.updateUser(name, age, city, id)
            }catch (e: Error) {
                Log.d("EditViewModel", e.message.toString())
            }
        }
    }

    private val _responseUser = MutableLiveData<UserEntity>()
    val responseUser: LiveData<UserEntity> = _responseUser
    fun getUser(id: Int){
        viewModelScope.launch {
            val response = appRepository.getUser(id)
            _responseUser.value = response
        }
    }
}