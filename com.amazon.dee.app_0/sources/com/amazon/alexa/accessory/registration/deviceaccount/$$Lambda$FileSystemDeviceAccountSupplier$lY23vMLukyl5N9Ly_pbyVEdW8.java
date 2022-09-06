package com.amazon.alexa.accessory.registration.deviceaccount;

import com.amazon.alexa.accessory.repositories.device.v2.DeviceGroup;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.registration.deviceaccount.-$$Lambda$FileSystemDeviceAccountSupplier$lY2-3vM-Lukyl5N9Ly_pbyVEdW8  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$FileSystemDeviceAccountSupplier$lY23vMLukyl5N9Ly_pbyVEdW8 implements Function {
    public static final /* synthetic */ $$Lambda$FileSystemDeviceAccountSupplier$lY23vMLukyl5N9Ly_pbyVEdW8 INSTANCE = new $$Lambda$FileSystemDeviceAccountSupplier$lY23vMLukyl5N9Ly_pbyVEdW8();

    private /* synthetic */ $$Lambda$FileSystemDeviceAccountSupplier$lY23vMLukyl5N9Ly_pbyVEdW8() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        String type;
        type = ((DeviceGroup) obj).getDeviceWithHighestDeviceId().getType();
        return type;
    }
}
