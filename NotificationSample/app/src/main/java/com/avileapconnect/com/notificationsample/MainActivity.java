package com.avileapconnect.com.notificationsample;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // Backwards compatibility for creating notification. Cannot create Notification Channels
    private NotificationManagerCompat managerCompat;
    private EditText title,msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.title);
        msg = findViewById(R.id.message);

        managerCompat = NotificationManagerCompat.from(this);

    }

    public void sendOnChannel2(View view) {
        Notification notification = new NotificationCompat.Builder(this,App.CHANNEL_2_ID)
                .setSmallIcon(R.drawable.ic_channel_2_icon)
                //The settings here will be used when we are working on API level lower then 26.
                //Level < 26 does not support Notification Channels hence we pass the properties here
                .setContentTitle(title.getText().toString())
                .setContentText(msg.getText().toString())
                .setPriority(NotificationCompat.PRIORITY_LOW)
                //See documentation for this option
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();

        managerCompat.notify(2,notification);

    }

    public void sendOnChannel1(View view) {

        //Event Listener when notification is clicked. It will start the
        Intent activity = new Intent(this, MainActivity.class);


        // For pending Intent see the One Note Online Intents

        //Basically it is an Intent which let's 3rd party apps use the intent using your applications
        //permissions and start the Service/Activity etc..
        PendingIntent contentIntent = PendingIntent.getActivity(this,
                0,activity, 0);

        //This intent will be used to trigger the Broadcast Receiver which will activate the toast
        Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
        broadcastIntent.putExtra("toastmsg",msg.getText().toString());
        PendingIntent actionIntent = PendingIntent.getBroadcast(this,
                0,broadcastIntent,

                //We are passing this flag to show that if the new object of this Pending intent is created
                // then it should update itself by using the new BroadcastIntent if any

                // if not done then every time there will be only same msg and pending intent will refuse to
                //update itself
                PendingIntent.FLAG_UPDATE_CURRENT);


        Notification notification = new NotificationCompat.Builder(this,App.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_channel_1_icon)
                //The settings here will be used when we are working on API level lower then 26.
                //Level < 26 does not support Notification Channels hence we pass the properties here
                .setContentTitle(title.getText().toString())
                .setContentText(msg.getText().toString())
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                //See documentation for this option
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                //Usage of Pending Intent for this particular Notification
                //Basically it will be able to call the Intent.
                .setContentIntent(contentIntent)
                //This lets the notification to cancel/dismiss when the notification itself is clicked
                .setAutoCancel(true)

                //The option below makes sure that this particular notification will not show alerts when the notification
                //is updated. As long as it is same it will only alert the user once like popping up/making Sound etc..
                //.setOnlyAlertOnce(true)

                //Sets the color of the notification. See in action to know which part is colored.
                .setColor(Color.BLUE)

                //add Action creates a new Button on the notification. We can set an Icon for this
                // which will be only available on API level < Oreo. The tile would be "Toast" in our case
                // and third is the pending intent that we want to trigger
                .addAction(R.mipmap.ic_launcher,"Toast",actionIntent)
                .build();

        managerCompat.notify(1,notification);

    }
}
