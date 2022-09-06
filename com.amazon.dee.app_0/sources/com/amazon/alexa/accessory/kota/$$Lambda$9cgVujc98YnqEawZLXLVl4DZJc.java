package com.amazon.alexa.accessory.kota;

import com.amazon.alexa.accessory.repositories.firmware.FirmwareContract;
import io.reactivex.rxjava3.functions.BiFunction;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.kota.-$$Lambda$9cgV-ujc98YnqEawZLXLVl4DZJc  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$9cgVujc98YnqEawZLXLVl4DZJc implements BiFunction {
    public static final /* synthetic */ $$Lambda$9cgVujc98YnqEawZLXLVl4DZJc INSTANCE = new $$Lambda$9cgVujc98YnqEawZLXLVl4DZJc();

    private /* synthetic */ $$Lambda$9cgVujc98YnqEawZLXLVl4DZJc() {
    }

    @Override // io.reactivex.rxjava3.functions.BiFunction
    public final Object apply(Object obj, Object obj2) {
        return FirmwareContract.Package.greaterHop((FirmwareContract.Package) obj, (FirmwareContract.Package) obj2);
    }
}
