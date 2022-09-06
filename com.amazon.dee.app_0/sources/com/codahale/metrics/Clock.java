package com.codahale.metrics;
/* loaded from: classes9.dex */
public abstract class Clock {
    private static final Clock DEFAULT = new UserTimeClock();

    /* loaded from: classes9.dex */
    public static class UserTimeClock extends Clock {
        @Override // com.codahale.metrics.Clock
        public long getTick() {
            return System.nanoTime();
        }
    }

    public static Clock defaultClock() {
        return DEFAULT;
    }

    public abstract long getTick();

    public long getTime() {
        return System.currentTimeMillis();
    }
}
