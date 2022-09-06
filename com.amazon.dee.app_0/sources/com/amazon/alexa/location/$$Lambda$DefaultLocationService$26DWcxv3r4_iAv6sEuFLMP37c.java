package com.amazon.alexa.location;

import android.util.Log;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$26DWcxv3r4_iAv6sEu-F-LMP37c  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$DefaultLocationService$26DWcxv3r4_iAv6sEuFLMP37c implements Consumer {
    public static final /* synthetic */ $$Lambda$DefaultLocationService$26DWcxv3r4_iAv6sEuFLMP37c INSTANCE = new $$Lambda$DefaultLocationService$26DWcxv3r4_iAv6sEuFLMP37c();

    private /* synthetic */ $$Lambda$DefaultLocationService$26DWcxv3r4_iAv6sEuFLMP37c() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Log.w(DefaultLocationService.TAG, "Failed to sync geofences after PID change.", (Throwable) obj);
    }
}
