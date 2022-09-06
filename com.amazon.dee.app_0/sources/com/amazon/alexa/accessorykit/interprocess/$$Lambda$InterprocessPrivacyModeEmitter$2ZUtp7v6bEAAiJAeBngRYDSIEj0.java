package com.amazon.alexa.accessorykit.interprocess;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$InterprocessPrivacyModeEmitter$2ZUtp7v6bEAAiJAeBngRYDSIEj0  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$InterprocessPrivacyModeEmitter$2ZUtp7v6bEAAiJAeBngRYDSIEj0 implements Consumer {
    public static final /* synthetic */ $$Lambda$InterprocessPrivacyModeEmitter$2ZUtp7v6bEAAiJAeBngRYDSIEj0 INSTANCE = new $$Lambda$InterprocessPrivacyModeEmitter$2ZUtp7v6bEAAiJAeBngRYDSIEj0();

    private /* synthetic */ $$Lambda$InterprocessPrivacyModeEmitter$2ZUtp7v6bEAAiJAeBngRYDSIEj0() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%s received error in emitting privacy mode states", (Throwable) obj, InterprocessPrivacyModeEmitter.TAG);
    }
}
