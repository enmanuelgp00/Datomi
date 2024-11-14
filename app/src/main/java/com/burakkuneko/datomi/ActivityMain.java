package com.burakkuneko.datomi;

import android.Manifest;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.burakkuneko.datomi.mobile.data.MobileDataManager;


public class ActivityMain extends Activity {

    LinearLayout main;
    TextView textView;
    Button button;

    MobileDataManager mobileDataManager;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        mobileDataManager = new MobileDataManager(this);
        main = findViewById(R.id.main);
        textView = findViewById(R.id.text);
        button = findViewById(R.id.button);

        button.setOnClickListener((e) -> {
            mobileDataManager.checkMobileData( new MobileDataManager.OnReceiveData() {
                @Override
                public void onReceive(String str) {
                    textView.setText(str);
                }
            });
        });

    }
}
