package com.amazon.alexa.location;

import android.util.Log;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.location.-$$Lambda$DefaultLocationService$iIkgWP4Zm54xS3TanCWfeFf-Z34  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$DefaultLocationService$iIkgWP4Zm54xS3TanCWfeFfZ34 implements Consumer {
    public static final /* synthetic */ $$Lambda$DefaultLocationService$iIkgWP4Zm54xS3TanCWfeFfZ34 INSTANCE = new $$Lambda$DefaultLocationService$iIkgWP4Zm54xS3TanCWfeFfZ34();

    private /* synthetic */ $$Lambda$DefaultLocationService$iIkgWP4Zm54xS3TanCWfeFfZ34() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Log.w(DefaultLocationService.TAG, "Failed to sync geofences when feature became available.", (Throwable) obj);
    }
}
