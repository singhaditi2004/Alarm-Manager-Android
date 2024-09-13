package com.example.alarmmanagerexample;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText text;
    AppCompatButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.editText);
        btn=findViewById(R.id.button);
        text.requestFocus();
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String f = text.getText().toString();
                long n = Long.parseLong(f);
                Intent intent=new Intent(MainActivity.this,BroadCastR.class);
                PendingIntent i=PendingIntent.getBroadcast(MainActivity.this,1,intent,PendingIntent.FLAG_MUTABLE);
                am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (n * 1000),i);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}