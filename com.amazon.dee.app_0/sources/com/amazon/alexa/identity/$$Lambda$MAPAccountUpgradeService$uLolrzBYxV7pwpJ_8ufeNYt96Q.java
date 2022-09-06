package com.amazon.alexa.identity;

import android.util.Log;
import rx.functions.Action1;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.identity.-$$Lambda$MAPAccountUpgradeService$uLolrzBYx-V7pwpJ_8ufeNYt96Q  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$MAPAccountUpgradeService$uLolrzBYxV7pwpJ_8ufeNYt96Q implements Action1 {
    public static final /* synthetic */ $$Lambda$MAPAccountUpgradeService$uLolrzBYxV7pwpJ_8ufeNYt96Q INSTANCE = new $$Lambda$MAPAccountUpgradeService$uLolrzBYxV7pwpJ_8ufeNYt96Q();

    private /* synthetic */ $$Lambda$MAPAccountUpgradeService$uLolrzBYxV7pwpJ_8ufeNYt96Q() {
    }

    @Override // rx.functions.Action1
    public final void call(Object obj) {
        Log.e(MAPAccountUpgradeService.TAG, "IdentityV2 : Error on registerAccountFromToken.", (Throwable) obj);
    }
}
