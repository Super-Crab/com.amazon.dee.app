package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class g {
    public static f a(Context context) {
        if (hx.ak(context)) {
            return (CentralAccountManagerCommunication) context.getSystemService("sso_map_account_manager_communicator");
        }
        return h.b(context);
    }
}
