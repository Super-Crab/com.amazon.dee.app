package com.amazon.dee.app.elements.bridges;

import com.amazon.dee.app.services.logging.Log;
import rx.functions.Action1;
/* compiled from: lambda */
/* renamed from: com.amazon.dee.app.elements.bridges.-$$Lambda$IdentityModule$GehtPS6vpllpEkR77lpY3Udnq08  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$IdentityModule$GehtPS6vpllpEkR77lpY3Udnq08 implements Action1 {
    public static final /* synthetic */ $$Lambda$IdentityModule$GehtPS6vpllpEkR77lpY3Udnq08 INSTANCE = new $$Lambda$IdentityModule$GehtPS6vpllpEkR77lpY3Udnq08();

    private /* synthetic */ $$Lambda$IdentityModule$GehtPS6vpllpEkR77lpY3Udnq08() {
    }

    @Override // rx.functions.Action1
    public final void call(Object obj) {
        Log.e(IdentityModule.TAG, (Throwable) obj, "Failed to sign out", new Object[0]);
    }
}
