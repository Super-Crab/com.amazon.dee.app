package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$nXbGl_Fend_M8Xhczr8WDr7GTYo  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$nXbGl_Fend_M8Xhczr8WDr7GTYo implements Function {
    public static final /* synthetic */ $$Lambda$nXbGl_Fend_M8Xhczr8WDr7GTYo INSTANCE = new $$Lambda$nXbGl_Fend_M8Xhczr8WDr7GTYo();

    private /* synthetic */ $$Lambda$nXbGl_Fend_M8Xhczr8WDr7GTYo() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return Long.valueOf(((DeviceGroup) obj).getId());
    }
}
