package com.example.gihansandaru.workmanagersample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.concurrent.TimeUnit;

import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

public class MainActivity extends AppCompatActivity {

    private PeriodicWorkRequest.Builder dataCheckBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.oneTimeRequest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPeriodicWork();
            }
        });


    }

    private void startWorkManager() {
        OneTimeWorkRequest compressionWork =
                new OneTimeWorkRequest.Builder(MyWorker.class)
                        .build();
        WorkManager.getInstance().enqueue(compressionWork);
    }

    private void startPeriodicWork(){
        PeriodicWorkRequest.Builder photoCheckBuilder =
                new PeriodicWorkRequest.Builder(MyWorker.class, 1,
                        TimeUnit.SECONDS);
// ...if you want, you can apply constraints to the builder here...

// Create the actual work object:
        PeriodicWorkRequest photoCheckWork = photoCheckBuilder.addTag("ADO").build();
// Then enqueue the recurring task:
        WorkManager.getInstance().enqueue(photoCheckWork);
    }
}
