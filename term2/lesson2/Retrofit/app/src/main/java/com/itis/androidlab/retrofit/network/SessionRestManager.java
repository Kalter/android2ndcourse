package com.itis.androidlab.retrofit.network;

import com.itis.androidlab.retrofit.BuildConfig;
import com.itis.androidlab.retrofit.Config;
import com.itis.androidlab.retrofit.utils.AndroidUtils;
import com.itis.androidlab.retrofit.utils.JacksonConverter;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class SessionRestManager {

    private static volatile SessionRestManager sInstance;

    private SessionRestManager() {
    }

    public static SessionRestManager getInstance() {
        if (sInstance == null)
            synchronized (SessionRestManager.class) {
                if (sInstance == null)
                    sInstance = new SessionRestManager();
            }
        return sInstance;
    }

    /**
     * Injects basic auth header to an every request
     */
    private final RequestInterceptor REQUEST_INTERCEPTOR = new RequestInterceptor() {
        @Override
        public void intercept(RequestFacade request) {
            request.addQueryParam("appid", Config.APPLICATION_ID);

            request.addHeader("Accept", "application/json");
        }
    };

    private final RestAdapter.Builder REST_ADAPTER_BUILDER = new RestAdapter.Builder()
            .setEndpoint(AndroidUtils.getRestEndpoint())
            .setConverter(new JacksonConverter())
            .setRequestInterceptor(REQUEST_INTERCEPTOR)
            .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL
                    : RestAdapter.LogLevel.NONE);

    private final RestAdapter REST_ADAPTER = REST_ADAPTER_BUILDER.build();

    public WeatherRest getRest() {
        return REST_ADAPTER.create(WeatherRest.class);
    }

}
