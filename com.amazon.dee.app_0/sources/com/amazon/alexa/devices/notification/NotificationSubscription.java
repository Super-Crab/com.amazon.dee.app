package com.amazon.alexa.devices.notification;
/* loaded from: classes6.dex */
public class NotificationSubscription<N> {
    private static final int VERSION = 0;
    private final transient INotificationCallback<N> mCallback;
    private final String mClassName;
    private final transient Class<N> mNotificationClass;
    private final boolean mSticky;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NotificationSubscription(Class<N> cls, boolean z, INotificationCallback<N> iNotificationCallback) {
        if (cls != null) {
            if (iNotificationCallback != null) {
                this.mNotificationClass = cls;
                this.mSticky = z;
                this.mCallback = iNotificationCallback;
                this.mClassName = getNotificationClass().getName();
                return;
            }
            throw new IllegalArgumentException("Callback cannot be null");
        }
        throw new IllegalArgumentException("Class cannot be null");
    }

    public INotificationCallback<N> getCallback() {
        return this.mCallback;
    }

    public String getClassName() {
        return this.mClassName;
    }

    public Class<N> getNotificationClass() {
        return this.mNotificationClass;
    }

    public boolean isSticky() {
        return this.mSticky;
    }
}
