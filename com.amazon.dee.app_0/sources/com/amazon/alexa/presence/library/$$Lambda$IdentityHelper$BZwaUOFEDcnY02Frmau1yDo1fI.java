package com.amazon.alexa.presence.library;

import android.util.Log;
import rx.functions.Action1;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.presence.library.-$$Lambda$IdentityHelper$BZwaUOFEDcnY02Frmau1y-Do1fI  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$IdentityHelper$BZwaUOFEDcnY02Frmau1yDo1fI implements Action1 {
    public static final /* synthetic */ $$Lambda$IdentityHelper$BZwaUOFEDcnY02Frmau1yDo1fI INSTANCE = new $$Lambda$IdentityHelper$BZwaUOFEDcnY02Frmau1yDo1fI();

    private /* synthetic */ $$Lambda$IdentityHelper$BZwaUOFEDcnY02Frmau1yDo1fI() {
    }

    @Override // rx.functions.Action1
    public final void call(Object obj) {
        Log.e(IdentityHelper.TAG, "failed to get new auth token", (Throwable) obj);
    }
}
