package com.example.signalstrength.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

@Singleton
class UserPreferencesDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val USER_ID_KEY = stringPreferencesKey("user_id")
    private val USER_EMAIL_KEY = stringPreferencesKey("user_email")

    suspend fun saveUser(userId: String, email: String) {
        context.dataStore.edit { prefs ->
            prefs[USER_ID_KEY] = userId
            prefs[USER_EMAIL_KEY] = email
        }
    }

    suspend fun clearUser() {
        context.dataStore.edit { prefs ->
            prefs.remove(USER_ID_KEY)
            prefs.remove(USER_EMAIL_KEY)
        }
    }

    val isLoggedInFlow: Flow<Boolean> = context.dataStore.data.map { prefs ->
        val userId = prefs[USER_ID_KEY]
        !userId.isNullOrEmpty()
    }

    val userIdFlow: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[USER_ID_KEY]
    }

    val userEmailFlow: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[USER_EMAIL_KEY]
    }
}
