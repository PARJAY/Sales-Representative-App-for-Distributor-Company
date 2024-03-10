package com.example.merchandiserappjetpackcompose.presentation.reportdetail

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "selling_item_kit")

class SellingKitProductListDataStore private constructor(private val dataStore: DataStore<Preferences>) {

    private val SELLING_KIT_PRODUCT_LIST_KEY = stringPreferencesKey("selling_item_kit")

    suspend fun saveSellingItemKit(kit: List<Int>) {
        dataStore.edit { preferences ->
            val json = Json.encodeToString(kit)
            preferences[SELLING_KIT_PRODUCT_LIST_KEY] = json
        }
    }

    fun getSellingItemKit(): Flow<List<Int>?> {
        return dataStore.data.map { preferences ->
            val json = preferences[SELLING_KIT_PRODUCT_LIST_KEY] ?: return@map null
            Json.decodeFromString<List<Int>>(json)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: SellingKitProductListDataStore? = null

        fun getInstance(dataStore: DataStore<Preferences>): SellingKitProductListDataStore {
            return INSTANCE ?: synchronized(this) {
                val instance = SellingKitProductListDataStore(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}