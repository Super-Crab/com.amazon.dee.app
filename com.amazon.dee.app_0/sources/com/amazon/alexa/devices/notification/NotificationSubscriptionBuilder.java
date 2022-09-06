package com.amazon.alexa.devices.notification;
/* loaded from: classes6.dex */
public class NotificationSubscriptionBuilder<N> {
    private final Class<N> mNotificationClass;
    private boolean mSticky = false;
    private INotificationCallback<N> mCallback = null;

    public NotificationSubscriptionBuilder(Class<N> cls) {
        this.mNotificationClass = cls;
    }

    public NotificationSubscription build() {
        return new NotificationSubscription(this.mNotificationClass, this.mSticky, this.mCallback);
    }

    public NotificationSubscriptionBuilder setCallback(INotificationCallback<N> iNotificationCallback) {
        this.mCallback = iNotificationCallback;
        return this;
    }

    public NotificationSubscriptionBuilder setSticky(boolean z) {
        this.mSticky = z;
        return this;
    }
}
