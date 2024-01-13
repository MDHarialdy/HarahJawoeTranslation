package com.saintek.mdh.harahjawoetranslation.data.database.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")
class AppPref private  constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun isFirstTimeFalse(){
        dataStore.edit { Preferences ->
            Preferences[FIRST_TIME] = false
        }
    }

    suspend fun isFirstTimeLaunch(): Boolean {
        return  dataStore.data.first()[FIRST_TIME] ?: true
    }


    companion object {
        private val FIRST_TIME = booleanPreferencesKey("first")

        @Volatile
        var INSTANCE : AppPref? = null

        fun getInstance(dataStore: DataStore<Preferences>): AppPref {
            return INSTANCE ?: synchronized(this){
                val instance = AppPref(dataStore)
                INSTANCE = instance
                return  instance
            }
        }
    }
}