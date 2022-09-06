package com.amazon.alexa.accessory.capabilities.state;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.state.-$$Lambda$StateCapability$V8PE_O7WTpb-Yh_I4KnaPVdGkPc  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$StateCapability$V8PE_O7WTpbYh_I4KnaPVdGkPc implements Consumer {
    public static final /* synthetic */ $$Lambda$StateCapability$V8PE_O7WTpbYh_I4KnaPVdGkPc INSTANCE = new $$Lambda$StateCapability$V8PE_O7WTpbYh_I4KnaPVdGkPc();

    private /* synthetic */ $$Lambda$StateCapability$V8PE_O7WTpbYh_I4KnaPVdGkPc() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("Failed to synchronize phone state update", (Throwable) obj);
    }
}
