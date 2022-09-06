package com.amazon.alexa.accessory.avsclient.context;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaTrustedStatesSupplier$-mHAILKnEPTeqJUGdOdeWpBwvso  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AlexaTrustedStatesSupplier$mHAILKnEPTeqJUGdOdeWpBwvso implements Consumer {
    public static final /* synthetic */ $$Lambda$AlexaTrustedStatesSupplier$mHAILKnEPTeqJUGdOdeWpBwvso INSTANCE = new $$Lambda$AlexaTrustedStatesSupplier$mHAILKnEPTeqJUGdOdeWpBwvso();

    private /* synthetic */ $$Lambda$AlexaTrustedStatesSupplier$mHAILKnEPTeqJUGdOdeWpBwvso() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%s Critical: Caught error in observeLockState()", (Throwable) obj, AlexaTrustedStatesSupplier.TAG);
    }
}
