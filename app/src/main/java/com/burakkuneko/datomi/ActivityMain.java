package com.burakkuneko.datomi;
import android.net.TrafficStats;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ActivityMain extends Activity{
    int counter = 0;
    LinearLayout main;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);

        main = findViewById(R.id.main);
        textView = findViewById(R.id.text);
        button = findViewById(R.id.button);
        button.setOnClickListener(requestUssdCall());

    }
    View.OnClickListener requestUssdCall() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view){

            }
        };
    }
}
