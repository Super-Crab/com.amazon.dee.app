package com.amazon.identity.auth.device.endpoint;

import android.os.Bundle;
import android.text.TextUtils;
import com.amazon.identity.auth.device.env.EnvironmentUtils;
import com.amazon.identity.auth.device.io;
import java.util.Locale;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class TokenRequestHelpers {
    private static final String TAG = "com.amazon.identity.auth.device.endpoint.TokenRequestHelpers";

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    public enum PROTOCOL {
        HTTPS;

        @Override // java.lang.Enum
        public String toString() {
            return super.toString().toLowerCase(Locale.US) + "://";
        }
    }

    private TokenRequestHelpers() {
    }

    public static String a(Bundle bundle, PROTOCOL protocol, String str) {
        if (TextUtils.isEmpty(str)) {
            io.dm(TAG);
            if (bundle != null) {
                str = EnvironmentUtils.cd().x(bundle);
            } else {
                str = EnvironmentUtils.cd().cl();
            }
        }
        return protocol.toString() + str;
    }
}
