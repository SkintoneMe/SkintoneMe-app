package com.skintone.me.database

import android.content.Context
import android.provider.ContactsContract.DisplayNameSources.EMAIL
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_preferences")

class PreferenceManager private constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun login() {
        dataStore.edit { preferences ->
            preferences[STATE_KEY] = true
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.clear()
            preferences[USERNAME] = ""
            preferences[USER_TOKEN] = ""
            preferences[STATE_KEY] = false
            preferences[IS_LOGGED_IN] = false
        }
    }

    suspend fun getUsername(): String? {
        return dataStore.data.first()[USERNAME]
    }

    suspend fun getToken(): String? {
        return dataStore.data.first()[USER_TOKEN]
    }
    suspend fun saveUsername(username: String) {
        dataStore.edit { preferences ->
            preferences[USERNAME] = username
        }
    }

    suspend fun saveSession(user: User) {
        dataStore.edit { preferences ->
            preferences[USERNAME] = user.name
            preferences[USER_TOKEN] = user.token
            preferences[STATE_KEY] = user.isLogin
            preferences[IS_LOGGED_IN] = true
            preferences[GENDER] = user.gender
            preferences[EMAIL] = user.email
        }
    }

    fun getSession(): Flow<User> {
        return dataStore.data.map { preferences ->
            User(
                preferences[USERNAME] ?: "",
                preferences[USER_TOKEN] ?: "",
                preferences[IS_LOGGED_IN] ?: false,
                preferences[GENDER] ?: "",
                preferences[EMAIL] ?: ""

            )
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: PreferenceManager? = null
        private val USERNAME = stringPreferencesKey("username")
        private val USER_TOKEN = stringPreferencesKey("user_token")
        private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        private val STATE_KEY = booleanPreferencesKey("state")
        private val GENDER = stringPreferencesKey("gender")
        private val EMAIL = stringPreferencesKey("email")

        fun getInstance(dataStore: DataStore<Preferences>): PreferenceManager {
            return INSTANCE ?: synchronized(this) {
                val instance = PreferenceManager(dataStore)
                INSTANCE = instance
                instance
            }
        }

    }
}