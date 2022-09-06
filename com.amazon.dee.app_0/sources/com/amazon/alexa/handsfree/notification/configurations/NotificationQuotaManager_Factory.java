package com.amazon.alexa.handsfree.notification.configurations;

import android.content.Context;
import com.amazon.alexa.handsfree.notification.NotificationOccurrenceCounter;
import com.amazon.alexa.handsfree.notification.configurations.NotificationQuotaManager;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class NotificationQuotaManager_Factory implements Factory<NotificationQuotaManager> {
    private final Provider<Context> contextProvider;
    private final Provider<NotificationOccurrenceCounter> notificationOccurrenceCounterProvider;
    private final Provider<NotificationQuotaManager.QuotaConstraintsHandler> quotaConstraintsHandlerProvider;

    public NotificationQuotaManager_Factory(Provider<Context> provider, Provider<NotificationOccurrenceCounter> provider2, Provider<NotificationQuotaManager.QuotaConstraintsHandler> provider3) {
        this.contextProvider = provider;
        this.notificationOccurrenceCounterProvider = provider2;
        this.quotaConstraintsHandlerProvider = provider3;
    }

    public static NotificationQuotaManager_Factory create(Provider<Context> provider, Provider<NotificationOccurrenceCounter> provider2, Provider<NotificationQuotaManager.QuotaConstraintsHandler> provider3) {
        return new NotificationQuotaManager_Factory(provider, provider2, provider3);
    }

    public static NotificationQuotaManager newNotificationQuotaManager(Context context, NotificationOccurrenceCounter notificationOccurrenceCounter, NotificationQuotaManager.QuotaConstraintsHandler quotaConstraintsHandler) {
        return new NotificationQuotaManager(context, notificationOccurrenceCounter, quotaConstraintsHandler);
    }

    public static NotificationQuotaManager provideInstance(Provider<Context> provider, Provider<NotificationOccurrenceCounter> provider2, Provider<NotificationQuotaManager.QuotaConstraintsHandler> provider3) {
        return new NotificationQuotaManager(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NotificationQuotaManager mo10268get() {
        return provideInstance(this.contextProvider, this.notificationOccurrenceCounterProvider, this.quotaConstraintsHandlerProvider);
    }
}
