package com.avileapconnect.com.notificationsample;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {

    /**
     * The Id's of the channel
     * */
    public static final String CHANNEL_1_ID = "channel1";
    public static final String CHANNEL_2_ID = "channel2";


    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationsChannels();

    }

    /**
     * Sets Properties of notification channels. These Channels can be viewed under App Settings-> App Notification
     * */
    private void createNotificationsChannels() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel1 = new NotificationChannel(
                    //Channel ID
                    CHANNEL_1_ID,
                    //Name of the channel
                    "Channel 1",
                    //This is the Priority. High means it will pop-up with a sound
                    NotificationManager.IMPORTANCE_HIGH
            );
            //The description of channel for user. It will be shown under App Notification
            // For this purpose it is dummy
            channel1.setDescription("Description Of Channel 1");

            NotificationChannel channel2= new NotificationChannel(
                    CHANNEL_2_ID,
                    "Channel 2",
                    //Low priority means there will be no pop-up/sound
                    NotificationManager.IMPORTANCE_LOW
            );
            channel2.setDescription("Description Of Channel 2");

            // Creates the channels
            NotificationManager manager = getSystemService(NotificationManager.class);
            assert manager != null;
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }
}
