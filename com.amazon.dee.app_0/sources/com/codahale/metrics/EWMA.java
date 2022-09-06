package com.codahale.metrics;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import java.util.concurrent.TimeUnit;
/* loaded from: classes9.dex */
public class EWMA {
    private static final int FIFTEEN_MINUTES = 15;
    private static final int FIVE_MINUTES = 5;
    private static final int INTERVAL = 5;
    private static final int ONE_MINUTE = 1;
    private static final double SECONDS_PER_MINUTE = 60.0d;
    private final double alpha;
    private final double interval;
    private static final double M1_ALPHA = 1.0d - Math.exp(-0.08333333333333333d);
    private static final double M5_ALPHA = 1.0d - Math.exp(-0.016666666666666666d);
    private static final double M15_ALPHA = 1.0d - Math.exp(-0.005555555555555555d);
    private volatile boolean initialized = false;
    private volatile double rate = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    private final LongAdder uncounted = new LongAdder();

    public EWMA(double d, long j, TimeUnit timeUnit) {
        this.interval = timeUnit.toNanos(j);
        this.alpha = d;
    }

    public static EWMA fifteenMinuteEWMA() {
        return new EWMA(M15_ALPHA, 5L, TimeUnit.SECONDS);
    }

    public static EWMA fiveMinuteEWMA() {
        return new EWMA(M5_ALPHA, 5L, TimeUnit.SECONDS);
    }

    public static EWMA oneMinuteEWMA() {
        return new EWMA(M1_ALPHA, 5L, TimeUnit.SECONDS);
    }

    public double getRate(TimeUnit timeUnit) {
        return this.rate * timeUnit.toNanos(1L);
    }

    public void tick() {
        double sumThenReset = this.uncounted.sumThenReset() / this.interval;
        if (!this.initialized) {
            this.rate = sumThenReset;
            this.initialized = true;
            return;
        }
        this.rate = ((sumThenReset - this.rate) * this.alpha) + this.rate;
    }

    public void update(long j) {
        this.uncounted.add(j);
    }
}
