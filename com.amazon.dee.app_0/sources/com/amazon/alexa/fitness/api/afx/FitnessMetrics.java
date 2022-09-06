package com.amazon.alexa.fitness.api.afx;

import com.amazon.alexa.fitness.api.fitnessSdk.Units;
import com.amazon.alexa.fitness.metrics.Metrics;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessMetrics.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0001\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0082\u0001\u0001\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/fitness/api/afx/FitnessMetrics;", "Ljava/io/Serializable;", "()V", "StepBased", "Lcom/amazon/alexa/fitness/api/afx/FitnessMetrics$StepBased;", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public abstract class FitnessMetrics implements Serializable {
    private FitnessMetrics() {
    }

    public /* synthetic */ FitnessMetrics(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* compiled from: FitnessMetrics.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B[\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\u0010\nJ\u0015\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003J\u0015\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003J\u0015\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003J\u0015\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003Ja\u0010\u0014\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\u00032\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0014\b\u0002\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u001d\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\f¨\u0006\u001c"}, d2 = {"Lcom/amazon/alexa/fitness/api/afx/FitnessMetrics$StepBased;", "Lcom/amazon/alexa/fitness/api/afx/FitnessMetrics;", "calories", "Lkotlin/Pair;", "", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Units;", Metrics.STEPS, "", "distance", "speed", "(Lkotlin/Pair;Lkotlin/Pair;Lkotlin/Pair;Lkotlin/Pair;)V", "getCalories", "()Lkotlin/Pair;", "getDistance", "getSpeed", "getSteps", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "toString", "", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class StepBased extends FitnessMetrics {
        @NotNull
        private final Pair<Double, Units> calories;
        @NotNull
        private final Pair<Double, Units> distance;
        @NotNull
        private final Pair<Double, Units> speed;
        @NotNull
        private final Pair<Integer, Units> steps;

        public /* synthetic */ StepBased(Pair pair, Pair pair2, Pair pair3, Pair pair4, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(pair, (i & 2) != 0 ? new Pair(0, Units.Count) : pair2, (i & 4) != 0 ? new Pair(Double.valueOf((double) FrostVideoEffectController.VIDEO_STRENGTH_CLEAR), Units.Miles) : pair3, (i & 8) != 0 ? new Pair(Double.valueOf((double) FrostVideoEffectController.VIDEO_STRENGTH_CLEAR), Units.MilesPerHour) : pair4);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ StepBased copy$default(StepBased stepBased, Pair pair, Pair pair2, Pair pair3, Pair pair4, int i, Object obj) {
            if ((i & 1) != 0) {
                pair = stepBased.calories;
            }
            if ((i & 2) != 0) {
                pair2 = stepBased.steps;
            }
            if ((i & 4) != 0) {
                pair3 = stepBased.distance;
            }
            if ((i & 8) != 0) {
                pair4 = stepBased.speed;
            }
            return stepBased.copy(pair, pair2, pair3, pair4);
        }

        @NotNull
        public final Pair<Double, Units> component1() {
            return this.calories;
        }

        @NotNull
        public final Pair<Integer, Units> component2() {
            return this.steps;
        }

        @NotNull
        public final Pair<Double, Units> component3() {
            return this.distance;
        }

        @NotNull
        public final Pair<Double, Units> component4() {
            return this.speed;
        }

        @NotNull
        public final StepBased copy(@NotNull Pair<Double, ? extends Units> calories, @NotNull Pair<Integer, ? extends Units> steps, @NotNull Pair<Double, ? extends Units> distance, @NotNull Pair<Double, ? extends Units> speed) {
            Intrinsics.checkParameterIsNotNull(calories, "calories");
            Intrinsics.checkParameterIsNotNull(steps, "steps");
            Intrinsics.checkParameterIsNotNull(distance, "distance");
            Intrinsics.checkParameterIsNotNull(speed, "speed");
            return new StepBased(calories, steps, distance, speed);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (!(obj instanceof StepBased)) {
                    return false;
                }
                StepBased stepBased = (StepBased) obj;
                return Intrinsics.areEqual(this.calories, stepBased.calories) && Intrinsics.areEqual(this.steps, stepBased.steps) && Intrinsics.areEqual(this.distance, stepBased.distance) && Intrinsics.areEqual(this.speed, stepBased.speed);
            }
            return true;
        }

        @NotNull
        public final Pair<Double, Units> getCalories() {
            return this.calories;
        }

        @NotNull
        public final Pair<Double, Units> getDistance() {
            return this.distance;
        }

        @NotNull
        public final Pair<Double, Units> getSpeed() {
            return this.speed;
        }

        @NotNull
        public final Pair<Integer, Units> getSteps() {
            return this.steps;
        }

        public int hashCode() {
            Pair<Double, Units> pair = this.calories;
            int i = 0;
            int hashCode = (pair != null ? pair.hashCode() : 0) * 31;
            Pair<Integer, Units> pair2 = this.steps;
            int hashCode2 = (hashCode + (pair2 != null ? pair2.hashCode() : 0)) * 31;
            Pair<Double, Units> pair3 = this.distance;
            int hashCode3 = (hashCode2 + (pair3 != null ? pair3.hashCode() : 0)) * 31;
            Pair<Double, Units> pair4 = this.speed;
            if (pair4 != null) {
                i = pair4.hashCode();
            }
            return hashCode3 + i;
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("StepBased(calories=");
            outline107.append(this.calories);
            outline107.append(", steps=");
            outline107.append(this.steps);
            outline107.append(", distance=");
            outline107.append(this.distance);
            outline107.append(", speed=");
            outline107.append(this.speed);
            outline107.append(")");
            return outline107.toString();
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public StepBased(@NotNull Pair<Double, ? extends Units> calories, @NotNull Pair<Integer, ? extends Units> steps, @NotNull Pair<Double, ? extends Units> distance, @NotNull Pair<Double, ? extends Units> speed) {
            super(null);
            Intrinsics.checkParameterIsNotNull(calories, "calories");
            Intrinsics.checkParameterIsNotNull(steps, "steps");
            Intrinsics.checkParameterIsNotNull(distance, "distance");
            Intrinsics.checkParameterIsNotNull(speed, "speed");
            this.calories = calories;
            this.steps = steps;
            this.distance = distance;
            this.speed = speed;
        }
    }
}
