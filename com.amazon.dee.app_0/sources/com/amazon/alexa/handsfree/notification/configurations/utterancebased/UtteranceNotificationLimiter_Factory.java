package com.amazon.alexa.handsfree.notification.configurations.utterancebased;

import android.content.Context;
import com.amazon.alexa.handsfree.notification.NotificationOccurrenceCounter;
import com.amazon.alexa.handsfree.notification.NotificationType;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class UtteranceNotificationLimiter_Factory implements Factory<UtteranceNotificationLimiter> {
    private final Provider<Context> contextProvider;
    private final Provider<NotificationOccurrenceCounter> notificationOccurrenceCounterProvider;
    private final Provider<NotificationType> notificationTypeProvider;
    private final Provider<UtteranceConfigHandler> utteranceConfigHandlerProvider;

    public UtteranceNotificationLimiter_Factory(Provider<Context> provider, Provider<NotificationType> provider2, Provider<NotificationOccurrenceCounter> provider3, Provider<UtteranceConfigHandler> provider4) {
        this.contextProvider = provider;
        this.notificationTypeProvider = provider2;
        this.notificationOccurrenceCounterProvider = provider3;
        this.utteranceConfigHandlerProvider = provider4;
    }

    public static UtteranceNotificationLimiter_Factory create(Provider<Context> provider, Provider<NotificationType> provider2, Provider<NotificationOccurrenceCounter> provider3, Provider<UtteranceConfigHandler> provider4) {
        return new UtteranceNotificationLimiter_Factory(provider, provider2, provider3, provider4);
    }

    public static UtteranceNotificationLimiter newUtteranceNotificationLimiter(Context context, NotificationType notificationType, NotificationOccurrenceCounter notificationOccurrenceCounter, UtteranceConfigHandler utteranceConfigHandler) {
        return new UtteranceNotificationLimiter(context, notificationType, notificationOccurrenceCounter, utteranceConfigHandler);
    }

    public static UtteranceNotificationLimiter provideInstance(Provider<Context> provider, Provider<NotificationType> provider2, Provider<NotificationOccurrenceCounter> provider3, Provider<UtteranceConfigHandler> provider4) {
        return new UtteranceNotificationLimiter(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public UtteranceNotificationLimiter mo10268get() {
        return provideInstance(this.contextProvider, this.notificationTypeProvider, this.notificationOccurrenceCounterProvider, this.utteranceConfigHandlerProvider);
    }
}
