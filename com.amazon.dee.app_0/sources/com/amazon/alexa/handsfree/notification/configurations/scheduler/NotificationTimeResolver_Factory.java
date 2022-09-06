package com.amazon.alexa.handsfree.notification.configurations.scheduler;

import android.content.Context;
import com.amazon.alexa.handsfree.notification.NotificationOccurrenceCounter;
import com.amazon.alexa.handsfree.notification.configurations.NotificationQuotaManager;
import com.amazon.alexa.handsfree.notification.configurations.scheduler.NotificationTimeResolver;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class NotificationTimeResolver_Factory implements Factory<NotificationTimeResolver> {
    private final Provider<NotificationQuotaManager.QuotaConstraintsHandler> configIntervalHandlerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<NotificationOccurrenceCounter> notificationOccurrenceCounterProvider;
    private final Provider<NotificationTimeResolver.ScheduleTransformation> scheduleTransformationProvider;

    public NotificationTimeResolver_Factory(Provider<Context> provider, Provider<NotificationQuotaManager.QuotaConstraintsHandler> provider2, Provider<NotificationOccurrenceCounter> provider3, Provider<NotificationTimeResolver.ScheduleTransformation> provider4) {
        this.contextProvider = provider;
        this.configIntervalHandlerProvider = provider2;
        this.notificationOccurrenceCounterProvider = provider3;
        this.scheduleTransformationProvider = provider4;
    }

    public static NotificationTimeResolver_Factory create(Provider<Context> provider, Provider<NotificationQuotaManager.QuotaConstraintsHandler> provider2, Provider<NotificationOccurrenceCounter> provider3, Provider<NotificationTimeResolver.ScheduleTransformation> provider4) {
        return new NotificationTimeResolver_Factory(provider, provider2, provider3, provider4);
    }

    public static NotificationTimeResolver newNotificationTimeResolver(Context context, NotificationQuotaManager.QuotaConstraintsHandler quotaConstraintsHandler, NotificationOccurrenceCounter notificationOccurrenceCounter, Object obj) {
        return new NotificationTimeResolver(context, quotaConstraintsHandler, notificationOccurrenceCounter, (NotificationTimeResolver.ScheduleTransformation) obj);
    }

    public static NotificationTimeResolver provideInstance(Provider<Context> provider, Provider<NotificationQuotaManager.QuotaConstraintsHandler> provider2, Provider<NotificationOccurrenceCounter> provider3, Provider<NotificationTimeResolver.ScheduleTransformation> provider4) {
        return new NotificationTimeResolver(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NotificationTimeResolver mo10268get() {
        return provideInstance(this.contextProvider, this.configIntervalHandlerProvider, this.notificationOccurrenceCounterProvider, this.scheduleTransformationProvider);
    }
}
