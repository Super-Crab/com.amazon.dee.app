package com.amazon.whisperjoin.provisioning.utils;
/* loaded from: classes13.dex */
public class SystemTime {
    public static final TimeSource REAL_TIME = new TimeSource() { // from class: com.amazon.whisperjoin.provisioning.utils.SystemTime.1
        @Override // com.amazon.whisperjoin.provisioning.utils.SystemTime.TimeSource
        public long now() {
            return System.currentTimeMillis();
        }
    };
    private static TimeSource sTimeSource = REAL_TIME;

    /* loaded from: classes13.dex */
    public interface TimeSource {
        long now();
    }

    public static long now() {
        return sTimeSource.now();
    }

    public static synchronized void setTimeSource(TimeSource timeSource) {
        synchronized (SystemTime.class) {
            sTimeSource = timeSource;
        }
    }
}
