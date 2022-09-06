package com.amazon.alexa.accessory.internal.bluetooth;

import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$BawggZJfidgCs-2CntNc8YXhpEU  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$BawggZJfidgCs2CntNc8YXhpEU implements Function {
    public static final /* synthetic */ $$Lambda$BawggZJfidgCs2CntNc8YXhpEU INSTANCE = new $$Lambda$BawggZJfidgCs2CntNc8YXhpEU();

    private /* synthetic */ $$Lambda$BawggZJfidgCs2CntNc8YXhpEU() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return ((DeviceGroup) obj).getIdentifier();
    }
}
