package com.amazon.alexa.voice.events;

import android.os.SystemClock;
/* loaded from: classes11.dex */
public final class VoxUiEvent {
    public static final long UNDEFINED_TIMESTAMP = 0;
    private final String name;
    private final long timestamp;

    private VoxUiEvent(String str, long j) {
        this.name = str;
        this.timestamp = j;
    }

    public static VoxUiEvent create(String str) {
        return new VoxUiEvent(str, 0L);
    }

    public static VoxUiEvent occurNow(String str) {
        return new VoxUiEvent(str, SystemClock.elapsedRealtime());
    }

    public String getName() {
        return this.name;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public static VoxUiEvent create(String str, long j) {
        return new VoxUiEvent(str, j);
    }
}
