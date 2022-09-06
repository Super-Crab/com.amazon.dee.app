package com.amazon.identity.auth.device;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.amazon.identity.auth.device.api.DeviceDataKeys;
import com.amazon.identity.auth.device.api.DeviceDataStoreException;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class jb {
    private static final String TAG = "jb";
    private static String rB;

    private jb() {
    }

    @SuppressLint({"NewApi"})
    public static synchronized String aG(Context context) {
        synchronized (jb.class) {
            if (mz.aY(context)) {
                if (mz.aZ(context) && !mz.aj(context)) {
                    int i = Build.VERSION.SDK_INT;
                    io.i(TAG, "MAP Client side on FireOS 7+ calls IPC to get the DSN.");
                    if (!TextUtils.isEmpty(rB)) {
                        return rB;
                    }
                    try {
                        cf aP = ce.t(context).aP(DeviceDataKeys.KEY_DEVICE_SERIAL_NUMBER);
                        if (aP != null) {
                            String str = aP.value;
                            rB = str;
                            return str;
                        }
                        io.e(TAG, "Cannot get device DSN from IPC");
                    } catch (DeviceDataStoreException e) {
                        io.e(TAG, "Cannot get device DSN", e);
                    }
                } else {
                    return aH(context);
                }
            }
            return null;
        }
    }

    @SuppressLint({"NewApi", "MissingPermission"})
    static String aH(Context context) {
        if (mz.aZ(context)) {
            String str = null;
            int i = Build.VERSION.SDK_INT;
            try {
                str = Build.getSerial();
            } catch (SecurityException unused) {
                io.e(TAG, "Cannot get Build.getSerial(). No READ_PHONE_STATE or READ_PRIVILEGED_PHONE_STATE permission granted");
            } catch (Exception e) {
                io.e(TAG, "Caught a general exception", e);
            }
            if (TextUtils.isEmpty(str) || TextUtils.equals(str, "unknown")) {
                io.e(TAG, "Cannot get build serial, return ".concat(String.valueOf(str)));
            }
            return str;
        }
        throw new UnsupportedOperationException("Cannot get Build serial on non FireOS Device");
    }
}
