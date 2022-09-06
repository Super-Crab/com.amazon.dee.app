package com.amazon.alexa.accessory.internal.interactor;

import com.amazon.alexa.accessory.repositories.device.v2.DeviceRepositoryV2;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.interactor.-$$Lambda$2v2_5L05H-BG2KNqNXx7ppY4-Hg  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$2v2_5L05HBG2KNqNXx7ppY4Hg implements Function {
    public static final /* synthetic */ $$Lambda$2v2_5L05HBG2KNqNXx7ppY4Hg INSTANCE = new $$Lambda$2v2_5L05HBG2KNqNXx7ppY4Hg();

    private /* synthetic */ $$Lambda$2v2_5L05HBG2KNqNXx7ppY4Hg() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return ((DeviceRepositoryV2) obj).queryDeviceInformationSet();
    }
}
