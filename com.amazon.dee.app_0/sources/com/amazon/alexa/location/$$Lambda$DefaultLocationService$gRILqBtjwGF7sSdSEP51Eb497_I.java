package com.amazon.alexa.location;

import android.util.Log;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$gRILqBtjwGF7sSdSEP51Eb497_I  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$DefaultLocationService$gRILqBtjwGF7sSdSEP51Eb497_I implements Consumer {
    public static final /* synthetic */ $$Lambda$DefaultLocationService$gRILqBtjwGF7sSdSEP51Eb497_I INSTANCE = new $$Lambda$DefaultLocationService$gRILqBtjwGF7sSdSEP51Eb497_I();

    private /* synthetic */ $$Lambda$DefaultLocationService$gRILqBtjwGF7sSdSEP51Eb497_I() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Log.e(DefaultLocationService.TAG, "getLocationSettings() failed", (Throwable) obj);
    }
}
