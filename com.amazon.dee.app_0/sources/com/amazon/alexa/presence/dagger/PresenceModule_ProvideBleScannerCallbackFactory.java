package com.amazon.alexa.presence.dagger;

import com.amazon.alexa.presence.detection.BLEScannerCallback;
import com.amazon.alexa.presence.detection.BlePacketConsumer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes9.dex */
public final class PresenceModule_ProvideBleScannerCallbackFactory implements Factory<BLEScannerCallback> {
    private final Provider<BlePacketConsumer> blePacketConsumerProvider;
    private final PresenceModule module;

    public PresenceModule_ProvideBleScannerCallbackFactory(PresenceModule presenceModule, Provider<BlePacketConsumer> provider) {
        this.module = presenceModule;
        this.blePacketConsumerProvider = provider;
    }

    public static PresenceModule_ProvideBleScannerCallbackFactory create(PresenceModule presenceModule, Provider<BlePacketConsumer> provider) {
        return new PresenceModule_ProvideBleScannerCallbackFactory(presenceModule, provider);
    }

    public static BLEScannerCallback provideInstance(PresenceModule presenceModule, Provider<BlePacketConsumer> provider) {
        return proxyProvideBleScannerCallback(presenceModule, provider.mo10268get());
    }

    public static BLEScannerCallback proxyProvideBleScannerCallback(PresenceModule presenceModule, BlePacketConsumer blePacketConsumer) {
        return (BLEScannerCallback) Preconditions.checkNotNull(presenceModule.provideBleScannerCallback(blePacketConsumer), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BLEScannerCallback mo10268get() {
        return provideInstance(this.module, this.blePacketConsumerProvider);
    }
}
