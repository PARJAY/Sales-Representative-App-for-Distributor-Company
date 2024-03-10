package com.example.merchandiserappjetpackcompose.presentation.mainmenu

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "sales_name")

class SalesNameDataStoreManager private constructor(private val dataStore: DataStore<Preferences>) {

    private val USERNAME_KEY = stringPreferencesKey("username")

    fun getUsername(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[USERNAME_KEY] ?: ""
        }
    }

    suspend fun saveUsername(username: String) {
        dataStore.edit { preferences ->
            preferences[USERNAME_KEY] = username
        }

        dataStore.edit {  }
    }

    companion object {
        @Volatile
        private var INSTANCE: SalesNameDataStoreManager? = null

        fun getInstance(dataStore: DataStore<Preferences>): SalesNameDataStoreManager {
            return INSTANCE ?: synchronized(this) {
                val instance = SalesNameDataStoreManager(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}