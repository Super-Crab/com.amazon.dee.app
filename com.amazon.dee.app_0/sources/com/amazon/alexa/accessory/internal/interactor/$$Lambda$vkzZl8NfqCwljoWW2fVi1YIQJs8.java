package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.internal.util.AccessoryUtils;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$vkzZl8NfqCwljoWW2fVi1YIQJs8  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$vkzZl8NfqCwljoWW2fVi1YIQJs8 implements Function {
    public static final /* synthetic */ $$Lambda$vkzZl8NfqCwljoWW2fVi1YIQJs8 INSTANCE = new $$Lambda$vkzZl8NfqCwljoWW2fVi1YIQJs8();

    private /* synthetic */ $$Lambda$vkzZl8NfqCwljoWW2fVi1YIQJs8() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return AccessoryUtils.getAccessoryFromDeviceGroup((DeviceGroup) obj);
    }
}
