package com.example.bigmarketapp.data.reciver

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import com.example.bigmarketapp.utils.isOnline
import javax.inject.Inject


class InternetBroadCast @Inject constructor(
    private val context: Context
) : BroadcastReceiver() {
    private var listener: ((Boolean) -> Unit)? = null

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.getIntExtra(
            WifiManager.EXTRA_WIFI_STATE,
            WifiManager.WIFI_STATE_UNKNOWN,
        )) {
            WifiManager.WIFI_STATE_ENABLED -> {
                listener?.invoke(isOnline(context))
            }
            WifiManager.WIFI_STATE_DISABLED -> {
                listener?.invoke(isOnline(context))
            }
        }
    }

    fun setListener(f: (Boolean) -> Unit) {
        listener = f
    }


}


