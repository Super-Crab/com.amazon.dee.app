package com.amazon.alexa.fitness.algorithm;

import com.amazon.alexa.fitness.api.fitnessSdk.WorkoutType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessAlgorithm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00070\u0005¢\u0006\u0002\u0010\bJ\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0013\u0010\u000e\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00070\u0005HÆ\u0003J)\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0012\b\u0002\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00070\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0006HÖ\u0001R\u001b\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/FitnessAlgorithmRecoverableState;", "Ljava/io/Serializable;", "workoutType", "Lcom/amazon/alexa/fitness/api/fitnessSdk/WorkoutType;", "listOfAlgorithmIds", "", "", "Lcom/amazon/alexa/fitness/algorithm/AlgorithmId;", "(Lcom/amazon/alexa/fitness/api/fitnessSdk/WorkoutType;Ljava/util/List;)V", "getListOfAlgorithmIds", "()Ljava/util/List;", "getWorkoutType", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/WorkoutType;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class FitnessAlgorithmRecoverableState implements Serializable {
    @NotNull
    private final List<String> listOfAlgorithmIds;
    @Nullable
    private final WorkoutType workoutType;

    public FitnessAlgorithmRecoverableState(@Nullable WorkoutType workoutType, @NotNull List<String> listOfAlgorithmIds) {
        Intrinsics.checkParameterIsNotNull(listOfAlgorithmIds, "listOfAlgorithmIds");
        this.workoutType = workoutType;
        this.listOfAlgorithmIds = listOfAlgorithmIds;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FitnessAlgorithmRecoverableState copy$default(FitnessAlgorithmRecoverableState fitnessAlgorithmRecoverableState, WorkoutType workoutType, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            workoutType = fitnessAlgorithmRecoverableState.workoutType;
        }
        if ((i & 2) != 0) {
            list = fitnessAlgorithmRecoverableState.listOfAlgorithmIds;
        }
        return fitnessAlgorithmRecoverableState.copy(workoutType, list);
    }

    @Nullable
    public final WorkoutType component1() {
        return this.workoutType;
    }

    @NotNull
    public final List<String> component2() {
        return this.listOfAlgorithmIds;
    }

    @NotNull
    public final FitnessAlgorithmRecoverableState copy(@Nullable WorkoutType workoutType, @NotNull List<String> listOfAlgorithmIds) {
        Intrinsics.checkParameterIsNotNull(listOfAlgorithmIds, "listOfAlgorithmIds");
        return new FitnessAlgorithmRecoverableState(workoutType, listOfAlgorithmIds);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof FitnessAlgorithmRecoverableState)) {
                return false;
            }
            FitnessAlgorithmRecoverableState fitnessAlgorithmRecoverableState = (FitnessAlgorithmRecoverableState) obj;
            return Intrinsics.areEqual(this.workoutType, fitnessAlgorithmRecoverableState.workoutType) && Intrinsics.areEqual(this.listOfAlgorithmIds, fitnessAlgorithmRecoverableState.listOfAlgorithmIds);
        }
        return true;
    }

    @NotNull
    public final List<String> getListOfAlgorithmIds() {
        return this.listOfAlgorithmIds;
    }

    @Nullable
    public final WorkoutType getWorkoutType() {
        return this.workoutType;
    }

    public int hashCode() {
        WorkoutType workoutType = this.workoutType;
        int i = 0;
        int hashCode = (workoutType != null ? workoutType.hashCode() : 0) * 31;
        List<String> list = this.listOfAlgorithmIds;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FitnessAlgorithmRecoverableState(workoutType=");
        outline107.append(this.workoutType);
        outline107.append(", listOfAlgorithmIds=");
        return GeneratedOutlineSupport1.outline95(outline107, this.listOfAlgorithmIds, ")");
    }
}
