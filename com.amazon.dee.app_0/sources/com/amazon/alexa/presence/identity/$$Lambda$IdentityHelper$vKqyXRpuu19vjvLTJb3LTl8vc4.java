package com.amazon.alexa.presence.identity;

import android.util.Log;
import rx.functions.Action1;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.presence.identity.-$$Lambda$IdentityHelper$vKqyXRpuu19vjvLTJb3LTl8vc-4  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$IdentityHelper$vKqyXRpuu19vjvLTJb3LTl8vc4 implements Action1 {
    public static final /* synthetic */ $$Lambda$IdentityHelper$vKqyXRpuu19vjvLTJb3LTl8vc4 INSTANCE = new $$Lambda$IdentityHelper$vKqyXRpuu19vjvLTJb3LTl8vc4();

    private /* synthetic */ $$Lambda$IdentityHelper$vKqyXRpuu19vjvLTJb3LTl8vc4() {
    }

    @Override // rx.functions.Action1
    public final void call(Object obj) {
        Log.e(IdentityHelper.TAG, "failed to get new auth token", (Throwable) obj);
    }
}
