package com.amazon.alexa.accessory.avsclient.context;

import android.util.Pair;
import com.amazon.alexa.accessory.registration.DeviceRegistration;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.context.-$$Lambda$AlexaTrustedStatesSupplier$jMsPRUZwjpvTluTw0Mrt8l5mKmY  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AlexaTrustedStatesSupplier$jMsPRUZwjpvTluTw0Mrt8l5mKmY implements Function {
    public static final /* synthetic */ $$Lambda$AlexaTrustedStatesSupplier$jMsPRUZwjpvTluTw0Mrt8l5mKmY INSTANCE = new $$Lambda$AlexaTrustedStatesSupplier$jMsPRUZwjpvTluTw0Mrt8l5mKmY();

    private /* synthetic */ $$Lambda$AlexaTrustedStatesSupplier$jMsPRUZwjpvTluTw0Mrt8l5mKmY() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        Pair extractDeviceInformation;
        extractDeviceInformation = AlexaTrustedStatesSupplier.extractDeviceInformation((DeviceRegistration) obj);
        return extractDeviceInformation;
    }
}
