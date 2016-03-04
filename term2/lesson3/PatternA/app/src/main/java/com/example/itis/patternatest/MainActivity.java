package com.example.itis.patternatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import android.os.Handler;

public class MainActivity extends AppCompatActivity implements BackgroundReceiver.Receiver{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService();
    }
    public void startService(){
        Intent intent = new Intent(this, BackgroundReceiverService.class);

        BackgroundReceiver receiver = new BackgroundReceiver(new Handler());
        receiver.setReceiver(this);
        intent.putExtra(Constant.EXTRA_RECEIVER, receiver);

        startService(intent);
    }

    @Override
    public void onReceivedResult(int resultCode, Bundle result) {
        switch (resultCode) {
            case BackgroundReceiverService.RESULT_OK:
                Toast.makeText(this, "Result ok", Toast.LENGTH_LONG).show();
                break;
            case BackgroundReceiverService.RESULT_ERROR:
                Toast.makeText(this, "Result error", Toast.LENGTH_LONG).show();
                break;
        }
    }

}
