package com.amazon.photos.discovery.internal.dagger.module;

import com.amazon.clouddrive.android.core.interfaces.Logger;
import com.amazon.photos.discovery.internal.dedupe.metadata.DateMatcher;
import com.amazon.photos.discovery.internal.util.NodeUtils;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes13.dex */
public final class DiscoveryModule_ProvideDateMatcherFactory implements Factory<DateMatcher> {
    private final Provider<Logger> loggerProvider;
    private final DiscoveryModule module;
    private final Provider<NodeUtils> nodeUtilsProvider;

    public DiscoveryModule_ProvideDateMatcherFactory(DiscoveryModule discoveryModule, Provider<Logger> provider, Provider<NodeUtils> provider2) {
        this.module = discoveryModule;
        this.loggerProvider = provider;
        this.nodeUtilsProvider = provider2;
    }

    public static DiscoveryModule_ProvideDateMatcherFactory create(DiscoveryModule discoveryModule, Provider<Logger> provider, Provider<NodeUtils> provider2) {
        return new DiscoveryModule_ProvideDateMatcherFactory(discoveryModule, provider, provider2);
    }

    public static DateMatcher provideDateMatcher(DiscoveryModule discoveryModule, Logger logger, NodeUtils nodeUtils) {
        return (DateMatcher) Preconditions.checkNotNull(discoveryModule.provideDateMatcher(logger, nodeUtils), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DateMatcher mo10268get() {
        return provideDateMatcher(this.module, this.loggerProvider.mo10268get(), this.nodeUtilsProvider.mo10268get());
    }
}
