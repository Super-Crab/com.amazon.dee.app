package com.amazon.alexa.fitness.sdk;

import amazon.speech.model.DirectiveIntent;
import com.amazon.alexa.accessory.repositories.fitness.FitnessSession;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SessionRecoveryManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/AccessoryFitnessSession;", "Ljava/io/Serializable;", "uuid", "Ljava/util/UUID;", DirectiveIntent.INTENT_KEY_SEQUENCE_ID, "", "workoutState", "Lcom/amazon/alexa/accessory/repositories/fitness/FitnessSession$WorkoutState;", "(Ljava/util/UUID;ILcom/amazon/alexa/accessory/repositories/fitness/FitnessSession$WorkoutState;)V", "getSequenceId", "()I", "getUuid", "()Ljava/util/UUID;", "getWorkoutState", "()Lcom/amazon/alexa/accessory/repositories/fitness/FitnessSession$WorkoutState;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class AccessoryFitnessSession implements Serializable {
    private final int sequenceId;
    @NotNull
    private final UUID uuid;
    @NotNull
    private final FitnessSession.WorkoutState workoutState;

    public AccessoryFitnessSession(@NotNull UUID uuid, int i, @NotNull FitnessSession.WorkoutState workoutState) {
        Intrinsics.checkParameterIsNotNull(uuid, "uuid");
        Intrinsics.checkParameterIsNotNull(workoutState, "workoutState");
        this.uuid = uuid;
        this.sequenceId = i;
        this.workoutState = workoutState;
    }

    public static /* synthetic */ AccessoryFitnessSession copy$default(AccessoryFitnessSession accessoryFitnessSession, UUID uuid, int i, FitnessSession.WorkoutState workoutState, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            uuid = accessoryFitnessSession.uuid;
        }
        if ((i2 & 2) != 0) {
            i = accessoryFitnessSession.sequenceId;
        }
        if ((i2 & 4) != 0) {
            workoutState = accessoryFitnessSession.workoutState;
        }
        return accessoryFitnessSession.copy(uuid, i, workoutState);
    }

    @NotNull
    public final UUID component1() {
        return this.uuid;
    }

    public final int component2() {
        return this.sequenceId;
    }

    @NotNull
    public final FitnessSession.WorkoutState component3() {
        return this.workoutState;
    }

    @NotNull
    public final AccessoryFitnessSession copy(@NotNull UUID uuid, int i, @NotNull FitnessSession.WorkoutState workoutState) {
        Intrinsics.checkParameterIsNotNull(uuid, "uuid");
        Intrinsics.checkParameterIsNotNull(workoutState, "workoutState");
        return new AccessoryFitnessSession(uuid, i, workoutState);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof AccessoryFitnessSession)) {
                return false;
            }
            AccessoryFitnessSession accessoryFitnessSession = (AccessoryFitnessSession) obj;
            return Intrinsics.areEqual(this.uuid, accessoryFitnessSession.uuid) && this.sequenceId == accessoryFitnessSession.sequenceId && Intrinsics.areEqual(this.workoutState, accessoryFitnessSession.workoutState);
        }
        return true;
    }

    public final int getSequenceId() {
        return this.sequenceId;
    }

    @NotNull
    public final UUID getUuid() {
        return this.uuid;
    }

    @NotNull
    public final FitnessSession.WorkoutState getWorkoutState() {
        return this.workoutState;
    }

    public int hashCode() {
        UUID uuid = this.uuid;
        int i = 0;
        int hashCode = (((uuid != null ? uuid.hashCode() : 0) * 31) + this.sequenceId) * 31;
        FitnessSession.WorkoutState workoutState = this.workoutState;
        if (workoutState != null) {
            i = workoutState.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AccessoryFitnessSession(uuid=");
        outline107.append(this.uuid);
        outline107.append(", sequenceId=");
        outline107.append(this.sequenceId);
        outline107.append(", workoutState=");
        outline107.append(this.workoutState);
        outline107.append(")");
        return outline107.toString();
    }
}
