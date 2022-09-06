package com.amazon.alexa.handsfree.notification;

import com.amazon.alexa.handsfree.protocols.Initializer;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class NotificationDismissReceiver_Factory implements Factory<NotificationDismissReceiver> {
    private final Provider<Initializer> initializerProvider;

    public NotificationDismissReceiver_Factory(Provider<Initializer> provider) {
        this.initializerProvider = provider;
    }

    public static NotificationDismissReceiver_Factory create(Provider<Initializer> provider) {
        return new NotificationDismissReceiver_Factory(provider);
    }

    public static NotificationDismissReceiver newNotificationDismissReceiver(Initializer initializer) {
        return new NotificationDismissReceiver(initializer);
    }

    public static NotificationDismissReceiver provideInstance(Provider<Initializer> provider) {
        return new NotificationDismissReceiver(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public NotificationDismissReceiver mo10268get() {
        return provideInstance(this.initializerProvider);
    }
}
