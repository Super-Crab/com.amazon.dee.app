package com.amazon.alexa.devices.notifier;
/* loaded from: classes6.dex */
public class AlexaNotificationBuilder<N> {
    private final N mNotification;
    private boolean mSticky = false;
    private INotifierCallback mCallback = null;

    public AlexaNotificationBuilder(N n) {
        this.mNotification = n;
    }

    public AlexaNotification build() {
        return new AlexaNotification(this.mNotification, this.mSticky, this.mCallback);
    }

    public AlexaNotificationBuilder setCallback(INotifierCallback iNotifierCallback) {
        this.mCallback = iNotifierCallback;
        return this;
    }

    public AlexaNotificationBuilder setSticky(boolean z) {
        this.mSticky = z;
        return this;
    }
}
