package com.amazon.alexa.accessory.repositories.fitness;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
import java.util.UUID;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes6.dex */
public final class FitnessSession {
    private final int sequenceId;
    private final UUID uuid;
    private final WorkoutState workoutState;

    /* loaded from: classes6.dex */
    public enum WorkoutState {
        UNKNOWN,
        IDLE,
        ACTIVE,
        PAUSED
    }

    public FitnessSession(UUID uuid, int i, WorkoutState workoutState) {
        Preconditions.notNull(uuid, "uuid");
        Preconditions.notNull(workoutState, "workoutState");
        this.uuid = uuid;
        this.sequenceId = i;
        this.workoutState = workoutState;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || FitnessSession.class != obj.getClass()) {
            return false;
        }
        FitnessSession fitnessSession = (FitnessSession) obj;
        return this.sequenceId == fitnessSession.sequenceId && Objects.equals(this.uuid, fitnessSession.uuid) && this.workoutState == fitnessSession.workoutState;
    }

    public int getSequenceId() {
        return this.sequenceId;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public WorkoutState getWorkoutState() {
        return this.workoutState;
    }

    public int hashCode() {
        return Objects.hash(this.uuid, Integer.valueOf(this.sequenceId), this.workoutState);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FitnessSession{uuid=");
        outline107.append(this.uuid);
        outline107.append(", sequenceId=");
        outline107.append(this.sequenceId);
        outline107.append(", workoutState=");
        outline107.append(this.workoutState);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
