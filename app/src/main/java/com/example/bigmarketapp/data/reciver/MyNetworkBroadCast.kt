package com.example.bigmarketapp.data.reciver

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import com.example.bigmarketapp.utils.isOnline
import javax.inject.Inject


class MyNetworkBroadCast @Inject constructor(
    private val context: Context
) : BroadcastReceiver() {
    private var listener: ((Boolean) -> Unit)? = null

    @SuppressLint("ServiceCast")
    override fun onReceive(context: Context, intent: Intent) {
        val systemServiceObj: Any =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as Any
        val connectivityManager: ConnectivityManager = systemServiceObj as ConnectivityManager
        listener?.invoke(isOnline(context))

    }

    fun setListener(f: (Boolean) -> Unit) {
        listener = f
    }

}



