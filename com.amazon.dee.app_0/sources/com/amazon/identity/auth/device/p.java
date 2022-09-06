package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.api.CustomerAttributeKeys;
import com.amazon.identity.auth.device.features.Feature;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class p {
    private static final String TAG = "com.amazon.identity.auth.device.p";

    private p() {
    }

    public static boolean I(String str) {
        return TextUtils.equals(str, "A1PY8QQU9P0WJV") || TextUtils.equals(str, "A17I2SKGZYX7FH") || TextUtils.equals(str, "A1MPSLFC7L5AFK");
    }

    private static String J(String str) {
        return GeneratedOutlineSupport1.outline72(str, ".is_registered_key");
    }

    public static boolean a(Context context, String str) {
        return !ie.p(context, str);
    }

    public static boolean b(Context context, String str) {
        if (!cp.a(new cq(context)).a(Feature.OverrideDeviceAttributes)) {
            return false;
        }
        return iq.o(context, str, "MAPDeviceAttributeRuntimeOverride").booleanValue();
    }

    public static Bundle x() {
        return new Bundle();
    }

    public static boolean a(Context context, gg ggVar, String str, String str2) {
        if (a(context, str2)) {
            return ggVar.b(str, J(str2)) != null;
        }
        io.e(TAG, String.format("%s is not a child application", str2));
        return false;
    }

    public static void a(co coVar, fz fzVar, String str, String str2, String str3) {
        io.i(TAG, String.format("%s has registered", str));
        fzVar.v(J(str), "true");
        if (q.a(str3, coVar)) {
            fzVar.v(CustomerAttributeKeys.getDsnKeyForPackage(str2), str3);
            q.a(fzVar, str2);
        }
    }
}
