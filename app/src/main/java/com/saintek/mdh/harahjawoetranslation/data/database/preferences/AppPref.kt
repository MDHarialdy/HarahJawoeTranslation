package com.saintek.mdh.harahjawoetranslation.data.database.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.saintek.mdh.harahjawoetranslation.data.database.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")
class AppPref private  constructor(private val dataStore: DataStore<Preferences>) {
    suspend fun saveUser(user: UserEntity){
        dataStore.edit { Preferences ->
            Preferences[NAME_USER] = user.name
            Preferences[AGE_USER] = user.age
            Preferences[CITY_USER] = user.city
        }
    }

    suspend fun setUserForTheFirstTime(){
        dataStore.edit { Preferences ->
            Preferences[NAME_USER] = "USERMDH210705112"
            Preferences[AGE_USER] = 0
            Preferences[CITY_USER] = "Banda Aceh"
            Preferences[FIRST_TIME] = false
        }
    }

    fun getUserOnlyName(): Flow<String?> {
        val name = dataStore.data.map {
            it[NAME_USER]
        }
        return name
    }
    fun getUserComplete(): Flow<UserEntity> {
        return dataStore.data.map {pref ->
            UserEntity(
                pref[NAME_USER] ?: "",
                pref[AGE_USER] ?: 0,
                pref[CITY_USER] ?: ""
            )
        }
    }

    suspend fun isFirstTimeLaunch(): Boolean {
        return  dataStore.data.first()[FIRST_TIME] ?: true
    }

    suspend fun logOut(){
        dataStore.edit {
            it.clear()
        }
    }

    companion object {
        private val NAME_USER = stringPreferencesKey("name")
        private val AGE_USER = intPreferencesKey("age")
        private val CITY_USER = stringPreferencesKey("city")
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