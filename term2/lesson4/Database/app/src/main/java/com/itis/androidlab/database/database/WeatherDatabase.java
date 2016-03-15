package com.itis.androidlab.database.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.itis.androidlab.database.database.WeatherContract.TemperatureColumns;

public class WeatherDatabase extends SQLiteOpenHelper {

    // Имя базы данных
    private static final String DATABASE_NAME = "weather.db";
    // Версия базы данных, увеличиваем её, если внесли какие-то изменения в структуру
    private static final int DATABASE_VERSION = 1;

    public WeatherDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Список таблиц в базе
    public interface Tables {
        String TEMPERATURE = "temperature";
    }

    // В методе происходит создание базы данных, выполняем запросы на создание таблиц
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Tables.TEMPERATURE + " ("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TemperatureColumns.DATE + " NUMERIC NOT NULL,"
                + TemperatureColumns.TEMPERATURE_MAX + " REAL NOT NULL,"
                + TemperatureColumns.TEMPERATURE_MIN + " REAL NOT NULL,"
                + "UNIQUE (" + TemperatureColumns.DATE + ") ON CONFLICT REPLACE)");
    }

    // Метод запускается если были внесены изменения в структуру базы данных
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Первоначально удаляются все существующие таблицы
        db.execSQL("DROP TABLE IF EXISTS " + Tables.TEMPERATURE);

        // Затем создаём базу по-новой
        onCreate(db);
    }
}