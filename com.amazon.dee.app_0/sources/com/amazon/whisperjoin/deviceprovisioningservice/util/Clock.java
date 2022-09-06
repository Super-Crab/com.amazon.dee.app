package com.amazon.whisperjoin.deviceprovisioningservice.util;

import android.os.SystemClock;
import com.amazon.dee.sdk.iotsoftap.Constants;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
/* loaded from: classes13.dex */
public interface Clock {

    /* loaded from: classes13.dex */
    public static class SystemClockImpl implements Clock {
        @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.Clock
        public long currentThreadTimeMillis() {
            return SystemClock.currentThreadTimeMillis();
        }

        @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.Clock
        public long elapsedRealtime() {
            return SystemClock.elapsedRealtime();
        }

        @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.Clock
        public long epochTimeMillis() {
            return System.currentTimeMillis();
        }

        @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.Clock
        public String getCurrentUtcTime() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone(Constants.UTC));
            return simpleDateFormat.format(new Date(epochTimeMillis()));
        }

        @Override // com.amazon.whisperjoin.deviceprovisioningservice.util.Clock
        public long uptimeMillis() {
            return SystemClock.uptimeMillis();
        }
    }

    long currentThreadTimeMillis();

    long elapsedRealtime();

    long epochTimeMillis();

    String getCurrentUtcTime();

    long uptimeMillis();
}
