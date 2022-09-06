package com.amazon.alexa.redesign.dependency;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.usermessage.UserMessageService;
import com.amazon.alexa.redesign.actions.ActionFactory;
import com.amazon.alexa.redesign.client.HomeFeedServiceClient;
import com.amazon.alexa.redesign.utils.HomeOEInteractor;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class RepositoryModule_ProvideActionFactoryFactory implements Factory<ActionFactory> {
    private final Provider<Context> contextProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<HomeFeedServiceClient> homeFeedServiceClientProvider;
    private final Provider<HomeOEInteractor> homeOEInteractorProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final RepositoryModule module;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<UserMessageService> userMessageServiceProvider;

    public RepositoryModule_ProvideActionFactoryFactory(RepositoryModule repositoryModule, Provider<RoutingService> provider, Provider<EventBus> provider2, Provider<HomeOEInteractor> provider3, Provider<HomeFeedServiceClient> provider4, Provider<IdentityService> provider5, Provider<UserMessageService> provider6, Provider<Context> provider7) {
        this.module = repositoryModule;
        this.routingServiceProvider = provider;
        this.eventBusProvider = provider2;
        this.homeOEInteractorProvider = provider3;
        this.homeFeedServiceClientProvider = provider4;
        this.identityServiceProvider = provider5;
        this.userMessageServiceProvider = provider6;
        this.contextProvider = provider7;
    }

    public static RepositoryModule_ProvideActionFactoryFactory create(RepositoryModule repositoryModule, Provider<RoutingService> provider, Provider<EventBus> provider2, Provider<HomeOEInteractor> provider3, Provider<HomeFeedServiceClient> provider4, Provider<IdentityService> provider5, Provider<UserMessageService> provider6, Provider<Context> provider7) {
        return new RepositoryModule_ProvideActionFactoryFactory(repositoryModule, provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static ActionFactory provideInstance(RepositoryModule repositoryModule, Provider<RoutingService> provider, Provider<EventBus> provider2, Provider<HomeOEInteractor> provider3, Provider<HomeFeedServiceClient> provider4, Provider<IdentityService> provider5, Provider<UserMessageService> provider6, Provider<Context> provider7) {
        return proxyProvideActionFactory(repositoryModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get());
    }

    public static ActionFactory proxyProvideActionFactory(RepositoryModule repositoryModule, RoutingService routingService, EventBus eventBus, HomeOEInteractor homeOEInteractor, HomeFeedServiceClient homeFeedServiceClient, IdentityService identityService, UserMessageService userMessageService, Context context) {
        return (ActionFactory) Preconditions.checkNotNull(repositoryModule.provideActionFactory(routingService, eventBus, homeOEInteractor, homeFeedServiceClient, identityService, userMessageService, context), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ActionFactory mo10268get() {
        return provideInstance(this.module, this.routingServiceProvider, this.eventBusProvider, this.homeOEInteractorProvider, this.homeFeedServiceClientProvider, this.identityServiceProvider, this.userMessageServiceProvider, this.contextProvider);
    }
}
