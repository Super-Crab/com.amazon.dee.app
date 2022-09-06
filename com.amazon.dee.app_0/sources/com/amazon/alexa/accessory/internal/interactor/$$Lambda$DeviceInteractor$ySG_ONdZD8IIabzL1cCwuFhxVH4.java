package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$DeviceInteractor$ySG_ONdZD8IIabzL1cCwuFhxVH4  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$DeviceInteractor$ySG_ONdZD8IIabzL1cCwuFhxVH4 implements Consumer {
    public static final /* synthetic */ $$Lambda$DeviceInteractor$ySG_ONdZD8IIabzL1cCwuFhxVH4 INSTANCE = new $$Lambda$DeviceInteractor$ySG_ONdZD8IIabzL1cCwuFhxVH4();

    private /* synthetic */ $$Lambda$DeviceInteractor$ySG_ONdZD8IIabzL1cCwuFhxVH4() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.d("DeviceInteractor device no longer paired. Marking for removal %s", (DeviceGroup) obj);
    }
}
