package com.amazon.alexa.accessory.kota;

import com.amazon.alexa.accessory.repositories.firmware.FirmwareContract;
import io.reactivex.rxjava3.functions.Function;
import java.io.File;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.kota.-$$Lambda$e7lA72LN2YIWgHn56sDoVpDP1t0  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$e7lA72LN2YIWgHn56sDoVpDP1t0 implements Function {
    public static final /* synthetic */ $$Lambda$e7lA72LN2YIWgHn56sDoVpDP1t0 INSTANCE = new $$Lambda$e7lA72LN2YIWgHn56sDoVpDP1t0();

    private /* synthetic */ $$Lambda$e7lA72LN2YIWgHn56sDoVpDP1t0() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return FirmwareContract.Package.create((File) obj);
    }
}
