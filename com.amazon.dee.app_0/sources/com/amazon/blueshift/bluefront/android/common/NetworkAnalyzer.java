package com.amazon.blueshift.bluefront.android.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.amazon.devicesetupservice.smarthome.ProtocolType;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
/* loaded from: classes11.dex */
public final class NetworkAnalyzer {

    /* loaded from: classes11.dex */
    public enum NetworkType {
        EDGE("EDGE"),
        GPRS("GPRS"),
        LTE("LTE"),
        THREE_G("3G"),
        UNKNOWN("UNKNOWN"),
        WIFI(ProtocolType.WIFI),
        NONE("NONE");
        
        private final String mName;

        NetworkType(String str) {
            this.mName = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mName;
        }
    }

    @VisibleForTesting
    protected NetworkAnalyzer() {
    }

    private static NetworkType getMobileNetworkType(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            return NetworkType.UNKNOWN;
        }
        switch (telephonyManager.getNetworkType()) {
            case 1:
                return NetworkType.GPRS;
            case 2:
                return NetworkType.EDGE;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 14:
            case 15:
                return NetworkType.THREE_G;
            case 13:
                return NetworkType.LTE;
            default:
                return NetworkType.UNKNOWN;
        }
    }

    public static NetworkType getNetworkType(Context context) {
        Preconditions.checkNotNull(context, "context cannot be null");
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
            NetworkInfo.State state = null;
            NetworkInfo.State state2 = networkInfo != null ? networkInfo.getState() : null;
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(1);
            if (networkInfo2 != null) {
                state = networkInfo2.getState();
            }
            if (state != null && (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING)) {
                return NetworkType.WIFI;
            }
            if (state2 != null && (state2 == NetworkInfo.State.CONNECTED || state2 == NetworkInfo.State.CONNECTING)) {
                try {
                    return getMobileNetworkType(context);
                } catch (SecurityException unused) {
                    return NetworkType.UNKNOWN;
                }
            } else if (state == null && state2 == null) {
                return NetworkType.UNKNOWN;
            }
        }
        return NetworkType.NONE;
    }
}
