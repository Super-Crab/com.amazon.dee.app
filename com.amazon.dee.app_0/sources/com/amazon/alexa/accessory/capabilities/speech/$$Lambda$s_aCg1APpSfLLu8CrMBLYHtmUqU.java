package com.amazon.alexa.accessory.capabilities.speech;

import com.amazon.alexa.accessory.repositories.device.DeviceFeatures;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.speech.-$$Lambda$s_aCg1APpSfLLu8CrMBLYHtmUqU  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$s_aCg1APpSfLLu8CrMBLYHtmUqU implements Function {
    public static final /* synthetic */ $$Lambda$s_aCg1APpSfLLu8CrMBLYHtmUqU INSTANCE = new $$Lambda$s_aCg1APpSfLLu8CrMBLYHtmUqU();

    private /* synthetic */ $$Lambda$s_aCg1APpSfLLu8CrMBLYHtmUqU() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return ((DeviceFeatures) obj).getFeatures();
    }
}
