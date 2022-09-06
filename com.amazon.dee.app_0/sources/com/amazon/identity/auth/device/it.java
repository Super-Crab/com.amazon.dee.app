package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.amazon.identity.auth.device.framework.MAPApplicationInformationQueryer;
import java.util.List;
import java.util.Locale;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class it {
    private static final String TAG = "com.amazon.identity.auth.device.it";

    private it() {
    }

    public static Integer aB(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo;
            if (applicationInfo == null) {
                return null;
            }
            return Integer.valueOf(applicationInfo.targetSdkVersion);
        } catch (PackageManager.NameNotFoundException e) {
            io.e(TAG, String.format("Could not find package. Error Message : %s", e.getMessage()));
            return null;
        }
    }

    public static boolean aC(Context context) {
        io.dm(TAG);
        if (mz.bb(context) && !mz.f(ed.M(context))) {
            List<du> cY = MAPApplicationInformationQueryer.E(context).cY();
            boolean z = cY.isEmpty() || cY.get(0).dK();
            String str = TAG;
            Locale locale = Locale.ENGLISH;
            Object[] objArr = new Object[2];
            objArr[0] = context.getPackageName();
            objArr[1] = z ? "" : "not";
            String.format(locale, "Current package %s should %s generate device data.", objArr);
            io.dm(str);
            return z;
        }
        String str2 = TAG;
        String.format("Running in 1P, or isolation mode. Package %s should generate device data.", context.getPackageName());
        io.dm(str2);
        return true;
    }

    public static boolean c(String str, Context context) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            context.getPackageManager().getPackageInfo(str, 0);
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                if (applicationInfo.enabled) {
                    return true;
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return false;
    }

    public static Long x(Context context, String str) {
        try {
            return Long.valueOf(context.getPackageManager().getPackageInfo(str, 0).versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            io.e(TAG, String.format("Could not find package. Error message : %s", e.getMessage()));
            return null;
        } catch (Exception e2) {
            io.e(TAG, String.format("Could not get version code for package. Error Message : %s", e2.getMessage()));
            return null;
        }
    }
}
