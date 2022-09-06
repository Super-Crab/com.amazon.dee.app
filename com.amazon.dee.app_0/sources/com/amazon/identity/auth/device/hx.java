package com.amazon.identity.auth.device;

import android.accounts.AccountManager;
import android.accounts.AuthenticatorDescription;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.utils.AccountConstants;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class hx {
    private static final String TAG = "com.amazon.identity.auth.device.hx";
    private static final et qW = new eu();
    private static AtomicReference<en<a>> qX = new AtomicReference<>(null);

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public static final class a {
        public final String bm;
        public final Integer qY;

        public a(String str, Integer num) {
            this.bm = str;
            this.qY = num;
        }
    }

    private hx() {
    }

    @Deprecated
    private static boolean a(Context context, AuthenticatorDescription authenticatorDescription, boolean z) {
        if (!AccountConstants.AMAZON_ACCOUNT_TYPE.equals(authenticatorDescription.type)) {
            return false;
        }
        return qW.a(context, e(authenticatorDescription.packageName, z));
    }

    public static boolean aj(Context context) {
        a am = am(context);
        if (am == null) {
            return false;
        }
        return a(context, am);
    }

    public static boolean ak(Context context) {
        return am(context) != null;
    }

    public static boolean al(Context context) {
        a am = am(context);
        if (am == null) {
            return true;
        }
        return a(context, am);
    }

    public static a am(Context context) {
        if (mz.f(ed.M(context))) {
            return null;
        }
        if (qX.get() == null) {
            qX.compareAndSet(null, ar(context));
        }
        return qX.get().getValue();
    }

    public static boolean an(Context context) {
        return ao(context) != null;
    }

    public static a ao(Context context) {
        return ar(context).getValue();
    }

    public static boolean ap(Context context) {
        return b(context, false) != null;
    }

    public static boolean aq(Context context) {
        AuthenticatorDescription authenticatorDescription;
        AuthenticatorDescription[] authenticatorTypes = AccountManager.get(context).getAuthenticatorTypes();
        int length = authenticatorTypes.length;
        int i = 0;
        while (true) {
            if (i < length) {
                authenticatorDescription = authenticatorTypes[i];
                if (a(context, authenticatorDescription, true)) {
                    io.i(TAG, String.format("SSO was found in package %s", authenticatorDescription.packageName));
                    break;
                }
                i++;
            } else {
                io.i(TAG, "Cannot find amazon authenticator. returning null");
                authenticatorDescription = null;
                break;
            }
        }
        return authenticatorDescription != null;
    }

    private static en<a> ar(Context context) {
        ProviderInfo a2 = ek.a(dn.jG, context.getPackageManager());
        if (a2 != null && qW.a(context, e(a2.packageName, true))) {
            Integer D = jm.D(context, a2.packageName);
            String str = TAG;
            String.format(Locale.ENGLISH, "Retrieved central APK info from package manager using content provider %s. The package name is : %s and version is: %d.", dn.jG, a2.packageName, D);
            io.dm(str);
            return new en<>(new a(a2.packageName, D));
        }
        AuthenticatorDescription b = b(context, true);
        if (b != null) {
            String str2 = b.packageName;
            Integer D2 = jm.D(context, str2);
            String str3 = TAG;
            String.format(Locale.ENGLISH, "Retrieved central APK info from account manager using account authenticator. The package name is: %s and version is: %d.", str2, D2);
            io.dm(str3);
            return new en<>(new a(str2, D2));
        }
        io.i(TAG, "No central apk detected. This should be a 3P device.");
        return new en<>(null);
    }

    @Deprecated
    public static AuthenticatorDescription as(Context context) {
        return b(context, false);
    }

    public static AuthenticatorDescription b(Context context, boolean z) {
        AuthenticatorDescription[] authenticatorTypes;
        for (AuthenticatorDescription authenticatorDescription : AccountManager.get(context).getAuthenticatorTypes()) {
            if (a(context, authenticatorDescription, z)) {
                String str = TAG;
                String.format("SSO was found in package %s", authenticatorDescription.packageName);
                io.dm(str);
                return authenticatorDescription;
            }
        }
        io.i(TAG, "Cannot find amazon authenticator. returning null");
        return null;
    }

    private static Bundle e(String str, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString("central_package_name", str);
        bundle.putBoolean("ignore_isolation_mode", z);
        return bundle;
    }

    private static boolean a(Context context, a aVar) {
        String packageName = context.getPackageName();
        String str = aVar.bm;
        boolean equals = TextUtils.equals(packageName, str);
        if (!equals) {
            String str2 = TAG;
            String.format("Current package: %s and Authenticator owner's package: %s are different", packageName, str);
            io.dm(str2);
        }
        return equals;
    }
}
