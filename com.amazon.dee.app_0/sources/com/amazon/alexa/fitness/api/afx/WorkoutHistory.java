package com.amazon.alexa.fitness.api.afx;

import com.amazon.alexa.fitness.api.afits.FitnessSession;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessSessionOrchestrator.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0016\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0016\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u0013J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J6\u0010\u001a\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001¢\u0006\u0002\u0010\u001bJ\u0013\u0010\u001c\u001a\u00020\b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\u0006\u0010 \u001a\u00020!J\t\u0010\"\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006#"}, d2 = {"Lcom/amazon/alexa/fitness/api/afx/WorkoutHistory;", "", "workouts", "", "Lcom/amazon/alexa/fitness/api/afits/FitnessSession;", "nextToken", "", "hasLoadedInitialWorkouts", "", "([Lcom/amazon/alexa/fitness/api/afits/FitnessSession;Ljava/lang/String;Z)V", "getHasLoadedInitialWorkouts", "()Z", "setHasLoadedInitialWorkouts", "(Z)V", "getNextToken", "()Ljava/lang/String;", "setNextToken", "(Ljava/lang/String;)V", "getWorkouts", "()[Lcom/amazon/alexa/fitness/api/afits/FitnessSession;", "setWorkouts", "([Lcom/amazon/alexa/fitness/api/afits/FitnessSession;)V", "[Lcom/amazon/alexa/fitness/api/afits/FitnessSession;", "component1", "component2", "component3", "copy", "([Lcom/amazon/alexa/fitness/api/afits/FitnessSession;Ljava/lang/String;Z)Lcom/amazon/alexa/fitness/api/afx/WorkoutHistory;", "equals", "other", "hashCode", "", "reset", "", "toString", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class WorkoutHistory {
    private boolean hasLoadedInitialWorkouts;
    @Nullable
    private String nextToken;
    @Nullable
    private FitnessSession[] workouts;

    public WorkoutHistory(@Nullable FitnessSession[] fitnessSessionArr, @Nullable String str, boolean z) {
        this.workouts = fitnessSessionArr;
        this.nextToken = str;
        this.hasLoadedInitialWorkouts = z;
    }

    public static /* synthetic */ WorkoutHistory copy$default(WorkoutHistory workoutHistory, FitnessSession[] fitnessSessionArr, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            fitnessSessionArr = workoutHistory.workouts;
        }
        if ((i & 2) != 0) {
            str = workoutHistory.nextToken;
        }
        if ((i & 4) != 0) {
            z = workoutHistory.hasLoadedInitialWorkouts;
        }
        return workoutHistory.copy(fitnessSessionArr, str, z);
    }

    @Nullable
    public final FitnessSession[] component1() {
        return this.workouts;
    }

    @Nullable
    public final String component2() {
        return this.nextToken;
    }

    public final boolean component3() {
        return this.hasLoadedInitialWorkouts;
    }

    @NotNull
    public final WorkoutHistory copy(@Nullable FitnessSession[] fitnessSessionArr, @Nullable String str, boolean z) {
        return new WorkoutHistory(fitnessSessionArr, str, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof WorkoutHistory)) {
                return false;
            }
            WorkoutHistory workoutHistory = (WorkoutHistory) obj;
            return Intrinsics.areEqual(this.workouts, workoutHistory.workouts) && Intrinsics.areEqual(this.nextToken, workoutHistory.nextToken) && this.hasLoadedInitialWorkouts == workoutHistory.hasLoadedInitialWorkouts;
        }
        return true;
    }

    public final boolean getHasLoadedInitialWorkouts() {
        return this.hasLoadedInitialWorkouts;
    }

    @Nullable
    public final String getNextToken() {
        return this.nextToken;
    }

    @Nullable
    public final FitnessSession[] getWorkouts() {
        return this.workouts;
    }

    public int hashCode() {
        FitnessSession[] fitnessSessionArr = this.workouts;
        int i = 0;
        int hashCode = (fitnessSessionArr != null ? Arrays.hashCode(fitnessSessionArr) : 0) * 31;
        String str = this.nextToken;
        if (str != null) {
            i = str.hashCode();
        }
        int i2 = (hashCode + i) * 31;
        boolean z = this.hasLoadedInitialWorkouts;
        if (z) {
            z = true;
        }
        int i3 = z ? 1 : 0;
        int i4 = z ? 1 : 0;
        return i2 + i3;
    }

    public final void reset() {
        this.workouts = null;
        this.nextToken = null;
        this.hasLoadedInitialWorkouts = false;
    }

    public final void setHasLoadedInitialWorkouts(boolean z) {
        this.hasLoadedInitialWorkouts = z;
    }

    public final void setNextToken(@Nullable String str) {
        this.nextToken = str;
    }

    public final void setWorkouts(@Nullable FitnessSession[] fitnessSessionArr) {
        this.workouts = fitnessSessionArr;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WorkoutHistory(workouts=");
        outline107.append(Arrays.toString(this.workouts));
        outline107.append(", nextToken=");
        outline107.append(this.nextToken);
        outline107.append(", hasLoadedInitialWorkouts=");
        return GeneratedOutlineSupport1.outline97(outline107, this.hasLoadedInitialWorkouts, ")");
    }

    public /* synthetic */ WorkoutHistory(FitnessSession[] fitnessSessionArr, String str, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(fitnessSessionArr, str, (i & 4) != 0 ? false : z);
    }
}
