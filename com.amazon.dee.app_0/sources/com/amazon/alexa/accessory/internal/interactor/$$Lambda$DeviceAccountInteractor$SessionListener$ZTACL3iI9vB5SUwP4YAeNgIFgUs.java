package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceAccountInteractor$SessionListener$ZTACL3iI9vB5SUwP4YAeNgIFgUs  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$DeviceAccountInteractor$SessionListener$ZTACL3iI9vB5SUwP4YAeNgIFgUs implements Consumer {
    public static final /* synthetic */ $$Lambda$DeviceAccountInteractor$SessionListener$ZTACL3iI9vB5SUwP4YAeNgIFgUs INSTANCE = new $$Lambda$DeviceAccountInteractor$SessionListener$ZTACL3iI9vB5SUwP4YAeNgIFgUs();

    private /* synthetic */ $$Lambda$DeviceAccountInteractor$SessionListener$ZTACL3iI9vB5SUwP4YAeNgIFgUs() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%s: Failure handling 1p accessory registrations for device accounts", (Throwable) obj, DeviceAccountInteractor.TAG);
    }
}
