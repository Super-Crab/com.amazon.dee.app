package com.amazon.identity.auth.device;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import com.amazon.identity.auth.device.utils.ReflectionHelper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsConstants;
import java.util.Locale;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class eg {
    private static final String TAG = "com.amazon.identity.auth.device.eg";
    private final ReflectionHelper lt = new ReflectionHelper();

    private String bx(String str) {
        return str.length() > 31 ? str.substring(0, 31) : str;
    }

    public void a(Runnable runnable) {
        a("addChangeCallback", new Class[]{Runnable.class}, runnable);
    }

    @TargetApi(17)
    public String b(String str, Context context) {
        if (str == null) {
            return null;
        }
        if (!mz.iT()) {
            return get(str);
        }
        String bx = bx(str);
        String str2 = TAG;
        "Try to get Settings.Global: ".concat(String.valueOf(bx));
        io.dm(str2);
        return Settings.Global.getString(context.getContentResolver(), bx);
    }

    public String get(String str) {
        if (str == null) {
            return null;
        }
        String bx = bx(str);
        GeneratedOutlineSupport1.outline161(bx, "Try to get system property: ", TAG);
        return (String) a(MetricsConstants.Method.CACHE_GET, new Class[]{String.class}, bx);
    }

    private Object a(String str, Class[] clsArr, Object... objArr) {
        try {
            return this.lt.a(str, "android.os.SystemProperties", clsArr, objArr);
        } catch (ReflectionHelper.CannotCallMethodException unused) {
            io.e(TAG, String.format(Locale.US, "Cannot call such method %s in SystemProperties in Android %d", str, Integer.valueOf(Build.VERSION.SDK_INT)));
            return null;
        }
    }
}
