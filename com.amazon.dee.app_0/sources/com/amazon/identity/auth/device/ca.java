package com.amazon.identity.auth.device;

import android.content.Context;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public abstract class ca {
    private final ea at;
    private final ed o;

    public ca(Context context) {
        this.o = ed.M(context);
        this.at = (ea) this.o.getSystemService("dcp_device_info");
    }

    public ed bC() {
        return this.o;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ea bD() {
        return this.at;
    }

    public abstract String bo();
}
