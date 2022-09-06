package com.amazon.alexa.fitness.metrics;

import android.os.SystemClock;
import kotlin.Metadata;
/* compiled from: ElapsedRealTimeClock.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/ElapsedRealTimeClock;", "Lcom/amazon/alexa/fitness/metrics/MonotonicallyIncreasingTimeSource;", "()V", "nowMilli", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class ElapsedRealTimeClock implements MonotonicallyIncreasingTimeSource {
    @Override // com.amazon.alexa.fitness.metrics.TimeSource
    public long nowMilli() {
        return SystemClock.elapsedRealtime();
    }
}
