package com.amazon.dee.app.services.coral;

import com.amazon.dee.app.services.logging.Log;
import rx.functions.Action1;
/* compiled from: lambda */
/* renamed from: com.amazon.dee.app.services.coral.-$$Lambda$HttpCoralAuthenticationResponseInterceptor$NAMe_69121SdY3gEHq0xcSHKweI  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$HttpCoralAuthenticationResponseInterceptor$NAMe_69121SdY3gEHq0xcSHKweI implements Action1 {
    public static final /* synthetic */ $$Lambda$HttpCoralAuthenticationResponseInterceptor$NAMe_69121SdY3gEHq0xcSHKweI INSTANCE = new $$Lambda$HttpCoralAuthenticationResponseInterceptor$NAMe_69121SdY3gEHq0xcSHKweI();

    private /* synthetic */ $$Lambda$HttpCoralAuthenticationResponseInterceptor$NAMe_69121SdY3gEHq0xcSHKweI() {
    }

    @Override // rx.functions.Action1
    public final void call(Object obj) {
        Log.e(HttpCoralAuthenticationResponseInterceptor.TAG, (Throwable) obj, "Failed to sign out", new Object[0]);
    }
}
