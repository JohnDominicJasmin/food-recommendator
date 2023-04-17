package com.example.foodrecommendation.core.di;

import android.content.Context;


import com.example.foodrecommendation.core.util.ConnectionStatus;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class NetworkModule {

    private static final String HEADER_PRAGMA = "Pragma";
    private static final String HEADER_CACHE_CONTROL = "Cache-Control";

    private static Interceptor providesNetworkInterceptor() {
        return chain -> {
            Response response = chain.proceed(chain.request());
            CacheControl cacheControl = new CacheControl.Builder()
                    .maxAge(30, TimeUnit.SECONDS)
                    .build();

            return response.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                    .build();
        };
    }


    private static Interceptor providesOfflineInterceptor(Context context) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!ConnectionStatus.hasInternetConnection(context)) {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(900, TimeUnit.DAYS)
                            .build();

                    request = request.newBuilder()
                            .removeHeader(HEADER_PRAGMA)
                            .removeHeader(HEADER_CACHE_CONTROL)
                            .cacheControl(cacheControl)
                            .build();
                }
                return chain.proceed(request);
            }
        };
    }



    private static Cache providesCache(Context context) {
        File httpCacheDirectory = new File(context.getCacheDir(), "offlineCache");
        int cacheSize = 50 * 1024 * 1024;
        return new Cache(httpCacheDirectory, cacheSize);
    }


    public static OkHttpClient providesOkHttpClient(
       Context context
    ) {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .cache(providesCache(context))
                .addNetworkInterceptor(providesNetworkInterceptor())
                .addInterceptor(providesOfflineInterceptor(context))
                .build();
    }
}
