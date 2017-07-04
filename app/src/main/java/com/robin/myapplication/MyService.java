package com.robin.myapplication;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;

public class MyService extends Service {
    private String TAG = "ButtonMainActivity";
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter intent = new IntentFilter();
        intent.addAction(Intent.ACTION_MEDIA_BUTTON);

        registerReceiver(new TestMedia(), intent);
    }

    private class TestMedia extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d(TAG, "onReceive action : " + action);
            KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
            Log.i(TAG, "Action ---->" + action + "  KeyEvent----->"+ keyEvent.toString());
        }
    }
}
