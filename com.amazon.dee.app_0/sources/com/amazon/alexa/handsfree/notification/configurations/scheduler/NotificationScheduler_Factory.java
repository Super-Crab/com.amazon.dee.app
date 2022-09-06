package com.amazon.alexa.handsfree.notification.configurations.scheduler;

import android.content.Context;
import com.amazon.alexa.handsfree.notification.NotificationType;
import com.amazon.alexa.handsfree.notification.configurations.NotificationQuotaManager;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class NotificationScheduler_Factory implements Factory<NotificationScheduler> {
    private final Provider<Context> contextProvider;
    private final Provider<NotificationQuotaManager> notificationQuotaManagerProvider;
    private final Provider<NotificationTimeResolver> notificationTimeResolverProvider;
    private final Provider<NotificationType> notificationTypeProvider;

    public NotificationScheduler_Factory(Provider<NotificationType> provider, Provider<Context> provider2, Provider<NotificationQuotaManager> provider3, Provider<NotificationTimeResolver> provider4) {
        this.notificationTypeProvider = provider;
        this.contextProvider = provider2;
        this.notificationQuotaManagerProvider = provider3;
        this.notificationTimeResolverProvider = provider4;
    }

    public static NotificationScheduler_Factory create(Provider<NotificationType> provider, Provider<Context> provider2, Provider<NotificationQuotaManager> provider3, Provider<NotificationTimeResolver> provider4) {
        return new NotificationScheduler_Factory(provider, provider2, provider3, provider4);
    }

    public static NotificationScheduler newNotificationScheduler(NotificationType notificationType, Context context, NotificationQuotaManager notificationQuotaManager, Object obj) {
        return new NotificationScheduler(notificationType, context, notificationQuotaManager, (NotificationTimeResolver) obj);
    }

    public static NotificationScheduler provideInstance(Provider<NotificationType> provider, Provider<Context> provider2, Provider<NotificationQuotaManager> provider3, Provider<NotificationTimeResolver> provider4) {
        return new NotificationScheduler(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NotificationScheduler mo10268get() {
        return provideInstance(this.notificationTypeProvider, this.contextProvider, this.notificationQuotaManagerProvider, this.notificationTimeResolverProvider);
    }
}
