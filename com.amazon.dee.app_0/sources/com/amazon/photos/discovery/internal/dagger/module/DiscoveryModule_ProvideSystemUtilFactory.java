package com.amazon.photos.discovery.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.SystemUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DiscoveryModule_ProvideSystemUtilFactory implements Factory<SystemUtil> {
    private final DiscoveryModule module;

    public DiscoveryModule_ProvideSystemUtilFactory(DiscoveryModule discoveryModule) {
        this.module = discoveryModule;
    }

    public static DiscoveryModule_ProvideSystemUtilFactory create(DiscoveryModule discoveryModule) {
        return new DiscoveryModule_ProvideSystemUtilFactory(discoveryModule);
    }

    public static SystemUtil provideSystemUtil(DiscoveryModule discoveryModule) {
        return (SystemUtil) Preconditions.checkNotNull(discoveryModule.provideSystemUtil(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public SystemUtil mo10268get() {
        return provideSystemUtil(this.module);
    }
}
