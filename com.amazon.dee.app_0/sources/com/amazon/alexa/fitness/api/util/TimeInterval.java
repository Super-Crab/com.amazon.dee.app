package com.amazon.alexa.fitness.api.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TimeInterval.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0000H\u0096\u0002J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\t\u001a\u0004\u0018\u00010\fH\u0096\u0002J\b\u0010\r\u001a\u00020\bH\u0016J\u0011\u0010\u000e\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0086\u0002J\u0011\u0010\u000f\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\u0000H\u0086\u0002J\u0006\u0010\u0010\u001a\u00020\u0003J\u0006\u0010\u0011\u001a\u00020\u0003J\u0006\u0010\u0012\u001a\u00020\u0003J\u0006\u0010\u0013\u001a\u00020\u0003J\u0006\u0010\u0014\u001a\u00020\u0003J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0006\u0010\u0017\u001a\u00020\u0000J\u0006\u0010\u0018\u001a\u00020\u0000J\u0006\u0010\u0019\u001a\u00020\u0000J\u0006\u0010\u001a\u001a\u00020\u0000J\t\u0010\u001b\u001a\u00020\u0000H\u0086\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u001c"}, d2 = {"Lcom/amazon/alexa/fitness/api/util/TimeInterval;", "", "milli", "", "(J)V", "getMilli", "()J", "compareTo", "", "other", "equals", "", "", "hashCode", "minus", "plus", "toDays", "toHours", "toMilliseconds", "toMinutes", "toSeconds", "toString", "", "truncateToDays", "truncateToHours", "truncateToMinutes", "truncateToSeconds", "unaryMinus", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class TimeInterval implements Comparable<TimeInterval> {
    private final long milli;

    public TimeInterval(long j) {
        this.milli = j;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual(TimeInterval.class, obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.api.util.TimeInterval");
        }
        return this.milli == ((TimeInterval) obj).milli;
    }

    public final long getMilli() {
        return this.milli;
    }

    public int hashCode() {
        return Long.valueOf(this.milli).hashCode();
    }

    @NotNull
    public final TimeInterval minus(@NotNull TimeInterval other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return new TimeInterval(this.milli - other.milli);
    }

    @NotNull
    public final TimeInterval plus(@NotNull TimeInterval other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return new TimeInterval(this.milli + other.milli);
    }

    public final long toDays() {
        return TimeUnit.DAYS.convert(this.milli, TimeUnit.MILLISECONDS);
    }

    public final long toHours() {
        return TimeUnit.HOURS.convert(this.milli, TimeUnit.MILLISECONDS);
    }

    public final long toMilliseconds() {
        return this.milli;
    }

    public final long toMinutes() {
        return TimeUnit.MINUTES.convert(this.milli, TimeUnit.MILLISECONDS);
    }

    public final long toSeconds() {
        return TimeUnit.SECONDS.convert(this.milli, TimeUnit.MILLISECONDS);
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TimeInterval(milli=");
        outline107.append(this.milli);
        outline107.append(')');
        return outline107.toString();
    }

    @NotNull
    public final TimeInterval truncateToDays() {
        return TimeIntervalKt.days(Long.valueOf(toDays()));
    }

    @NotNull
    public final TimeInterval truncateToHours() {
        return TimeIntervalKt.hours(Long.valueOf(toHours()));
    }

    @NotNull
    public final TimeInterval truncateToMinutes() {
        return TimeIntervalKt.minutes(Long.valueOf(toMinutes()));
    }

    @NotNull
    public final TimeInterval truncateToSeconds() {
        return TimeIntervalKt.seconds(Long.valueOf(toSeconds()));
    }

    @NotNull
    public final TimeInterval unaryMinus() {
        return new TimeInterval(-this.milli);
    }

    @Override // java.lang.Comparable
    public int compareTo(@NotNull TimeInterval other) {
        Intrinsics.checkParameterIsNotNull(other, "other");
        return (this.milli > other.milli ? 1 : (this.milli == other.milli ? 0 : -1));
    }
}
