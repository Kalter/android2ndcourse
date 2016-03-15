package com.itis.androidlab.database.activities;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.itis.androidlab.database.R;
import com.itis.androidlab.database.database.WeatherContract.TemperatureColumns;
import com.itis.androidlab.database.database.WeatherDatabase;
import com.itis.androidlab.database.models.FullWeatherInfo;
import com.itis.androidlab.database.network.SessionRestManager;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.display) TextView mDisplay;

    @OnClick(R.id.get_info_from_database)
    void getInfoFromDatabase() {
        readWeatherFromDatabase();
    }

    private MaterialDialog mDialog;

    private WeatherDatabase mDatabase;
    private SQLiteDatabase mSqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);
        getTemperature("Kazan");

        mDatabase = new WeatherDatabase(this);
        mSqLiteDatabase = mDatabase.getReadableDatabase();
    }

    private void getTemperature(String city) {
        showProgressBar();
        Call<FullWeatherInfo> call = SessionRestManager.getInstance().getRest().getTemperatureByCity(city);
        call.enqueue(new Callback<FullWeatherInfo>() {
            @Override
            public void onResponse(Call<FullWeatherInfo> call, Response<FullWeatherInfo> response) {
                // response.isSuccessful() is true if the response code is 2xx
                if (response.isSuccessful()) {
                    FullWeatherInfo weatherInfo = response.body();
                    saveToDatabase(weatherInfo);
                    mDisplay.setText(weatherInfo.toString());
                } else {
                    int statusCode = response.code();

                    // handle request errors yourself
                    ResponseBody errorBody = response.errorBody();
                }
                hideProgressBar();
            }

            @Override
            public void onFailure(Call<FullWeatherInfo> call, Throwable t) {
                // handle execution failures like no internet connectivity
                hideProgressBar();
            }
        });
    }

    // Запись в базу данных необходимо выполнять в BACKGROUND потоке
    private void saveToDatabase(FullWeatherInfo weatherInfo) {
        ContentValues values = new ContentValues();
        // Задаём значения для каждого столбца
        values.put(TemperatureColumns.DATE, weatherInfo.getDate());
        values.put(TemperatureColumns.TEMPERATURE_MAX, weatherInfo.getTemperature().getTemperatureMax());
        values.put(TemperatureColumns.TEMPERATURE_MIN, weatherInfo.getTemperature().getTemperatureMin());
        // Вставляем данные в таблицу
        mSqLiteDatabase.insert(WeatherDatabase.Tables.TEMPERATURE, null, values);
        Toast.makeText(MainActivity.this, "Сохранено в базу", Toast.LENGTH_SHORT).show();
    }

    // Чтение из базы данных необходимо выполнять в BACKGROUND потоке
    private void readWeatherFromDatabase() {
        // Создаём запрос к базе данных для получения таблицы с температурой и указываем колонки, которые необходимо считать
        Cursor cursor = mSqLiteDatabase.query(
                WeatherDatabase.Tables.TEMPERATURE,
                new String[]{
                        TemperatureColumns.DATE,
                        TemperatureColumns.TEMPERATURE_MAX,
                        TemperatureColumns.TEMPERATURE_MIN},
                null, null,
                null, null, null);

        cursor.moveToFirst();

        long date = cursor.getLong(cursor.getColumnIndex(TemperatureColumns.DATE));
        Double temperatureMax = cursor.getDouble(cursor.getColumnIndex(TemperatureColumns.TEMPERATURE_MAX));
        Double temperatureMin = cursor.getDouble(cursor.getColumnIndex(TemperatureColumns.TEMPERATURE_MIN));

        mDisplay.setText("Минимальная температура = " + temperatureMin +"\n"
                + "Максимальная температура = " + temperatureMax);
        // не забываем закрывать курсор
        cursor.close();
    }

    protected void showProgressBar() {
        if (mDialog == null) {
            mDialog = new MaterialDialog.Builder(MainActivity.this)
                    .content(R.string.content_loading)
                    .progress(true, 0)
                    .cancelable(false)
                    .show();
        } else {
            mDialog.show();
        }
    }

    protected void hideProgressBar() {
        if (mDialog != null)
            mDialog.dismiss();
    }


}
