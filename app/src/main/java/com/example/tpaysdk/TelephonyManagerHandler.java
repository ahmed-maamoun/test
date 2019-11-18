package com.example.tpaysdk;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import androidx.core.content.ContextCompat;

public class TelephonyManagerHandler {

    public boolean checkReadPhoneStatePermission(Context context) {
        return ContextCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") == 0;
    }

    public PhoneInfo getPhoneInfo(Context context) {
        PhoneInfo phoneInfo = null;
        if (this.checkReadPhoneStatePermission(context)) {
        TelephonyManager telephonyManager =
                (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            phoneInfo = new PhoneInfo();
            phoneInfo.PhoneNumber = telephonyManager.getLine1Number();
            if (phoneInfo.PhoneNumber.isEmpty() || phoneInfo.PhoneNumber == null) {
                phoneInfo.PhoneNumber = telephonyManager.getSubscriberId();
            }

            phoneInfo.OperatorId = telephonyManager.getNetworkOperator();
        }

        return phoneInfo;
    }
}
