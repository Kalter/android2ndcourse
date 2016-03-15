package com.itis.androidlab.database.utils;

import com.itis.androidlab.database.BuildConfig;
import com.itis.androidlab.database.Config;

public final class AndroidUtils {

    private AndroidUtils() {
    }

    public static String getRestEndpoint() {
        return BuildConfig.DEBUG ? Config.WEATHER_ENDPOINT_DEBUG : Config.WEATHER_ENDPOINT_RELEASE;
    }
}
