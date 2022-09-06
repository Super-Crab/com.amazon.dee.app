package com.amazon.identity.auth.device;

import android.os.Bundle;
import com.amazon.identity.auth.device.api.AuthenticationMethod;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class jp {
    public static final long rM = TimeUnit.MILLISECONDS.convert(20, TimeUnit.SECONDS);

    public static Map<String, String> P(Bundle bundle) {
        HashMap hashMap = new HashMap();
        Bundle bundle2 = bundle.getBundle(AuthenticationMethod.KEY_AUTH_HEADERS);
        if (bundle2 == null) {
            return hashMap;
        }
        for (String str : bundle2.keySet()) {
            hashMap.put(str, bundle2.getString(str));
        }
        return hashMap;
    }

    public static void b(Bundle bundle, String str, String str2) {
        Bundle bundle2 = bundle.getBundle(AuthenticationMethod.KEY_AUTH_HEADERS);
        if (bundle2 == null) {
            bundle2 = new Bundle();
            bundle.putBundle(AuthenticationMethod.KEY_AUTH_HEADERS, bundle2);
        }
        bundle2.putString(str, str2);
    }
}
