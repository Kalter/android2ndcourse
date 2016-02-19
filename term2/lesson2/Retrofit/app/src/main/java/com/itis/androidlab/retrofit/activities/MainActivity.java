package com.itis.androidlab.retrofit.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.itis.androidlab.retrofit.R;
import com.itis.androidlab.retrofit.models.FullWeatherInfo;
import com.itis.androidlab.retrofit.network.SessionRestManager;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.display) TextView mDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);
        GetTemperatureRequest getTemperatureRequest = new GetTemperatureRequest();
        getTemperatureRequest.execute("Kazan");
    }

    public class GetTemperatureRequest extends AsyncTask<String, Void, FullWeatherInfo> {

        @Override
        protected FullWeatherInfo doInBackground(String... city) {
            return SessionRestManager.getInstance().getRest().getTemperatureByCity(city[0]);
        }

        @Override
        protected void onPostExecute(FullWeatherInfo fullWeatherInfo) {
            mDisplay.setText(fullWeatherInfo.toString());
        }
    }
}
