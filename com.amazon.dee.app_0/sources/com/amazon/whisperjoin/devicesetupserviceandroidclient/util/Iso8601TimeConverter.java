package com.amazon.whisperjoin.devicesetupserviceandroidclient.util;

import org.threeten.bp.Duration;
/* loaded from: classes13.dex */
public class Iso8601TimeConverter {
    public static long convertDurationToMs(String str) {
        if (str == null) {
            return 0L;
        }
        return Duration.parse(str).toMillis();
    }
}
