package com.amazon.dee.app.ui.web;

import com.amazon.dee.app.services.logging.Log;
import rx.functions.Action1;
/* compiled from: lambda */
/* renamed from: com.amazon.dee.app.ui.web.-$$Lambda$OOBEBridge$k7BB9REmaqO5qhlL2LFnTGd6C9s  reason: invalid class name */
/* loaded from: classes12.dex */
public final /* synthetic */ class $$Lambda$OOBEBridge$k7BB9REmaqO5qhlL2LFnTGd6C9s implements Action1 {
    public static final /* synthetic */ $$Lambda$OOBEBridge$k7BB9REmaqO5qhlL2LFnTGd6C9s INSTANCE = new $$Lambda$OOBEBridge$k7BB9REmaqO5qhlL2LFnTGd6C9s();

    private /* synthetic */ $$Lambda$OOBEBridge$k7BB9REmaqO5qhlL2LFnTGd6C9s() {
    }

    @Override // rx.functions.Action1
    public final void call(Object obj) {
        Log.e(OOBEBridge.TAG, (Throwable) obj, "Failed to sign out", new Object[0]);
    }
}
