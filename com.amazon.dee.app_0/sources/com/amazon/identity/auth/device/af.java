package com.amazon.identity.auth.device;

import android.content.Context;
import android.os.Bundle;
import com.amazon.identity.auth.device.api.Callback;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class af extends ct<Bundle> {
    private final Context mContext;

    public af(Callback callback, Context context) {
        super(callback);
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.identity.auth.device.ct
    /* renamed from: b */
    public void a(Callback callback, Bundle bundle) {
        hu.b(this.mContext, bundle);
        m.a(callback, bundle);
    }
}
