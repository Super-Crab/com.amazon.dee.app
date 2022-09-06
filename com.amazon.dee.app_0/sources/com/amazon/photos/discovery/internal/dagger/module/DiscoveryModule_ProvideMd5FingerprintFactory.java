package com.amazon.photos.discovery.internal.dagger.module;

import com.amazon.clouddrive.cdasdk.util.MD5Fingerprint;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DiscoveryModule_ProvideMd5FingerprintFactory implements Factory<MD5Fingerprint> {
    private final DiscoveryModule module;

    public DiscoveryModule_ProvideMd5FingerprintFactory(DiscoveryModule discoveryModule) {
        this.module = discoveryModule;
    }

    public static DiscoveryModule_ProvideMd5FingerprintFactory create(DiscoveryModule discoveryModule) {
        return new DiscoveryModule_ProvideMd5FingerprintFactory(discoveryModule);
    }

    public static MD5Fingerprint provideMd5Fingerprint(DiscoveryModule discoveryModule) {
        return (MD5Fingerprint) Preconditions.checkNotNull(discoveryModule.provideMd5Fingerprint(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MD5Fingerprint mo10268get() {
        return provideMd5Fingerprint(this.module);
    }
}
