package com.amazon.identity.auth.device;

import java.util.Date;
import java.util.concurrent.TimeUnit;
/* compiled from: DCP */
/* loaded from: classes12.dex */
public final class jj {
    public static long b(long j, TimeUnit timeUnit) {
        return c(j * 24, timeUnit);
    }

    public static long c(long j, TimeUnit timeUnit) {
        return d(j * 60, timeUnit);
    }

    public static long d(long j, TimeUnit timeUnit) {
        return timeUnit.convert(j * 60, TimeUnit.SECONDS);
    }

    public static Date dH(String str) {
        Long dB = je.dB(str);
        if (dB == null) {
            return null;
        }
        return new Date(dB.longValue());
    }

    public static String h(Date date) {
        if (date == null) {
            return null;
        }
        return Long.toString(date.getTime());
    }
}
