package com.burakkuneko.datomi.mobile.data;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MobileDataManager {
    Context context;
    Handler handler;
    TelephonyManager telephonyManager;

    public MobileDataManager(Context context) {
        this.context = context;
        this.handler = new Handler(Looper.getMainLooper());
        this.telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    public interface OnReceiveData {
        void onReceive(String str);
    }

    public void checkMobileData(OnReceiveData onReceiveData) {
        if (context.checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                telephonyManager.sendUssdRequest("*222#", new TelephonyManager.UssdResponseCallback() {
                    @Override
                    public void onReceiveUssdResponse(TelephonyManager telephonyManager, String request, CharSequence response) {
                        super.onReceiveUssdResponse(telephonyManager, request, response);
                        //dataInfo.update(res);
                        Toast.makeText(context, response, Toast.LENGTH_LONG).show();
                        onReceiveData.onReceive(response.toString());
                    }
                }, handler);
            } else {
                Toast.makeText(context, "SDK version no supported.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Activity ac = (Activity) context;
            ac.requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
        }
    }

}
