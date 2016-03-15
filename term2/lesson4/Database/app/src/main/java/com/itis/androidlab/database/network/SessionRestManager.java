package com.itis.androidlab.database.network;

import com.itis.androidlab.database.BuildConfig;
import com.itis.androidlab.database.Config;
import com.itis.androidlab.database.utils.AndroidUtils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

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

// Изменилось создание Interceptor-а, теперь мы выполняем это, используя классы библиотеки OkHttp
    private OkHttpClient setupHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                // Получаем  HTTP запрос, который будет выполняется
                Request original = chain.request();

                // Добавляем к URL запроса параметры
                HttpUrl url = original.url().newBuilder()
                        .addQueryParameter("appid", Config.APPLICATION_ID)
                        .build();

                // Настраиваем запрос, изменив его Url, на тот, что определили выше, добавив header-ы, но методо и тело запроса оставляем прежними
                Request request = original.newBuilder()
                        .url(url)
                        .header("Accept", "application/json")
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        // Задаём "уровень" логирования запросов
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        // set your desired log level
        loggingInterceptor.setLevel(BuildConfig.DEBUG ? Level.BODY : Level.NONE);
        httpClient.addInterceptor(loggingInterceptor);
        return httpClient.build();
    }

    /**
     * Определяем RestAdapter,
     * <p/>
     * - baseUrl - базовый URL для запросов
     * - client - настройки HTTP клиента, с Retrofit 2.x используем клиент библиотеки OkHttp
     * - addConverterFactory - добавляем конвертер, при помощи которого будут парсится JSON
     */
    private final Retrofit REST_ADAPTER = new Retrofit.Builder()
            .baseUrl(AndroidUtils.getRestEndpoint())
            .client(setupHttpClient())
            .addConverterFactory(JacksonConverterFactory.create())
            .build();


    public WeatherRest getRest() {
        return REST_ADAPTER.create(WeatherRest.class);
    }

}
