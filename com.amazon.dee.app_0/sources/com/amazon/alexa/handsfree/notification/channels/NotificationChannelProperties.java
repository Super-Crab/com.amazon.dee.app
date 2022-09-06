package com.amazon.alexa.handsfree.notification.channels;

import androidx.annotation.NonNull;
/* loaded from: classes8.dex */
public enum NotificationChannelProperties {
    HANDS_FREE_SETUP("com.amazon.alexa.handsfree.notification.CHANNEL_ID", "Alexa Hands-Free Setup", 3);
    
    private final String mChannelId;
    private final String mChannelName;
    private final int mImportance;

    NotificationChannelProperties(@NonNull String str, @NonNull String str2, int i) {
        this.mChannelId = str;
        this.mChannelName = str2;
        this.mImportance = i;
    }

    public String getChannelId() {
        return this.mChannelId;
    }

    public String getChannelName() {
        return this.mChannelName;
    }

    public int getImportance() {
        return this.mImportance;
    }
}
