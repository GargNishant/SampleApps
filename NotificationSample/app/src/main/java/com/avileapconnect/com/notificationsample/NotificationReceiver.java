package com.avileapconnect.com.notificationsample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NotificationReceiver extends BroadcastReceiver {

    /**
     * The onReceived will be called when we trigger this Broadcast Receiver.
     * The code inside it can be triggered even when the app is not in foreground
     * */
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("toastmsg");
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }
}
