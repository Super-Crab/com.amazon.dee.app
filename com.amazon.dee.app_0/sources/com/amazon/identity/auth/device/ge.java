package com.amazon.identity.auth.device;

import android.content.Context;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class ge {
    private final ds bb;
    private final Context mContext;

    public ge(Context context) {
        this.mContext = context;
        this.bb = (ds) context.getSystemService("sso_platform");
    }

    public gd fa() {
        if (this.bb.dm()) {
            return new gr(this.mContext);
        }
        return new gl(this.mContext);
    }
}
