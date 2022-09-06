package com.amazon.identity.auth.device.api;

import android.os.Bundle;
import com.amazon.fireos.sdk.annotations.FireOsSdk;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public interface Callback {
    @FireOsSdk
    void onError(Bundle bundle);

    @FireOsSdk
    void onSuccess(Bundle bundle);
}
