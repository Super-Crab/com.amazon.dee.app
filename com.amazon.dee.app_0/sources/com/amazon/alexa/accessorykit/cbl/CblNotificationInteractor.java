package com.amazon.alexa.accessorykit.cbl;
/* loaded from: classes6.dex */
public interface CblNotificationInteractor {
    boolean areNotificationsEnabled();

    void cancel(int i);

    void show(int i, CblNotification cblNotification);
}
