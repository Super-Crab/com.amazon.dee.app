package com.amazon.alexa.voice.metrics;

import android.os.SystemClock;
/* loaded from: classes11.dex */
public final class VoxMetricEvent {
    private static final String EMPTY_STRING = "";
    public static final long UNDEFINED_TIMESTAMP = 0;
    private final String fromWhere;
    private final String locales;
    private final String name;
    private final long timestamp;

    private VoxMetricEvent(String str, long j) {
        this.name = str;
        this.timestamp = j;
        this.locales = "";
        this.fromWhere = "";
    }

    public static VoxMetricEvent create(String str) {
        return new VoxMetricEvent(str, 0L);
    }

    public static VoxMetricEvent occurNow(String str) {
        return new VoxMetricEvent(str, SystemClock.elapsedRealtime());
    }

    public String getFromWhere() {
        return this.fromWhere;
    }

    public String getLocales() {
        return this.locales;
    }

    public String getName() {
        return this.name;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public static VoxMetricEvent create(String str, long j) {
        return new VoxMetricEvent(str, j);
    }

    public static VoxMetricEvent occurNow(String str, String str2, String str3) {
        return new VoxMetricEvent(str, str2, str3, SystemClock.elapsedRealtime());
    }

    private VoxMetricEvent(String str, String str2, String str3, long j) {
        this.name = str;
        this.locales = str2;
        this.fromWhere = str3;
        this.timestamp = j;
    }
}
