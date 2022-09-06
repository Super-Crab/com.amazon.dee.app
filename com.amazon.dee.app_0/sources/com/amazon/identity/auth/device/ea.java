package com.amazon.identity.auth.device;

import android.text.TextUtils;
import java.util.UUID;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class ea {
    private static String lf = UUID.randomUUID().toString().replace(ProcessIdUtil.DEFAULT_PROCESSID, "");
    private static final String TAG = ea.class.getSimpleName();

    public abstract long cr();

    public abstract String cs();

    public abstract boolean ct();

    public abstract String cu();

    public mc dR() {
        return new mc(Long.toString(cr()));
    }

    public String dS() {
        String str;
        try {
            str = getDeviceSerialNumber();
        } catch (Exception e) {
            io.e(TAG, "Exception when trying to get DSN", e);
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            io.e(TAG, "Cannot get DSN, use randomly generated: " + lf);
            return lf;
        }
        return str;
    }

    public abstract String f();

    public abstract String getDeviceSerialNumber();

    public abstract String getDeviceType();
}
