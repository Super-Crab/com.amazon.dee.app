package com.amazon.identity.frc.helper;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;
/* loaded from: classes12.dex */
public final class TelephonyManagerUtil {
    private static final String TAG = "com.amazon.identity.frc.helper.TelephonyManagerUtil";

    private TelephonyManagerUtil() {
    }

    public static String getCarrier(Context context) {
        TelephonyManager telephonyManager = getTelephonyManager(context);
        if (telephonyManager == null) {
            return null;
        }
        return telephonyManager.getNetworkOperatorName();
    }

    public static String getLine1Number(Context context) {
        TelephonyManager telephonyManager = getTelephonyManager(context);
        if (telephonyManager == null) {
            return null;
        }
        return telephonyManager.getLine1Number();
    }

    private static TelephonyManager getTelephonyManager(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            Log.e(TAG, "TelephonyManager returned is null");
            return null;
        }
        return telephonyManager;
    }
}
