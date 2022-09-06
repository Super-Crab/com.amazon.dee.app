package com.amazon.crypto.utils;
/* loaded from: classes12.dex */
public final class Time {
    private Time() {
        UtilityInstanceAttempt.in(this);
    }

    public static long afterNow(long j) {
        return now() + j;
    }

    public static long beforeNow(long j) {
        return now() - j;
    }

    public static long now() {
        return System.currentTimeMillis();
    }
}
