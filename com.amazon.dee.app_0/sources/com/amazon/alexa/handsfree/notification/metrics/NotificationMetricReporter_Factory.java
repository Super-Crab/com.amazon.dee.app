package com.amazon.alexa.handsfree.notification.metrics;

import android.content.Context;
import com.amazon.alexa.handsfree.notification.api.NotificationContract;
import com.amazon.alexa.handsfree.protocols.metrics.builders.MetricsBuilderProvider;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class NotificationMetricReporter_Factory implements Factory<NotificationMetricReporter> {
    private final Provider<Context> contextProvider;
    private final Provider<MetricsBuilderProvider> metricsBuilderProvider;
    private final Provider<NotificationContract> notificationContractProvider;

    public NotificationMetricReporter_Factory(Provider<Context> provider, Provider<MetricsBuilderProvider> provider2, Provider<NotificationContract> provider3) {
        this.contextProvider = provider;
        this.metricsBuilderProvider = provider2;
        this.notificationContractProvider = provider3;
    }

    public static NotificationMetricReporter_Factory create(Provider<Context> provider, Provider<MetricsBuilderProvider> provider2, Provider<NotificationContract> provider3) {
        return new NotificationMetricReporter_Factory(provider, provider2, provider3);
    }

    public static NotificationMetricReporter newNotificationMetricReporter(Context context, MetricsBuilderProvider metricsBuilderProvider, NotificationContract notificationContract) {
        return new NotificationMetricReporter(context, metricsBuilderProvider, notificationContract);
    }

    public static NotificationMetricReporter provideInstance(Provider<Context> provider, Provider<MetricsBuilderProvider> provider2, Provider<NotificationContract> provider3) {
        return new NotificationMetricReporter(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NotificationMetricReporter mo10268get() {
        return provideInstance(this.contextProvider, this.metricsBuilderProvider, this.notificationContractProvider);
    }
}
