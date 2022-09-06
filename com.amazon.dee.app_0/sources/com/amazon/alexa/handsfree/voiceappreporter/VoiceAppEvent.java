package com.amazon.alexa.handsfree.voiceappreporter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes8.dex */
public class VoiceAppEvent {
    private final String mEventType;
    private final Throwable mThrowable;
    private final long mTimestamp;

    public VoiceAppEvent(@NonNull String str, @NonNull long j, Throwable th) {
        this.mEventType = str;
        this.mTimestamp = j;
        this.mThrowable = th;
    }

    @NonNull
    public String getEventType() {
        return this.mEventType;
    }

    @Nullable
    public Throwable getThrowable() {
        return this.mThrowable;
    }

    @NonNull
    public long getTimestamp() {
        return this.mTimestamp;
    }
}
