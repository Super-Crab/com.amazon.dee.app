package com.amazon.alexa.handsfree.notification.notifiers;

import android.content.Context;
import android.content.Intent;
import com.amazon.alexa.handsfree.notification.NotificationModule;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class NotifierPriorityResolver_Factory implements Factory<NotifierPriorityResolver> {
    private final Provider<Context> contextProvider;
    private final Provider<Intent> intentProvider;
    private final Provider<MetricsBuilderProvider> metricsBuilderProvider;
    private final Provider<NotificationModule> notificationModuleProvider;
    private final Provider<NotifierFactory> notifierFactoryProvider;

    public NotifierPriorityResolver_Factory(Provider<Context> provider, Provider<Intent> provider2, Provider<NotifierFactory> provider3, Provider<NotificationModule> provider4, Provider<MetricsBuilderProvider> provider5) {
        this.contextProvider = provider;
        this.intentProvider = provider2;
        this.notifierFactoryProvider = provider3;
        this.notificationModuleProvider = provider4;
        this.metricsBuilderProvider = provider5;
    }

    public static NotifierPriorityResolver_Factory create(Provider<Context> provider, Provider<Intent> provider2, Provider<NotifierFactory> provider3, Provider<NotificationModule> provider4, Provider<MetricsBuilderProvider> provider5) {
        return new NotifierPriorityResolver_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static NotifierPriorityResolver newNotifierPriorityResolver(Context context, Intent intent, NotifierFactory notifierFactory, NotificationModule notificationModule, MetricsBuilderProvider metricsBuilderProvider) {
        return new NotifierPriorityResolver(context, intent, notifierFactory, notificationModule, metricsBuilderProvider);
    }

    public static NotifierPriorityResolver provideInstance(Provider<Context> provider, Provider<Intent> provider2, Provider<NotifierFactory> provider3, Provider<NotificationModule> provider4, Provider<MetricsBuilderProvider> provider5) {
        return new NotifierPriorityResolver(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NotifierPriorityResolver mo10268get() {
        return provideInstance(this.contextProvider, this.intentProvider, this.notifierFactoryProvider, this.notificationModuleProvider, this.metricsBuilderProvider);
    }
}
