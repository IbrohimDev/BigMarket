package com.example.bigmarketapp.presentation.ui

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.bigmarketapp.R
import com.example.bigmarketapp.data.enum.Screens
import com.example.bigmarketapp.data.local.preferences.SharedPreferences
import com.example.bigmarketapp.data.reciver.MyNetworkBroadCast
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var internetReceiver: MyNetworkBroadCast

    @Inject
    lateinit var shared: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHost = supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment
        val navController = navHost.navController.navInflater.inflate(R.navigation.app_graph)
        navController.setStartDestination(R.id.splashScreen)
        navHost.navController.graph = navController


        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val intentFilter = IntentFilter()
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        intentFilter.priority = 100

        registerReceiver(internetReceiver, intentFilter)

        internetReceiver.setListener {
            navHost.navController.popBackStack()
            if (!it) navHost.navController.navigate(R.id.noConnectionScreen)
            else {
                if (shared.screens == Screens.SPLASH) {
                    navHost.navController.navigate(R.id.splashScreen)
                } else {
                    navHost.navController.navigate(R.id.mainScreen)
                }
            }
        }


    }


    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(internetReceiver)
    }

}