package com.avileapconnect.com.foregroundservicesample;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import static com.avileapconnect.com.foregroundservicesample.App.CHANNEL_ID;


public class ExampleService extends Service {


    //Will be called only once
    @Override
    public void onCreate() {
        super.onCreate();
    }

    //Will be triggered when we start our service. It will be called every time we call startService()
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //return super.onStartCommand(intent, flags, startId);

        String input = intent.getStringExtra("inputExtra");

        Intent notificationIntent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0,notificationIntent,0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Example Service")
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .build();

        //This line indicates that the Service we want to start is actually a Foreground Service
        //This makes sure that it will not be killed
        startForeground(1,notification);

        return START_NOT_STICKY;
    }


    //Destroys the Service
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //This method will only be called for Bound Services which helps in communicating back and forth
    //with various components. In our case we are not implementing bound service and hence there is no
    //need for it to override
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
