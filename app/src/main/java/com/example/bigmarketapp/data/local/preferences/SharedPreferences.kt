package com.example.bigmarketapp.data.local.preferences

import android.content.Context
import androidx.core.content.edit
import com.example.bigmarketapp.data.enum.Screens
import com.example.bigmarketapp.utils.startScreen
import com.securepreferences.SecurePreferences
import javax.inject.Inject

class SharedPreferences @Inject constructor(
    private val context: Context
) {
    private val prefs = SecurePreferences(context, "MyPrefs", "ibrohim")

    private val SCREENS_CHECK = "screens"

    var screens: Screens
        get() = prefs.getString(SCREENS_CHECK, Screens.SPLASH.name)!!.startScreen()
        set(value) = prefs.edit().putString(SCREENS_CHECK, value.name).apply()
}