package com.amazon.alexa.location;

import android.util.Log;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$8Bx76bRY9yr4RSpanhpSulpPdIQ  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$DefaultLocationService$8Bx76bRY9yr4RSpanhpSulpPdIQ implements Consumer {
    public static final /* synthetic */ $$Lambda$DefaultLocationService$8Bx76bRY9yr4RSpanhpSulpPdIQ INSTANCE = new $$Lambda$DefaultLocationService$8Bx76bRY9yr4RSpanhpSulpPdIQ();

    private /* synthetic */ $$Lambda$DefaultLocationService$8Bx76bRY9yr4RSpanhpSulpPdIQ() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Log.w(DefaultLocationService.TAG, "Failed to clear geofences when feature became unavailable.", (Throwable) obj);
    }
}
