package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class eu implements et {
    private static final String TAG = "eu";

    @Override // com.amazon.identity.auth.device.et
    public boolean a(Context context, Bundle bundle) {
        String string = bundle.getString("central_package_name");
        if (TextUtils.equals(string, context.getPackageName())) {
            io.dm(TAG);
            return true;
        }
        boolean z = bundle.getBoolean("ignore_isolation_mode");
        String str = TAG;
        io.i(str, "Current package is: " + context.getPackageName() + ". Going to check central package's signature:" + string + ", since it is AOSP MAP library");
        if (new ek(context, z).bC(string)) {
            return true;
        }
        io.e(TAG, "Central package signature check failed! This probably means someone is squatting or a platform bug in the signature check. The account authenticator's package name is: ".concat(String.valueOf(string)));
        return false;
    }
}
