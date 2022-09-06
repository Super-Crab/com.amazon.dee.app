package com.amazon.photos.discovery.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.Logger;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DiscoveryModule_ProvideLoggerFactory implements Factory<Logger> {
    private final DiscoveryModule module;

    public DiscoveryModule_ProvideLoggerFactory(DiscoveryModule discoveryModule) {
        this.module = discoveryModule;
    }

    public static DiscoveryModule_ProvideLoggerFactory create(DiscoveryModule discoveryModule) {
        return new DiscoveryModule_ProvideLoggerFactory(discoveryModule);
    }

    public static Logger provideLogger(DiscoveryModule discoveryModule) {
        return (Logger) Preconditions.checkNotNull(discoveryModule.provideLogger(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public Logger mo10268get() {
        return provideLogger(this.module);
    }
}
