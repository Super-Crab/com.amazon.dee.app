package com.amazon.identity.auth.device;

import android.content.Context;
import com.amazon.identity.auth.device.features.Feature;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class cq extends co {
    private final Context mContext;

    public cq(Context context) {
        if (context != null) {
            this.mContext = context;
            return;
        }
        throw new IllegalArgumentException("context is null");
    }

    @Override // com.amazon.identity.auth.device.co
    public boolean a(Feature feature) {
        return feature.fetchValue(this.mContext);
    }
}
