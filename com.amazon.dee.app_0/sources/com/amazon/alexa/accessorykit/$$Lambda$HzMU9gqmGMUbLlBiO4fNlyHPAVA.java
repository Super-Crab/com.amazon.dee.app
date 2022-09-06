package com.amazon.alexa.accessorykit;

import com.amazon.alexa.accessory.protocol.Device;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.-$$Lambda$HzMU9gqmGMUbLlBiO4fNlyHPAVA  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$HzMU9gqmGMUbLlBiO4fNlyHPAVA implements Function {
    public static final /* synthetic */ $$Lambda$HzMU9gqmGMUbLlBiO4fNlyHPAVA INSTANCE = new $$Lambda$HzMU9gqmGMUbLlBiO4fNlyHPAVA();

    private /* synthetic */ $$Lambda$HzMU9gqmGMUbLlBiO4fNlyHPAVA() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return Boolean.valueOf(((Device.DeviceConfiguration) obj).getNeedsAssistantOverride());
    }
}
