package com.saintek.mdh.harahjawoetranslation.ui.scanner

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.saintek.mdh.harahjawoetranslation.data.database.HistoryEntity
import com.saintek.mdh.harahjawoetranslation.data.repository.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScannerViewModel(val appRepository: AppRepository) : ViewModel(){

    @SuppressLint("Recycle")
    fun insertHistory(imageUri: Uri, resultScan: String, contentResolver: ContentResolver){
        CoroutineScope(Dispatchers.IO).launch{
            try {
                val newImage = contentResolver.openInputStream(imageUri)?.readBytes()?.let { byteArray ->
                    HistoryEntity(
                        imageByteArray = byteArray,
                        historyId = 1,
                        result = resultScan,
                        accuration = 20,
                        id = 0
                    )
                }
                newImage.let {
                    if (it != null) {
                        appRepository.insertHistory(it)
                    } else {
                        Log.d("Scanner ViewModel", "Image Null")
                    }
                }
            } catch (e: Exception){
                println(e.stackTrace)
            }
        }
    }
}