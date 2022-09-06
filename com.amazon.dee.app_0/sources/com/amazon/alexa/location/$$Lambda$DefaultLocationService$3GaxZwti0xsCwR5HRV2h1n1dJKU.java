package com.amazon.alexa.location;

import android.util.Log;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$3GaxZwti0xsCwR5HRV2h1n1dJKU  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$DefaultLocationService$3GaxZwti0xsCwR5HRV2h1n1dJKU implements Consumer {
    public static final /* synthetic */ $$Lambda$DefaultLocationService$3GaxZwti0xsCwR5HRV2h1n1dJKU INSTANCE = new $$Lambda$DefaultLocationService$3GaxZwti0xsCwR5HRV2h1n1dJKU();

    private /* synthetic */ $$Lambda$DefaultLocationService$3GaxZwti0xsCwR5HRV2h1n1dJKU() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Log.e(DefaultLocationService.TAG, "[ERROR] Fail to sync geofences onResume", (Throwable) obj);
    }
}
