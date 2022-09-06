package com.amazon.identity.auth.device;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import java.util.LinkedHashSet;
import java.util.Set;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class bj {
    private static final String TAG = "com.amazon.identity.auth.device.bj";

    private bj() {
    }

    public static Set<String> c(Context context, String str) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        try {
            for (Signature signature : context.getPackageManager().getPackageInfo(str, 64).signatures) {
                linkedHashSet.add(Base64.encodeToString(signature.toByteArray(), 2));
            }
        } catch (PackageManager.NameNotFoundException unused) {
            io.e(TAG, "NameNotFoundException");
        } catch (SecurityException unused2) {
            io.e(TAG, "SecurityException");
        }
        return linkedHashSet;
    }
}
