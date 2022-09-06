package com.amazon.alexa.accessory.avsclient.context;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaTrustedStatesSupplier$yYPs4ZJH6_-IEfCjoxALF-6ufv4  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AlexaTrustedStatesSupplier$yYPs4ZJH6_IEfCjoxALF6ufv4 implements Consumer {
    public static final /* synthetic */ $$Lambda$AlexaTrustedStatesSupplier$yYPs4ZJH6_IEfCjoxALF6ufv4 INSTANCE = new $$Lambda$AlexaTrustedStatesSupplier$yYPs4ZJH6_IEfCjoxALF6ufv4();

    private /* synthetic */ $$Lambda$AlexaTrustedStatesSupplier$yYPs4ZJH6_IEfCjoxALF6ufv4() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%s caught an error while observing session", (Throwable) obj, AlexaTrustedStatesSupplier.TAG);
    }
}
