package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ApplicationModule_ProvideMainMessagingReceiverFactory implements Factory<MessagingReceiver> {
    private final Provider<Context> contextProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<FeatureServiceV2> featureServiceV2Provider;
    private final Provider<Mobilytics> mobilyticsProvider;
    private final ApplicationModule module;

    public ApplicationModule_ProvideMainMessagingReceiverFactory(ApplicationModule applicationModule, Provider<Context> provider, Provider<Mobilytics> provider2, Provider<EnvironmentService> provider3, Provider<FeatureServiceV2> provider4) {
        this.module = applicationModule;
        this.contextProvider = provider;
        this.mobilyticsProvider = provider2;
        this.environmentServiceProvider = provider3;
        this.featureServiceV2Provider = provider4;
    }

    public static ApplicationModule_ProvideMainMessagingReceiverFactory create(ApplicationModule applicationModule, Provider<Context> provider, Provider<Mobilytics> provider2, Provider<EnvironmentService> provider3, Provider<FeatureServiceV2> provider4) {
        return new ApplicationModule_ProvideMainMessagingReceiverFactory(applicationModule, provider, provider2, provider3, provider4);
    }

    public static MessagingReceiver provideInstance(ApplicationModule applicationModule, Provider<Context> provider, Provider<Mobilytics> provider2, Provider<EnvironmentService> provider3, Provider<FeatureServiceV2> provider4) {
        return proxyProvideMainMessagingReceiver(applicationModule, provider.mo10268get(), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4));
    }

    public static MessagingReceiver proxyProvideMainMessagingReceiver(ApplicationModule applicationModule, Context context, Lazy<Mobilytics> lazy, Lazy<EnvironmentService> lazy2, Lazy<FeatureServiceV2> lazy3) {
        return (MessagingReceiver) Preconditions.checkNotNull(applicationModule.provideMainMessagingReceiver(context, lazy, lazy2, lazy3), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MessagingReceiver mo10268get() {
        return provideInstance(this.module, this.contextProvider, this.mobilyticsProvider, this.environmentServiceProvider, this.featureServiceV2Provider);
    }
}
