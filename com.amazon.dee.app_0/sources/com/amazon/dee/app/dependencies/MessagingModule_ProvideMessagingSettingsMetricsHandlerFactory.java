package com.amazon.dee.app.dependencies;

import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.dee.app.services.messaging.MessagingSettingsMetricsHandler;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MessagingModule_ProvideMessagingSettingsMetricsHandlerFactory implements Factory<MessagingSettingsMetricsHandler> {
    private final Provider<Mobilytics> mobilyticsProvider;
    private final MessagingModule module;

    public MessagingModule_ProvideMessagingSettingsMetricsHandlerFactory(MessagingModule messagingModule, Provider<Mobilytics> provider) {
        this.module = messagingModule;
        this.mobilyticsProvider = provider;
    }

    public static MessagingModule_ProvideMessagingSettingsMetricsHandlerFactory create(MessagingModule messagingModule, Provider<Mobilytics> provider) {
        return new MessagingModule_ProvideMessagingSettingsMetricsHandlerFactory(messagingModule, provider);
    }

    public static MessagingSettingsMetricsHandler provideInstance(MessagingModule messagingModule, Provider<Mobilytics> provider) {
        return proxyProvideMessagingSettingsMetricsHandler(messagingModule, DoubleCheck.lazy(provider));
    }

    public static MessagingSettingsMetricsHandler proxyProvideMessagingSettingsMetricsHandler(MessagingModule messagingModule, Lazy<Mobilytics> lazy) {
        return (MessagingSettingsMetricsHandler) Preconditions.checkNotNull(messagingModule.provideMessagingSettingsMetricsHandler(lazy), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MessagingSettingsMetricsHandler mo10268get() {
        return provideInstance(this.module, this.mobilyticsProvider);
    }
}
