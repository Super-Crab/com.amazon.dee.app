package com.amazon.dee.app.dependencies;

import android.app.Activity;
import com.amazon.deecomms.api.CommsManager;
import com.amazon.deecomms.api.CommsServiceV2;
import com.amazon.deecomms.common.ui.main.ConversationRoutingAdapter;
import com.amazon.deecomms.conversation.ConversationRouting;
import com.dee.app.metrics.MetricsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ConversationModule_ProvideConversationRoutingAdapterFactory implements Factory<ConversationRoutingAdapter> {
    private final Provider<Activity> activityProvider;
    private final Provider<CommsManager> commsManagerProvider;
    private final Provider<CommsServiceV2> commsServiceV2Provider;
    private final Provider<ConversationRouting> conversationRoutingProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final ConversationModule module;

    public ConversationModule_ProvideConversationRoutingAdapterFactory(ConversationModule conversationModule, Provider<Activity> provider, Provider<CommsManager> provider2, Provider<ConversationRouting> provider3, Provider<MetricsService> provider4, Provider<CommsServiceV2> provider5) {
        this.module = conversationModule;
        this.activityProvider = provider;
        this.commsManagerProvider = provider2;
        this.conversationRoutingProvider = provider3;
        this.metricsServiceProvider = provider4;
        this.commsServiceV2Provider = provider5;
    }

    public static ConversationModule_ProvideConversationRoutingAdapterFactory create(ConversationModule conversationModule, Provider<Activity> provider, Provider<CommsManager> provider2, Provider<ConversationRouting> provider3, Provider<MetricsService> provider4, Provider<CommsServiceV2> provider5) {
        return new ConversationModule_ProvideConversationRoutingAdapterFactory(conversationModule, provider, provider2, provider3, provider4, provider5);
    }

    public static ConversationRoutingAdapter provideInstance(ConversationModule conversationModule, Provider<Activity> provider, Provider<CommsManager> provider2, Provider<ConversationRouting> provider3, Provider<MetricsService> provider4, Provider<CommsServiceV2> provider5) {
        return proxyProvideConversationRoutingAdapter(conversationModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), DoubleCheck.lazy(provider5));
    }

    public static ConversationRoutingAdapter proxyProvideConversationRoutingAdapter(ConversationModule conversationModule, Activity activity, CommsManager commsManager, ConversationRouting conversationRouting, MetricsService metricsService, Lazy<CommsServiceV2> lazy) {
        return (ConversationRoutingAdapter) Preconditions.checkNotNull(conversationModule.provideConversationRoutingAdapter(activity, commsManager, conversationRouting, metricsService, lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public ConversationRoutingAdapter mo10268get() {
        return provideInstance(this.module, this.activityProvider, this.commsManagerProvider, this.conversationRoutingProvider, this.metricsServiceProvider, this.commsServiceV2Provider);
    }
}
