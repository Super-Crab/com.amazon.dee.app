package com.amazon.alexa.drive.dependency;

import com.amazon.alexa.eventbus.api.EventBus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes7.dex */
public final class RepositoryModule_ProvideEventBusFactory implements Factory<EventBus> {
    private final RepositoryModule module;

    public RepositoryModule_ProvideEventBusFactory(RepositoryModule repositoryModule) {
        this.module = repositoryModule;
    }

    public static RepositoryModule_ProvideEventBusFactory create(RepositoryModule repositoryModule) {
        return new RepositoryModule_ProvideEventBusFactory(repositoryModule);
    }

    public static EventBus provideInstance(RepositoryModule repositoryModule) {
        return proxyProvideEventBus(repositoryModule);
    }

    public static EventBus proxyProvideEventBus(RepositoryModule repositoryModule) {
        return (EventBus) Preconditions.checkNotNull(repositoryModule.provideEventBus(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventBus mo10268get() {
        return provideInstance(this.module);
    }
}
