package com.amazon.identity.auth.device;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.amazon.identity.auth.device.utils.AccountConstants;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class o extends eb {
    public o(ComponentName componentName) {
        super(componentName);
    }

    public static o d(Context context) {
        ComponentName a = eb.a(context, "com.amazon.dcp.sso.AuthenticatedRequestService", eb.lg);
        if (a == null) {
            return null;
        }
        return new o(a);
    }

    public Intent s() {
        return bw(AccountConstants.INTENT_ACTION_GET_DEVICE_CREDENTIALS);
    }
}
