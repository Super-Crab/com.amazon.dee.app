package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceAccountInteractor$7Mse2OzzO-StOiIZvBCRjZ57n-o  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$DeviceAccountInteractor$7Mse2OzzOStOiIZvBCRjZ57no implements Consumer {
    public static final /* synthetic */ $$Lambda$DeviceAccountInteractor$7Mse2OzzOStOiIZvBCRjZ57no INSTANCE = new $$Lambda$DeviceAccountInteractor$7Mse2OzzOStOiIZvBCRjZ57no();

    private /* synthetic */ $$Lambda$DeviceAccountInteractor$7Mse2OzzOStOiIZvBCRjZ57no() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%s: Login observer onError", DeviceAccountInteractor.TAG, (Throwable) obj);
    }
}
