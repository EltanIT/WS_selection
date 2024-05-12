package com.example.ws_selection.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainActivityViewModel(

): ViewModel() {

    private val _isConnectionTrue = MutableStateFlow(true)
    val isConnectionTrue = _isConnectionTrue.asStateFlow()

    fun isOnline(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            while (true){
                delay(2000)
                val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
                if (connectivityManager != null) {
                    val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                    if (capabilities != null) {
                        if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                            _isConnectionTrue.value = true
                            Log.i("InternetClient", "NetworkCapabilities.TRANSPORT_CELLULAR")
                        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                            _isConnectionTrue.value = true
                            Log.i("InternetClient", "NetworkCapabilities.TRANSPORT_WIFI")
                        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                            _isConnectionTrue.value = true
                            Log.i("InternetClient", "NetworkCapabilities.TRANSPORT_ETHERNET")
                        }
                    }else{
                        _isConnectionTrue.value = false
                        Log.i("InternetClient", "connection is false")
                    }
                }
            }

        }

    }


    fun closeAlertDialog(){
        _isConnectionTrue.value = true
    }
}