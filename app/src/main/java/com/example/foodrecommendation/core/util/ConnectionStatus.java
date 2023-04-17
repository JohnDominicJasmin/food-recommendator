package com.example.foodrecommendation.core.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import kotlin.Suppress;

public class ConnectionStatus {

    @Suppress(names = "Deprecation")
    public static boolean hasInternetConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable();
    }
}
