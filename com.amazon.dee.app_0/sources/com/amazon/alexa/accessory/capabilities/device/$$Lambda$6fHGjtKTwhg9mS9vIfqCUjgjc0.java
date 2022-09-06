package com.amazon.alexa.accessory.capabilities.device;

import com.amazon.alexa.accessory.protocol.Accessories;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.device.-$$Lambda$6fHGjtKTwhg9mS9vIfqCU-jgjc0  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$6fHGjtKTwhg9mS9vIfqCUjgjc0 implements Function {
    public static final /* synthetic */ $$Lambda$6fHGjtKTwhg9mS9vIfqCUjgjc0 INSTANCE = new $$Lambda$6fHGjtKTwhg9mS9vIfqCUjgjc0();

    private /* synthetic */ $$Lambda$6fHGjtKTwhg9mS9vIfqCUjgjc0() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return ((Accessories.Response) obj).getDeviceInformation();
    }
}
