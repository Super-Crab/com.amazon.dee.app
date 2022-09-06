package com.amazon.deecomms.notifications;

import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class NotificationActivatedReceiver_MembersInjector implements MembersInjector<NotificationActivatedReceiver> {
    private final Provider<NotificationLatencyMetricHelper> mNotificationLatencyMetricHelperProvider;

    public NotificationActivatedReceiver_MembersInjector(Provider<NotificationLatencyMetricHelper> provider) {
        this.mNotificationLatencyMetricHelperProvider = provider;
    }

    public static MembersInjector<NotificationActivatedReceiver> create(Provider<NotificationLatencyMetricHelper> provider) {
        return new NotificationActivatedReceiver_MembersInjector(provider);
    }

    public static void injectMNotificationLatencyMetricHelper(NotificationActivatedReceiver notificationActivatedReceiver, NotificationLatencyMetricHelper notificationLatencyMetricHelper) {
        notificationActivatedReceiver.mNotificationLatencyMetricHelper = notificationLatencyMetricHelper;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(NotificationActivatedReceiver notificationActivatedReceiver) {
        notificationActivatedReceiver.mNotificationLatencyMetricHelper = this.mNotificationLatencyMetricHelperProvider.mo10268get();
    }
}
