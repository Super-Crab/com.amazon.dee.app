package com.amazon.deecomms.core;

import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.conversation.CommsDynamicFeatureService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ApplicationModule_ProvidesCommsDynamicFeatureServiceFactory implements Factory<CommsDynamicFeatureService> {
    private final Provider<CommsManager> commsManagerProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvidesCommsDynamicFeatureServiceFactory(ApplicationModule applicationModule, Provider<CommsManager> provider) {
        this.module = applicationModule;
        this.commsManagerProvider = provider;
    }

    public static ApplicationModule_ProvidesCommsDynamicFeatureServiceFactory create(ApplicationModule applicationModule, Provider<CommsManager> provider) {
        return new ApplicationModule_ProvidesCommsDynamicFeatureServiceFactory(applicationModule, provider);
    }

    public static CommsDynamicFeatureService provideInstance(ApplicationModule applicationModule, Provider<CommsManager> provider) {
        return (CommsDynamicFeatureService) Preconditions.checkNotNull(applicationModule.providesCommsDynamicFeatureService(provider.mo10268get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static CommsDynamicFeatureService proxyProvidesCommsDynamicFeatureService(ApplicationModule applicationModule, CommsManager commsManager) {
        return (CommsDynamicFeatureService) Preconditions.checkNotNull(applicationModule.providesCommsDynamicFeatureService(commsManager), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsDynamicFeatureService mo10268get() {
        return provideInstance(this.module, this.commsManagerProvider);
    }
}
