package com.amazon.photos.discovery.internal.dagger.module;

import androidx.work.WorkManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class DiscoveryModule_ProvideWorkManagerFactory implements Factory<WorkManager> {
    private final DiscoveryModule module;

    public DiscoveryModule_ProvideWorkManagerFactory(DiscoveryModule discoveryModule) {
        this.module = discoveryModule;
    }

    public static DiscoveryModule_ProvideWorkManagerFactory create(DiscoveryModule discoveryModule) {
        return new DiscoveryModule_ProvideWorkManagerFactory(discoveryModule);
    }

    public static WorkManager provideWorkManager(DiscoveryModule discoveryModule) {
        return (WorkManager) Preconditions.checkNotNull(discoveryModule.provideWorkManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WorkManager mo10268get() {
        return provideWorkManager(this.module);
    }
}
