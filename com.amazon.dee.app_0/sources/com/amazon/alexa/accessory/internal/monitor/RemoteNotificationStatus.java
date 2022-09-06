package com.amazon.alexa.accessory.internal.monitor;
/* loaded from: classes.dex */
public enum RemoteNotificationStatus {
    NO_ACTIVITY(1),
    REMINDER(2),
    TIMER(4),
    ALARM(8),
    ANNOUNCEMENT(16);
    
    public final int value;

    RemoteNotificationStatus(int i) {
        this.value = i;
    }
}
