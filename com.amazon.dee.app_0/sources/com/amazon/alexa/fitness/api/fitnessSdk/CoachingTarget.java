package com.amazon.alexa.fitness.api.fitnessSdk;

import com.amazon.deecomms.common.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SessionDataModels.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/CoachingTarget;", "Ljava/io/Serializable;", "()V", "Distance", Constants.CALL_DURATION_KEY, "Lcom/amazon/alexa/fitness/api/fitnessSdk/CoachingTarget$Distance;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/CoachingTarget$Duration;", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public abstract class CoachingTarget implements Serializable {

    /* compiled from: SessionDataModels.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/CoachingTarget$Distance;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/CoachingTarget;", "value", "", "unit", "Lcom/amazon/alexa/fitness/api/fitnessSdk/CoachingUnits;", "(DLcom/amazon/alexa/fitness/api/fitnessSdk/CoachingUnits;)V", "getUnit", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/CoachingUnits;", "getValue", "()D", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Distance extends CoachingTarget {
        @NotNull
        private final CoachingUnits unit;
        private final double value;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Distance(double d, @NotNull CoachingUnits unit) {
            super(null);
            Intrinsics.checkParameterIsNotNull(unit, "unit");
            this.value = d;
            this.unit = unit;
        }

        public static /* synthetic */ Distance copy$default(Distance distance, double d, CoachingUnits coachingUnits, int i, Object obj) {
            if ((i & 1) != 0) {
                d = distance.value;
            }
            if ((i & 2) != 0) {
                coachingUnits = distance.unit;
            }
            return distance.copy(d, coachingUnits);
        }

        public final double component1() {
            return this.value;
        }

        @NotNull
        public final CoachingUnits component2() {
            return this.unit;
        }

        @NotNull
        public final Distance copy(double d, @NotNull CoachingUnits unit) {
            Intrinsics.checkParameterIsNotNull(unit, "unit");
            return new Distance(d, unit);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (!(obj instanceof Distance)) {
                    return false;
                }
                Distance distance = (Distance) obj;
                return Double.compare(this.value, distance.value) == 0 && Intrinsics.areEqual(this.unit, distance.unit);
            }
            return true;
        }

        @NotNull
        public final CoachingUnits getUnit() {
            return this.unit;
        }

        public final double getValue() {
            return this.value;
        }

        public int hashCode() {
            long doubleToLongBits = Double.doubleToLongBits(this.value);
            int i = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31;
            CoachingUnits coachingUnits = this.unit;
            return i + (coachingUnits != null ? coachingUnits.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Distance(value=");
            outline107.append(this.value);
            outline107.append(", unit=");
            outline107.append(this.unit);
            outline107.append(")");
            return outline107.toString();
        }
    }

    /* compiled from: SessionDataModels.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/CoachingTarget$Duration;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/CoachingTarget;", "timeMillis", "", "(J)V", "getTimeMillis", "()J", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Duration extends CoachingTarget {
        private final long timeMillis;

        public Duration(long j) {
            super(null);
            this.timeMillis = j;
        }

        public static /* synthetic */ Duration copy$default(Duration duration, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                j = duration.timeMillis;
            }
            return duration.copy(j);
        }

        public final long component1() {
            return this.timeMillis;
        }

        @NotNull
        public final Duration copy(long j) {
            return new Duration(j);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                return (obj instanceof Duration) && this.timeMillis == ((Duration) obj).timeMillis;
            }
            return true;
        }

        public final long getTimeMillis() {
            return this.timeMillis;
        }

        public int hashCode() {
            long j = this.timeMillis;
            return (int) (j ^ (j >>> 32));
        }

        @NotNull
        public String toString() {
            return GeneratedOutlineSupport1.outline87(GeneratedOutlineSupport1.outline107("Duration(timeMillis="), this.timeMillis, ")");
        }
    }

    private CoachingTarget() {
    }

    public /* synthetic */ CoachingTarget(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
