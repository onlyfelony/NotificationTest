package com.example.administrator.notificationtest;

import android.app.NotificationManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CancelNotificationActivity extends AppCompatActivity {
 private Button can_no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel);
        can_no = findViewById(R.id.canc);
        can_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

               if(Build.VERSION.SDK_INT>=26) {
                   notificationManager.cancel(1);//传入创建通知时的id
               }else{

                   notificationManager.cancel(2);//传入创建通知时的id

               }
            }
        });

    }
}
