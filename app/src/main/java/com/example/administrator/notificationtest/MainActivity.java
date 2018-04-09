package com.example.administrator.notificationtest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

import static android.graphics.Color.GREEN;

public class MainActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.send_notification);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                Intent intent = new Intent(MainActivity.this,CancelNotificationActivity.class);
               // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
               // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,0);

               if(Build.VERSION.SDK_INT>=26) {
                    NotificationChannel channel = new NotificationChannel("MyChannelId", "mytestchannel", NotificationManager.IMPORTANCE_DEFAULT);
                    channel.enableLights(true);//开启闪光灯
                    channel.enableVibration(true);//开启震动
                    notificationManager.createNotificationChannel(channel);

                    Notification notification = new NotificationCompat.Builder(MainActivity.this,"MyChannelId")
                            .setContentTitle("This is content title")
                            .setContentText("This is content text")
                            .setWhen(System.currentTimeMillis())
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                            .setLights(Color.GREEN,1000,2000)//设置闪光灯亮1s暗2s
                            .setVibrate(new long []{0,1000,2000,3000})//设置震动频率
                            //.setAutoCancel(true)//点击通知后，通知自动取消
                            .setSound(Uri.fromFile(new File("/qqmusic/song/fangyuan.mp3")))
                            .setContentIntent(pendingIntent)
                            .build();
                      notificationManager.notify(1,notification);//发布通知以显示在状态栏中


                }else {
                   Notification notification = new NotificationCompat.Builder(MainActivity.this,null)
                           .setContentTitle("This is content title")
                           .setContentText("This is content text")
                           .setWhen(System.currentTimeMillis())
                           .setSmallIcon(R.mipmap.ic_launcher)
                           .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                           .setLights(Color.GREEN,1000,2000)//设置闪光灯亮1s暗2s
                           .setVibrate(new long []{0,1000,2000,3000})//设置震动频率
                           //.setAutoCancel(true)//点击通知后，通知自动取消
                           .setContentIntent(pendingIntent)
                           .setSound(Uri.fromFile(new File("/Recordings/gaosang.m4a")))
                           .build();
                   notificationManager.notify(2,notification);//发布通知以显示在状态栏中


               }



            }
        });


    }
}
