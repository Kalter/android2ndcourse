package com.example.itis.patternatest;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class BackgroundReceiver extends ResultReceiver {
    private Receiver mReceiver;
    /**
     * Create a new ResultReceive to receive results.  Your
     * {@link #onReceiveResult} method will be called from the thread running
     * <var>handler</var> if given, or from an arbitrary thread if null.
     *
     * @param handler
     */
    public BackgroundReceiver(Handler handler) {
        super(handler);
    }


    public void setReceiver(Receiver receiver) {
        mReceiver = receiver;
    }

    public interface Receiver{
        void onReceivedResult(int resultCode, Bundle result);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if(mReceiver != null) {
            mReceiver.onReceivedResult(resultCode, resultData);
        }
    }

}
