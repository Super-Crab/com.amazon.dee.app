package com.amazon.alexa.accessory.capabilities.firmware;

import com.amazon.alexa.accessory.repositories.firmware.FirmwareComponentSupplier;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.firmware.-$$Lambda$9AsuFbl7Ei-_ym27UcWYJR5AAF0  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$9AsuFbl7Ei_ym27UcWYJR5AAF0 implements Function {
    public static final /* synthetic */ $$Lambda$9AsuFbl7Ei_ym27UcWYJR5AAF0 INSTANCE = new $$Lambda$9AsuFbl7Ei_ym27UcWYJR5AAF0();

    private /* synthetic */ $$Lambda$9AsuFbl7Ei_ym27UcWYJR5AAF0() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return ((FirmwareComponentSupplier) obj).getName();
    }
}
