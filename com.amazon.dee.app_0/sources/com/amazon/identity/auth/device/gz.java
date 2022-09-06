package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.identity.auth.device.api.Callback;
import com.amazon.identity.auth.device.api.MAPFuture;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class gz {
    private hh gL;
    private final Context mContext;

    public gz(@NonNull Context context) {
        this.mContext = context;
    }

    private synchronized hh bl() {
        if (this.gL == null) {
            this.gL = hi.ad(this.mContext);
        }
        return this.gL;
    }

    public MAPFuture<Bundle> a(String str, String str2, Bundle bundle, Callback callback, gy gyVar) {
        ej by = ej.by("TokenManagement:UpgradeToken");
        return bl().a(str, str2, bundle, mq.a(by, by.f(this.mContext, "Time"), callback, gyVar), gyVar, by);
    }
}
