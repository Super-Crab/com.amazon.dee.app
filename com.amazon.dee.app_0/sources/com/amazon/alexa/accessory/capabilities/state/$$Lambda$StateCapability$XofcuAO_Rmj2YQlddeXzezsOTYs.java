package com.amazon.alexa.accessory.capabilities.state;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.state.-$$Lambda$StateCapability$XofcuAO_Rmj2YQlddeXzezsOTYs  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$StateCapability$XofcuAO_Rmj2YQlddeXzezsOTYs implements Consumer {
    public static final /* synthetic */ $$Lambda$StateCapability$XofcuAO_Rmj2YQlddeXzezsOTYs INSTANCE = new $$Lambda$StateCapability$XofcuAO_Rmj2YQlddeXzezsOTYs();

    private /* synthetic */ $$Lambda$StateCapability$XofcuAO_Rmj2YQlddeXzezsOTYs() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.d("Synchronized phone states %s update with accessory", (StateOuterClass.State) obj);
    }
}
