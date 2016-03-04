package com.example.itis.patternatest;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

public class BackgroundReceiverService extends IntentService {

    public static final int RESULT_OK = 1;
    public static final int RESULT_ERROR = 2;

    /**
     * В классе необходимо создать конструктор,
     * в котором вызываем конструктор супер-класса и указываем какое-нибудь имя,
     * которое будет использовано для наименования потока.
     */
    public BackgroundReceiverService() {
        super("ReminderService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ResultReceiver receiver = intent.getParcelableExtra(Constant.EXTRA_RECEIVER);
        try {
            // Выполняем операцию
            Thread.sleep(3000);
            receiver.send(RESULT_OK, new Bundle());
        } catch (InterruptedException e) {
            e.printStackTrace();
            receiver.send(RESULT_ERROR, new Bundle());
        }
    }
}
