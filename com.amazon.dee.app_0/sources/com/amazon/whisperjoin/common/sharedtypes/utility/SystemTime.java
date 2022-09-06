package com.amazon.whisperjoin.common.sharedtypes.utility;
/* loaded from: classes13.dex */
public class SystemTime {
    private static final TimeSource REAL_TIME = new TimeSource() { // from class: com.amazon.whisperjoin.common.sharedtypes.utility.SystemTime.1
        @Override // com.amazon.whisperjoin.common.sharedtypes.utility.SystemTime.TimeSource
        public long now() {
            return System.currentTimeMillis();
        }

        @Override // com.amazon.whisperjoin.common.sharedtypes.utility.SystemTime.TimeSource
        public long nowNano() {
            return System.nanoTime();
        }
    };
    private static TimeSource sTimeSource = REAL_TIME;

    /* loaded from: classes13.dex */
    public interface TimeSource {
        long now();

        long nowNano();
    }

    public static long now() {
        return sTimeSource.now();
    }

    public static long nowNano() {
        return sTimeSource.nowNano();
    }

    public static synchronized void setTimeSource(TimeSource timeSource) {
        synchronized (SystemTime.class) {
            sTimeSource = timeSource;
        }
    }
}
