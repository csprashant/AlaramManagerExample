package com.example.alarmmanagerexample;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
public static String CHANNEL_ID="Alaram_Hello_Msg";
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();

        //create intent to go to the alarmreciever class
        Intent intent=new Intent(this,AlarmNotificationReceiver.class);
        PendingIntent pIntent=PendingIntent.getBroadcast(this.getApplicationContext(),1,intent,0);
        //get the alarm manager
        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+600000,pIntent);
        Toast.makeText(this, "Notification in 10 minutes", Toast.LENGTH_SHORT).show();
    }
    public void createNotificationChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)// for checking devices is running on which Android os o Build.VERSIION_CODE.o means oriyo
        {
            NotificationChannel channel=new NotificationChannel(CHANNEL_ID,"notification", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("MY");
            NotificationManager notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }
}