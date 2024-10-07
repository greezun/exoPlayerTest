package com.greezun.exoplayertest.data.tools

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


class NetworkListener @Inject constructor(context: Context) {
    private val manager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val _isAvailable = MutableStateFlow(true)
    val isAvailable = _isAvailable.asStateFlow()

    private val callback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            _isAvailable.value = listen()
        }

        override fun onLost(network: Network) {
            _isAvailable.value = listen()
        }

    }
    fun stopListener() {
        manager.unregisterNetworkCallback(callback)
    }

    fun startListener() {
        val request = getRequest()
        manager.registerNetworkCallback(request, callback)
    }

    private fun getRequest(): NetworkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()


    private fun listen(): Boolean {
        return manager.activeNetworkInfo?.isConnected ?: false
    }

}