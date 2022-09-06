package com.amazon.dee.app.dependencies;

import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.CommsServiceV2;
import com.amazon.deecomms.services.ConversationUIService;
import com.amazon.deecomms.ui.util.CommsRoutingHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ConversationModule_ProvideCommsRoutingHelperFactory implements Factory<CommsRoutingHelper> {
    private final Provider<CommsManager> commsManagerProvider;
    private final Provider<CommsServiceV2> commsServiceV2Provider;
    private final Provider<ConversationUIService> conversationUIServiceProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final ConversationModule module;
    private final Provider<RoutingService> routingServiceProvider;

    public ConversationModule_ProvideCommsRoutingHelperFactory(ConversationModule conversationModule, Provider<ConversationUIService> provider, Provider<RoutingService> provider2, Provider<CommsServiceV2> provider3, Provider<CommsManager> provider4, Provider<IdentityService> provider5) {
        this.module = conversationModule;
        this.conversationUIServiceProvider = provider;
        this.routingServiceProvider = provider2;
        this.commsServiceV2Provider = provider3;
        this.commsManagerProvider = provider4;
        this.identityServiceProvider = provider5;
    }

    public static ConversationModule_ProvideCommsRoutingHelperFactory create(ConversationModule conversationModule, Provider<ConversationUIService> provider, Provider<RoutingService> provider2, Provider<CommsServiceV2> provider3, Provider<CommsManager> provider4, Provider<IdentityService> provider5) {
        return new ConversationModule_ProvideCommsRoutingHelperFactory(conversationModule, provider, provider2, provider3, provider4, provider5);
    }

    public static CommsRoutingHelper provideInstance(ConversationModule conversationModule, Provider<ConversationUIService> provider, Provider<RoutingService> provider2, Provider<CommsServiceV2> provider3, Provider<CommsManager> provider4, Provider<IdentityService> provider5) {
        return proxyProvideCommsRoutingHelper(conversationModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    public static CommsRoutingHelper proxyProvideCommsRoutingHelper(ConversationModule conversationModule, ConversationUIService conversationUIService, RoutingService routingService, CommsServiceV2 commsServiceV2, CommsManager commsManager, IdentityService identityService) {
        return (CommsRoutingHelper) Preconditions.checkNotNull(conversationModule.provideCommsRoutingHelper(conversationUIService, routingService, commsServiceV2, commsManager, identityService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsRoutingHelper mo10268get() {
        return provideInstance(this.module, this.conversationUIServiceProvider, this.routingServiceProvider, this.commsServiceV2Provider, this.commsManagerProvider, this.identityServiceProvider);
    }
}
