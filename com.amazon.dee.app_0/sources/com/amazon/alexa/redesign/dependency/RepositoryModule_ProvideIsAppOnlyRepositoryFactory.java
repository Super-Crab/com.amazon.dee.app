package com.amazon.alexa.redesign.dependency;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.redesign.repository.IsAppOnlyRepository;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class RepositoryModule_ProvideIsAppOnlyRepositoryFactory implements Factory<IsAppOnlyRepository> {
    private final Provider<Context> contextProvider;
    private final Provider<EventBus> eventBusProvider;
    private final RepositoryModule module;

    public RepositoryModule_ProvideIsAppOnlyRepositoryFactory(RepositoryModule repositoryModule, Provider<EventBus> provider, Provider<Context> provider2) {
        this.module = repositoryModule;
        this.eventBusProvider = provider;
        this.contextProvider = provider2;
    }

    public static RepositoryModule_ProvideIsAppOnlyRepositoryFactory create(RepositoryModule repositoryModule, Provider<EventBus> provider, Provider<Context> provider2) {
        return new RepositoryModule_ProvideIsAppOnlyRepositoryFactory(repositoryModule, provider, provider2);
    }

    public static IsAppOnlyRepository provideInstance(RepositoryModule repositoryModule, Provider<EventBus> provider, Provider<Context> provider2) {
        return proxyProvideIsAppOnlyRepository(repositoryModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static IsAppOnlyRepository proxyProvideIsAppOnlyRepository(RepositoryModule repositoryModule, EventBus eventBus, Context context) {
        return (IsAppOnlyRepository) Preconditions.checkNotNull(repositoryModule.provideIsAppOnlyRepository(eventBus, context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public IsAppOnlyRepository mo10268get() {
        return provideInstance(this.module, this.eventBusProvider, this.contextProvider);
    }
}
