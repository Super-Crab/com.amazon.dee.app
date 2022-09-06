package com.amazon.alexa.fitness.algorithm.calories;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.fitness.algorithms.ActivityType;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import kotlin.Metadata;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CaloriesActivityBuffer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\fJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0014\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00050!H\u0007J\t\u0010\"\u001a\u00020\u0005HÖ\u0001J\t\u0010#\u001a\u00020$HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00050\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0013\"\u0004\b\u0015\u0010\u0016¨\u0006%"}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/calories/CaloriesActivityWindowSummary;", "", "durationSeconds", "", "accumulatedSteps", "", "(DI)V", "getAccumulatedSteps", "()I", "setAccumulatedSteps", "(I)V", "activityClassification", "Lcom/amazon/alexa/fitness/algorithms/ActivityType;", "getActivityClassification", "()Lcom/amazon/alexa/fitness/algorithms/ActivityType;", "activityTypeCount", "", "durationMinutes", "getDurationMinutes", "()D", "getDurationSeconds", "setDurationSeconds", "(D)V", "addToActivityClassification", "", "algorithmActivityType", "component1", "component2", "copy", "equals", "", "other", "getPrioritizedActivityTypeCount", "Ljava/util/SortedMap;", "hashCode", "toString", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class CaloriesActivityWindowSummary {
    private int accumulatedSteps;
    private Map<ActivityType, Integer> activityTypeCount;
    private double durationSeconds;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[ActivityType.values().length];

        static {
            $EnumSwitchMapping$0[ActivityType.ACTIVITY_TYPE_IDLE.ordinal()] = 1;
            $EnumSwitchMapping$0[ActivityType.ACTIVITY_TYPE_WALK.ordinal()] = 2;
            $EnumSwitchMapping$0[ActivityType.ACTIVITY_TYPE_RUN.ordinal()] = 3;
        }
    }

    public CaloriesActivityWindowSummary() {
        this(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR, 0, 3, null);
    }

    public CaloriesActivityWindowSummary(double d, int i) {
        this.durationSeconds = d;
        this.accumulatedSteps = i;
        this.activityTypeCount = new LinkedHashMap();
    }

    public static /* synthetic */ CaloriesActivityWindowSummary copy$default(CaloriesActivityWindowSummary caloriesActivityWindowSummary, double d, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            d = caloriesActivityWindowSummary.durationSeconds;
        }
        if ((i2 & 2) != 0) {
            i = caloriesActivityWindowSummary.accumulatedSteps;
        }
        return caloriesActivityWindowSummary.copy(d, i);
    }

    public final void addToActivityClassification(@NotNull ActivityType algorithmActivityType) {
        Intrinsics.checkParameterIsNotNull(algorithmActivityType, "algorithmActivityType");
        Integer num = this.activityTypeCount.get(algorithmActivityType);
        this.activityTypeCount.put(algorithmActivityType, Integer.valueOf((num != null ? num.intValue() : 0) + 1));
    }

    public final double component1() {
        return this.durationSeconds;
    }

    public final int component2() {
        return this.accumulatedSteps;
    }

    @NotNull
    public final CaloriesActivityWindowSummary copy(double d, int i) {
        return new CaloriesActivityWindowSummary(d, i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof CaloriesActivityWindowSummary)) {
                return false;
            }
            CaloriesActivityWindowSummary caloriesActivityWindowSummary = (CaloriesActivityWindowSummary) obj;
            return Double.compare(this.durationSeconds, caloriesActivityWindowSummary.durationSeconds) == 0 && this.accumulatedSteps == caloriesActivityWindowSummary.accumulatedSteps;
        }
        return true;
    }

    public final int getAccumulatedSteps() {
        return this.accumulatedSteps;
    }

    @NotNull
    public final ActivityType getActivityClassification() {
        Object next;
        ActivityType activityType;
        Iterator<T> it2 = getPrioritizedActivityTypeCount().entrySet().iterator();
        if (!it2.hasNext()) {
            next = null;
        } else {
            next = it2.next();
            if (it2.hasNext()) {
                Integer num = (Integer) ((Map.Entry) next).getValue();
                do {
                    Object next2 = it2.next();
                    Integer num2 = (Integer) ((Map.Entry) next2).getValue();
                    if (num.compareTo(num2) < 0) {
                        next = next2;
                        num = num2;
                    }
                } while (it2.hasNext());
            }
        }
        Map.Entry entry = (Map.Entry) next;
        return (entry == null || (activityType = (ActivityType) entry.getKey()) == null) ? ActivityType.ACTIVITY_TYPE_IDLE : activityType;
    }

    public final double getDurationMinutes() {
        return this.durationSeconds / 60.0d;
    }

    public final double getDurationSeconds() {
        return this.durationSeconds;
    }

    @VisibleForTesting
    @NotNull
    public final SortedMap<ActivityType, Integer> getPrioritizedActivityTypeCount() {
        SortedMap<ActivityType, Integer> sortedMap;
        CaloriesActivityWindowSummary$getPrioritizedActivityTypeCount$1 caloriesActivityWindowSummary$getPrioritizedActivityTypeCount$1 = CaloriesActivityWindowSummary$getPrioritizedActivityTypeCount$1.INSTANCE;
        sortedMap = MapsKt__MapsJVMKt.toSortedMap(this.activityTypeCount, CaloriesActivityWindowSummary$getPrioritizedActivityTypeCount$2.INSTANCE);
        return sortedMap;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.durationSeconds);
        return (((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + this.accumulatedSteps;
    }

    public final void setAccumulatedSteps(int i) {
        this.accumulatedSteps = i;
    }

    public final void setDurationSeconds(double d) {
        this.durationSeconds = d;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CaloriesActivityWindowSummary(durationSeconds=");
        outline107.append(this.durationSeconds);
        outline107.append(", accumulatedSteps=");
        return GeneratedOutlineSupport1.outline86(outline107, this.accumulatedSteps, ")");
    }

    public /* synthetic */ CaloriesActivityWindowSummary(double d, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? FrostVideoEffectController.VIDEO_STRENGTH_CLEAR : d, (i2 & 2) != 0 ? 0 : i);
    }
}
