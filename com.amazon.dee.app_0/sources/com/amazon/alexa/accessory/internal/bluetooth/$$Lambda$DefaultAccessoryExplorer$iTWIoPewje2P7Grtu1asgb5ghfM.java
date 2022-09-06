package com.amazon.alexa.accessory.internal.bluetooth;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryExplorer$iTWIoPewje2P7Grtu1asgb5ghfM  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$DefaultAccessoryExplorer$iTWIoPewje2P7Grtu1asgb5ghfM implements Consumer {
    public static final /* synthetic */ $$Lambda$DefaultAccessoryExplorer$iTWIoPewje2P7Grtu1asgb5ghfM INSTANCE = new $$Lambda$DefaultAccessoryExplorer$iTWIoPewje2P7Grtu1asgb5ghfM();

    private /* synthetic */ $$Lambda$DefaultAccessoryExplorer$iTWIoPewje2P7Grtu1asgb5ghfM() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("DefaultAccessoryExplorer: Failed to query standby accessories", (Throwable) obj);
    }
}
