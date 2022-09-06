package com.codahale.metrics;
/* loaded from: classes9.dex */
public interface Metered extends Metric, Counting {
    long getCount();

    double getFifteenMinuteRate();

    double getFiveMinuteRate();

    double getMeanRate();

    double getOneMinuteRate();
}
