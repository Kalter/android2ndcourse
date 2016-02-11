package com.itis.androidlab.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String APP_PREFERENCES = "counter";

    public static final String APP_PREFERENCES_CLICKS = "clicks";

    private Integer mCounter = 0;

    private TextView mDisplay;

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        mDisplay = (TextView) findViewById(R.id.counter);
        readData();
    }

    private void readData() {
        if (mSharedPreferences.contains(APP_PREFERENCES_CLICKS)) {
            mCounter = mSharedPreferences.getInt(APP_PREFERENCES_CLICKS, 0);
        }
        mDisplay.setText(mCounter.toString());
    }

    public void clickListener(View view) {
        mCounter++;
        mDisplay.setText(mCounter.toString());
    }

    public void clearButtonListener(View view) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.remove(APP_PREFERENCES_CLICKS);
        editor.clear();
        editor.apply();
        mCounter = 0;
        mDisplay.setText(mCounter.toString());
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(APP_PREFERENCES_CLICKS, mCounter);
        editor.apply();
    }

}
