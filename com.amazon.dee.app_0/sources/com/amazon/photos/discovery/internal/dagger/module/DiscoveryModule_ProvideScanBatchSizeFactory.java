package com.amazon.photos.discovery.internal.dagger.module;

import dagger.internal.Factory;
/* loaded from: classes13.dex */
public final class DiscoveryModule_ProvideScanBatchSizeFactory implements Factory<Integer> {
    private final DiscoveryModule module;

    public DiscoveryModule_ProvideScanBatchSizeFactory(DiscoveryModule discoveryModule) {
        this.module = discoveryModule;
    }

    public static DiscoveryModule_ProvideScanBatchSizeFactory create(DiscoveryModule discoveryModule) {
        return new DiscoveryModule_ProvideScanBatchSizeFactory(discoveryModule);
    }

    public static int provideScanBatchSize(DiscoveryModule discoveryModule) {
        return discoveryModule.provideScanBatchSize();
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Integer mo10268get() {
        return Integer.valueOf(provideScanBatchSize(this.module));
    }
}
