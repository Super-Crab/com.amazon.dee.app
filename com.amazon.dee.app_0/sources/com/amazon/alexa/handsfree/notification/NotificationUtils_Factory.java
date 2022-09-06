package com.amazon.alexa.handsfree.notification;

import android.content.Context;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class NotificationUtils_Factory implements Factory<NotificationUtils> {
    private final Provider<Context> contextProvider;
    private final Provider<MetricsBuilderProvider> metricsBuilderProvider;
    private final Provider<NotificationOccurrenceCounter> notificationOccurrenceCounterProvider;
    private final Provider<NotificationType> notificationTypeProvider;

    public NotificationUtils_Factory(Provider<Context> provider, Provider<NotificationType> provider2, Provider<MetricsBuilderProvider> provider3, Provider<NotificationOccurrenceCounter> provider4) {
        this.contextProvider = provider;
        this.notificationTypeProvider = provider2;
        this.metricsBuilderProvider = provider3;
        this.notificationOccurrenceCounterProvider = provider4;
    }

    public static NotificationUtils_Factory create(Provider<Context> provider, Provider<NotificationType> provider2, Provider<MetricsBuilderProvider> provider3, Provider<NotificationOccurrenceCounter> provider4) {
        return new NotificationUtils_Factory(provider, provider2, provider3, provider4);
    }

    public static NotificationUtils newNotificationUtils(Context context, NotificationType notificationType, MetricsBuilderProvider metricsBuilderProvider, NotificationOccurrenceCounter notificationOccurrenceCounter) {
        return new NotificationUtils(context, notificationType, metricsBuilderProvider, notificationOccurrenceCounter);
    }

    public static NotificationUtils provideInstance(Provider<Context> provider, Provider<NotificationType> provider2, Provider<MetricsBuilderProvider> provider3, Provider<NotificationOccurrenceCounter> provider4) {
        return new NotificationUtils(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NotificationUtils mo10268get() {
        return provideInstance(this.contextProvider, this.notificationTypeProvider, this.metricsBuilderProvider, this.notificationOccurrenceCounterProvider);
    }
}
