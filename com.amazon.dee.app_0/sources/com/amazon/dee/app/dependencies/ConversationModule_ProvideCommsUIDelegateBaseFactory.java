package com.amazon.dee.app.dependencies;

import android.app.Activity;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.routing.api.RoutingRegistryAdapter;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.deecomms.api.CommsUIDelegateBase;
import com.amazon.deecomms.conversation.ConversationRouting;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ConversationModule_ProvideCommsUIDelegateBaseFactory implements Factory<CommsUIDelegateBase> {
    private final Provider<AccountService> accountServiceProvider;
    private final Provider<Activity> activityProvider;
    private final Provider<ConversationRouting> conversationRoutingProvider;
    private final ConversationModule module;
    private final Provider<RoutingRegistryAdapter> routingRegistryAdapterProvider;
    private final Provider<RoutingService> routingServiceProvider;

    public ConversationModule_ProvideCommsUIDelegateBaseFactory(ConversationModule conversationModule, Provider<Activity> provider, Provider<RoutingService> provider2, Provider<ConversationRouting> provider3, Provider<AccountService> provider4, Provider<RoutingRegistryAdapter> provider5) {
        this.module = conversationModule;
        this.activityProvider = provider;
        this.routingServiceProvider = provider2;
        this.conversationRoutingProvider = provider3;
        this.accountServiceProvider = provider4;
        this.routingRegistryAdapterProvider = provider5;
    }

    public static ConversationModule_ProvideCommsUIDelegateBaseFactory create(ConversationModule conversationModule, Provider<Activity> provider, Provider<RoutingService> provider2, Provider<ConversationRouting> provider3, Provider<AccountService> provider4, Provider<RoutingRegistryAdapter> provider5) {
        return new ConversationModule_ProvideCommsUIDelegateBaseFactory(conversationModule, provider, provider2, provider3, provider4, provider5);
    }

    public static CommsUIDelegateBase provideInstance(ConversationModule conversationModule, Provider<Activity> provider, Provider<RoutingService> provider2, Provider<ConversationRouting> provider3, Provider<AccountService> provider4, Provider<RoutingRegistryAdapter> provider5) {
        return proxyProvideCommsUIDelegateBase(conversationModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    public static CommsUIDelegateBase proxyProvideCommsUIDelegateBase(ConversationModule conversationModule, Activity activity, RoutingService routingService, ConversationRouting conversationRouting, AccountService accountService, RoutingRegistryAdapter routingRegistryAdapter) {
        return (CommsUIDelegateBase) Preconditions.checkNotNull(conversationModule.provideCommsUIDelegateBase(activity, routingService, conversationRouting, accountService, routingRegistryAdapter), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsUIDelegateBase mo10268get() {
        return provideInstance(this.module, this.activityProvider, this.routingServiceProvider, this.conversationRoutingProvider, this.accountServiceProvider, this.routingRegistryAdapterProvider);
    }
}
