package com.amazon.alexa.accessory.notificationpublisher.audiofocus;

import androidx.annotation.Nullable;
/* loaded from: classes.dex */
public final class ZionAudioFocusRequest {
    private int eventId;
    private int eventType;
    private String fileToPlay;
    private String uuid;

    public ZionAudioFocusRequest(int i, int i2, @Nullable String str, @Nullable String str2) {
        this.eventId = i;
        this.eventType = i2;
        this.uuid = str;
        this.fileToPlay = str2;
    }

    public int getEventId() {
        return this.eventId;
    }

    public int getEventType() {
        return this.eventType;
    }

    public String getFileToPlay() {
        return this.fileToPlay;
    }

    public String getUuid() {
        return this.uuid;
    }
}
