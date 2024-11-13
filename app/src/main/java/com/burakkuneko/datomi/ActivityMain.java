package com.burakkuneko.datomi;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

public class ActivityMain extends Activity {
    int counter = 0;
    TelephonyManager telephonyManager;
    LinearLayout main;
    TextView textView;
    Button button;

    Handler handler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);

        main = findViewById(R.id.main);
        textView = findViewById(R.id.text);
        button = findViewById(R.id.button);
        button.setOnClickListener(requestUssdCall());

    }

    View.OnClickListener requestUssdCall() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendUssdRequest();
            }
        };
    }

    void sendUssdRequest() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            telephonyManager.sendUssdRequest("*222#", new TelephonyManager.UssdResponseCallback() {
                @Override
                public void onReceiveUssdResponse(TelephonyManager telephonyManager, String request, CharSequence response) {
                    super.onReceiveUssdResponse(telephonyManager, request, response);
                    String res = response.toString();
                    Log.d("USSD_RESPONSE: ", res);
                    textView.setText(res);
                    Toast.makeText(ActivityMain.this, response, Toast.LENGTH_LONG).show();
                }
            }, handler);
        }
    }

}
