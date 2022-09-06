package com.amazon.alexa.devices.notifier;
/* loaded from: classes6.dex */
public class AlexaNotification<N> {
    private static final int VERSION = 0;
    private final transient INotifierCallback mCallback;
    private final String mClassName;
    private final N mNotification;
    private final boolean mSticky;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaNotification(N n, boolean z, INotifierCallback iNotifierCallback) {
        if (n != null) {
            if (iNotifierCallback != null) {
                this.mNotification = n;
                this.mSticky = z;
                this.mCallback = iNotifierCallback;
                this.mClassName = getNotification().getClass().getName();
                return;
            }
            throw new IllegalArgumentException("Callback cannot be null");
        }
        throw new IllegalArgumentException("Notification cannot be null");
    }

    public INotifierCallback getCallback() {
        return this.mCallback;
    }

    public String getClassName() {
        return this.mClassName;
    }

    public N getNotification() {
        return this.mNotification;
    }

    public boolean isSticky() {
        return this.mSticky;
    }
}
