package com.amazon.alexa.fitness.metrics;

import com.amazon.alexa.fitness.util.PreconditionsKt;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: MetricEvent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0012\u001a\u00020\u0005J\b\u0010\u0013\u001a\u00020\u0003H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/amazon/alexa/fitness/metrics/Counter;", "Lcom/amazon/alexa/fitness/metrics/DataPoint;", "metric", "", "value", "", "(Ljava/lang/String;J)V", "getMetric", "()Ljava/lang/String;", "getValue", "()J", "equals", "", "other", "", "hashCode", "", "increment", "by", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class Counter implements DataPoint {
    @NotNull
    private final String metric;
    private final long value;

    public Counter(@NotNull String metric, long j) {
        Intrinsics.checkParameterIsNotNull(metric, "metric");
        this.metric = metric;
        this.value = j;
        PreconditionsKt.assertNotNullOrBlank$default(getMetric(), null, 1, null);
    }

    public static /* synthetic */ Counter increment$default(Counter counter, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 1;
        }
        return counter.increment(j);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(Counter.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            Counter counter = (Counter) obj;
            return Intrinsics.areEqual(getMetric(), counter.getMetric()) && this.value == counter.value;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.metrics.Counter");
    }

    @Override // com.amazon.alexa.fitness.metrics.DataPoint
    @NotNull
    public String getMetric() {
        return this.metric;
    }

    public final long getValue() {
        return this.value;
    }

    public int hashCode() {
        return Objects.hash(getMetric(), Long.valueOf(this.value));
    }

    @NotNull
    public final Counter increment(long j) {
        return new Counter(getMetric(), this.value + j);
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Counter(metric='");
        outline107.append(getMetric());
        outline107.append("', value=");
        outline107.append(this.value);
        outline107.append(')');
        return outline107.toString();
    }

    public /* synthetic */ Counter(String str, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? 0L : j);
    }
}
