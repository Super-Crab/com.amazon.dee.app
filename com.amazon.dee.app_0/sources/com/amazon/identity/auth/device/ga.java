package com.amazon.identity.auth.device;

import android.content.Context;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ga {
    private static final String TAG = "com.amazon.identity.auth.device.ga";
    private final Context mContext;
    private final gg w;

    public ga(Context context) {
        this.mContext = ed.M(context.getApplicationContext());
        this.w = ((gh) this.mContext.getSystemService("dcp_data_storage_factory")).dV();
    }

    public br eR() {
        return new bv(this.mContext);
    }
}
