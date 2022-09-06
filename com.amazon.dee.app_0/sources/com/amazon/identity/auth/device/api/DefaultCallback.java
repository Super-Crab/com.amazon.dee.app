package com.amazon.identity.auth.device.api;

import android.os.Bundle;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public class DefaultCallback implements Callback {
    private static final DefaultCallback fY = new DefaultCallback();

    public static Callback nullToDefault(Callback callback) {
        return callback == null ? fY : callback;
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    @FireOsSdk
    public void onError(Bundle bundle) {
    }

    @Override // com.amazon.identity.auth.device.api.Callback
    @FireOsSdk
    public void onSuccess(Bundle bundle) {
    }
}
