package com.amazon.alexa.redesign.dependency;

import android.content.Context;
import com.amazon.alexa.redesign.DismissedCardDataStore;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class RepositoryModule_ProvideDismissedCardDataStoreFactory implements Factory<DismissedCardDataStore> {
    private final Provider<Context> contextProvider;
    private final RepositoryModule module;

    public RepositoryModule_ProvideDismissedCardDataStoreFactory(RepositoryModule repositoryModule, Provider<Context> provider) {
        this.module = repositoryModule;
        this.contextProvider = provider;
    }

    public static RepositoryModule_ProvideDismissedCardDataStoreFactory create(RepositoryModule repositoryModule, Provider<Context> provider) {
        return new RepositoryModule_ProvideDismissedCardDataStoreFactory(repositoryModule, provider);
    }

    public static DismissedCardDataStore provideInstance(RepositoryModule repositoryModule, Provider<Context> provider) {
        return proxyProvideDismissedCardDataStore(repositoryModule, provider.mo10268get());
    }

    public static DismissedCardDataStore proxyProvideDismissedCardDataStore(RepositoryModule repositoryModule, Context context) {
        return (DismissedCardDataStore) Preconditions.checkNotNull(repositoryModule.provideDismissedCardDataStore(context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DismissedCardDataStore mo10268get() {
        return provideInstance(this.module, this.contextProvider);
    }
}
