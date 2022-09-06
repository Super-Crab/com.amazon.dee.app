package com.amazon.alexa.redesign.dependency;

import android.content.Context;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.redesign.actions.ActionFactory;
import com.amazon.alexa.redesign.cache.HomeCacheService;
import com.amazon.alexa.redesign.client.HomeFeedServiceClient;
import com.amazon.alexa.redesign.repository.HomeCardsRepository;
import com.amazon.alexa.redesign.repository.IsAppOnlyRepository;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class RepositoryModule_ProvideHomeCardsRepositoryFactory implements Factory<HomeCardsRepository> {
    private final Provider<ActionFactory> actionFactoryProvider;
    private final Provider<Context> contextProvider;
    private final Provider<HomeCacheService> homeCacheServiceProvider;
    private final Provider<HomeFeedServiceClient> homeFeedServiceClientProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<IsAppOnlyRepository> isAppOnlyRepositoryProvider;
    private final Provider<Mobilytics> mobilyticsProvider;
    private final RepositoryModule module;

    public RepositoryModule_ProvideHomeCardsRepositoryFactory(RepositoryModule repositoryModule, Provider<HomeFeedServiceClient> provider, Provider<HomeCacheService> provider2, Provider<Mobilytics> provider3, Provider<ActionFactory> provider4, Provider<Context> provider5, Provider<IdentityService> provider6, Provider<IsAppOnlyRepository> provider7) {
        this.module = repositoryModule;
        this.homeFeedServiceClientProvider = provider;
        this.homeCacheServiceProvider = provider2;
        this.mobilyticsProvider = provider3;
        this.actionFactoryProvider = provider4;
        this.contextProvider = provider5;
        this.identityServiceProvider = provider6;
        this.isAppOnlyRepositoryProvider = provider7;
    }

    public static RepositoryModule_ProvideHomeCardsRepositoryFactory create(RepositoryModule repositoryModule, Provider<HomeFeedServiceClient> provider, Provider<HomeCacheService> provider2, Provider<Mobilytics> provider3, Provider<ActionFactory> provider4, Provider<Context> provider5, Provider<IdentityService> provider6, Provider<IsAppOnlyRepository> provider7) {
        return new RepositoryModule_ProvideHomeCardsRepositoryFactory(repositoryModule, provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static HomeCardsRepository provideInstance(RepositoryModule repositoryModule, Provider<HomeFeedServiceClient> provider, Provider<HomeCacheService> provider2, Provider<Mobilytics> provider3, Provider<ActionFactory> provider4, Provider<Context> provider5, Provider<IdentityService> provider6, Provider<IsAppOnlyRepository> provider7) {
        return proxyProvideHomeCardsRepository(repositoryModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get());
    }

    public static HomeCardsRepository proxyProvideHomeCardsRepository(RepositoryModule repositoryModule, HomeFeedServiceClient homeFeedServiceClient, HomeCacheService homeCacheService, Mobilytics mobilytics, ActionFactory actionFactory, Context context, IdentityService identityService, IsAppOnlyRepository isAppOnlyRepository) {
        return (HomeCardsRepository) Preconditions.checkNotNull(repositoryModule.provideHomeCardsRepository(homeFeedServiceClient, homeCacheService, mobilytics, actionFactory, context, identityService, isAppOnlyRepository), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public HomeCardsRepository mo10268get() {
        return provideInstance(this.module, this.homeFeedServiceClientProvider, this.homeCacheServiceProvider, this.mobilyticsProvider, this.actionFactoryProvider, this.contextProvider, this.identityServiceProvider, this.isAppOnlyRepositoryProvider);
    }
}
