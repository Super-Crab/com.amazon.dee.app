package com.amazon.alexa.location;

import android.util.Log;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$btl8dWaMXtrDI8DtzC9HBhXb_8k  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$DefaultLocationService$btl8dWaMXtrDI8DtzC9HBhXb_8k implements Consumer {
    public static final /* synthetic */ $$Lambda$DefaultLocationService$btl8dWaMXtrDI8DtzC9HBhXb_8k INSTANCE = new $$Lambda$DefaultLocationService$btl8dWaMXtrDI8DtzC9HBhXb_8k();

    private /* synthetic */ $$Lambda$DefaultLocationService$btl8dWaMXtrDI8DtzC9HBhXb_8k() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Log.e(DefaultLocationService.TAG, "[ERROR] Fail to sync geofences onCreate", (Throwable) obj);
    }
}
