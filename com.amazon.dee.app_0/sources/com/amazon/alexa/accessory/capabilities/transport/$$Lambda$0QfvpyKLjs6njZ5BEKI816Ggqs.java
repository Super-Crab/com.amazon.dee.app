package com.amazon.alexa.accessory.capabilities.transport;

import com.amazon.alexa.accessory.protocol.Device;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.transport.-$$Lambda$0-QfvpyKLjs6njZ5BEKI816Ggqs  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$0QfvpyKLjs6njZ5BEKI816Ggqs implements Function {
    public static final /* synthetic */ $$Lambda$0QfvpyKLjs6njZ5BEKI816Ggqs INSTANCE = new $$Lambda$0QfvpyKLjs6njZ5BEKI816Ggqs();

    private /* synthetic */ $$Lambda$0QfvpyKLjs6njZ5BEKI816Ggqs() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return ((Device.DeviceInformation) obj).getSupportedTransportsList();
    }
}
