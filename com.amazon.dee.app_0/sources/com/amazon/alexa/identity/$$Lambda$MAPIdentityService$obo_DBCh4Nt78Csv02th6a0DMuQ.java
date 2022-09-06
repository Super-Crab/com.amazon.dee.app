package com.amazon.alexa.identity;

import android.util.Log;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.identity.-$$Lambda$MAPIdentityService$obo_DBCh4Nt78Csv02th6a0DMuQ  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$MAPIdentityService$obo_DBCh4Nt78Csv02th6a0DMuQ implements Consumer {
    public static final /* synthetic */ $$Lambda$MAPIdentityService$obo_DBCh4Nt78Csv02th6a0DMuQ INSTANCE = new $$Lambda$MAPIdentityService$obo_DBCh4Nt78Csv02th6a0DMuQ();

    private /* synthetic */ $$Lambda$MAPIdentityService$obo_DBCh4Nt78Csv02th6a0DMuQ() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Log.e(MAPIdentityService.TAG, "IdentityV2 : Error updating user", (Throwable) obj);
    }
}
