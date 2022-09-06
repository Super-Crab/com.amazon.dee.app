package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class iq {
    private static final String TAG = "com.amazon.identity.auth.device.iq";

    private iq() {
    }

    public static Boolean o(Context context, String str, String str2) {
        Bundle w = w(context, str);
        if (w == null) {
            return Boolean.FALSE;
        }
        return Boolean.valueOf(w.getBoolean(str2, false));
    }

    public static List<String> p(Context context, String str, String str2) {
        ArrayList arrayList = new ArrayList();
        String q = q(context, str, str2);
        if (!TextUtils.isEmpty(q)) {
            for (String str3 : q.split("\\|")) {
                arrayList.add(str3);
            }
        }
        return arrayList;
    }

    public static String q(Context context, String str, String str2) {
        Bundle w = w(context, str);
        if (w == null) {
            return null;
        }
        return w.getString(str2);
    }

    public static String v(Context context, String str) {
        return q(context, context.getPackageName(), str);
    }

    private static Bundle w(Context context, String str) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
            if (applicationInfo == null) {
                io.w(TAG, "Could not get application meta data for installed package.");
                return null;
            }
            return applicationInfo.metaData;
        } catch (PackageManager.NameNotFoundException e) {
            io.e(TAG, "Could not get meta data for the application", e);
            return null;
        }
    }
}
