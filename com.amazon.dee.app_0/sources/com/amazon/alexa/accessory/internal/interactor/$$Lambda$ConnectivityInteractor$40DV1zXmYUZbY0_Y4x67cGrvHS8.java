package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$ConnectivityInteractor$40DV1zXmYUZbY0_Y4x67cGrvHS8  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$ConnectivityInteractor$40DV1zXmYUZbY0_Y4x67cGrvHS8 implements Consumer {
    public static final /* synthetic */ $$Lambda$ConnectivityInteractor$40DV1zXmYUZbY0_Y4x67cGrvHS8 INSTANCE = new $$Lambda$ConnectivityInteractor$40DV1zXmYUZbY0_Y4x67cGrvHS8();

    private /* synthetic */ $$Lambda$ConnectivityInteractor$40DV1zXmYUZbY0_Y4x67cGrvHS8() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Throwable th = (Throwable) obj;
        Logger.e("ConnectivityInteractor: CRITICAL: Error in querying user change");
    }
}
