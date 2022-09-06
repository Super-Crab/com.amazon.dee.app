package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$CompanionDeviceInteractor$QKKnKporeDTl9kbpuw1r-WagEkI  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$CompanionDeviceInteractor$QKKnKporeDTl9kbpuw1rWagEkI implements Consumer {
    public static final /* synthetic */ $$Lambda$CompanionDeviceInteractor$QKKnKporeDTl9kbpuw1rWagEkI INSTANCE = new $$Lambda$CompanionDeviceInteractor$QKKnKporeDTl9kbpuw1rWagEkI();

    private /* synthetic */ $$Lambda$CompanionDeviceInteractor$QKKnKporeDTl9kbpuw1rWagEkI() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Throwable th = (Throwable) obj;
        Logger.e("CompanionDeviceInteractor: CRITICAL: failed to listen for removed devices");
    }
}
