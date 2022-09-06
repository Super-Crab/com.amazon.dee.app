package com.amazon.alexa.fitness.model.biometric;

import com.amazon.alexa.fitness.api.fitnessSdk.ActivityType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.EnumMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessSessionSummary.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\u0010\u0006J\u0015\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0003J\u001f\u0010\n\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/amazon/alexa/fitness/model/biometric/FitnessSessionSummary;", "Ljava/io/Serializable;", "activitySummaries", "Ljava/util/EnumMap;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/ActivityType;", "Lcom/amazon/alexa/fitness/model/biometric/ActivitySummary;", "(Ljava/util/EnumMap;)V", "getActivitySummaries", "()Ljava/util/EnumMap;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessSessionSummary implements Serializable {
    @NotNull
    private final EnumMap<ActivityType, ActivitySummary> activitySummaries;

    public FitnessSessionSummary(@NotNull EnumMap<ActivityType, ActivitySummary> activitySummaries) {
        Intrinsics.checkParameterIsNotNull(activitySummaries, "activitySummaries");
        this.activitySummaries = activitySummaries;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FitnessSessionSummary copy$default(FitnessSessionSummary fitnessSessionSummary, EnumMap enumMap, int i, Object obj) {
        if ((i & 1) != 0) {
            enumMap = fitnessSessionSummary.activitySummaries;
        }
        return fitnessSessionSummary.copy(enumMap);
    }

    @NotNull
    public final EnumMap<ActivityType, ActivitySummary> component1() {
        return this.activitySummaries;
    }

    @NotNull
    public final FitnessSessionSummary copy(@NotNull EnumMap<ActivityType, ActivitySummary> activitySummaries) {
        Intrinsics.checkParameterIsNotNull(activitySummaries, "activitySummaries");
        return new FitnessSessionSummary(activitySummaries);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof FitnessSessionSummary) && Intrinsics.areEqual(this.activitySummaries, ((FitnessSessionSummary) obj).activitySummaries);
        }
        return true;
    }

    @NotNull
    public final EnumMap<ActivityType, ActivitySummary> getActivitySummaries() {
        return this.activitySummaries;
    }

    public int hashCode() {
        EnumMap<ActivityType, ActivitySummary> enumMap = this.activitySummaries;
        if (enumMap != null) {
            return enumMap.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FitnessSessionSummary(activitySummaries=");
        outline107.append(this.activitySummaries);
        outline107.append(")");
        return outline107.toString();
    }
}
