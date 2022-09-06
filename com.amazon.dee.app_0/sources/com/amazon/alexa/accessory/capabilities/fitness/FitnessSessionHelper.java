package com.amazon.alexa.accessory.capabilities.fitness;

import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.protocol.Fitness;
import com.amazon.alexa.accessory.repositories.fitness.FitnessSession;
import com.google.protobuf.ByteString;
import java.io.IOException;
import java.util.UUID;
/* loaded from: classes.dex */
final class FitnessSessionHelper {

    /* renamed from: com.amazon.alexa.accessory.capabilities.fitness.FitnessSessionHelper$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$protocol$Fitness$WorkoutState;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$accessory$repositories$fitness$FitnessSession$WorkoutState = new int[FitnessSession.WorkoutState.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$accessory$repositories$fitness$FitnessSession$WorkoutState[FitnessSession.WorkoutState.IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$repositories$fitness$FitnessSession$WorkoutState[FitnessSession.WorkoutState.ACTIVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$repositories$fitness$FitnessSession$WorkoutState[FitnessSession.WorkoutState.PAUSED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $SwitchMap$com$amazon$alexa$accessory$protocol$Fitness$WorkoutState = new int[Fitness.WorkoutState.values().length];
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Fitness$WorkoutState[Fitness.WorkoutState.WORKOUT_STATE_IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Fitness$WorkoutState[Fitness.WorkoutState.WORKOUT_STATE_ACTIVE.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$accessory$protocol$Fitness$WorkoutState[Fitness.WorkoutState.WORKOUT_STATE_PAUSED.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    private FitnessSessionHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Fitness.SyncFitnessSession marshal(FitnessSession fitnessSession) throws IOException {
        Fitness.WorkoutState workoutState;
        if (fitnessSession != null) {
            int sequenceId = fitnessSession.getSequenceId();
            ByteString uuidToByteString = IOUtils.uuidToByteString(fitnessSession.getUuid());
            FitnessSession.WorkoutState workoutState2 = fitnessSession.getWorkoutState();
            int ordinal = workoutState2.ordinal();
            if (ordinal == 1) {
                workoutState = Fitness.WorkoutState.WORKOUT_STATE_IDLE;
            } else if (ordinal == 2) {
                workoutState = Fitness.WorkoutState.WORKOUT_STATE_ACTIVE;
            } else if (ordinal == 3) {
                workoutState = Fitness.WorkoutState.WORKOUT_STATE_PAUSED;
            } else {
                throw new IOException("Unsupported WorkoutState '" + workoutState2 + "', was a new state added?");
            }
            return Fitness.SyncFitnessSession.newBuilder().setSessionId(uuidToByteString).setSequenceId(sequenceId).setWorkoutState(workoutState).mo10084build();
        }
        throw new IOException("Cannot marshal a null FitnessSession");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FitnessSession unmarshal(Fitness.SyncFitnessSession syncFitnessSession) throws IOException {
        FitnessSession.WorkoutState workoutState;
        if (syncFitnessSession != null) {
            int sequenceId = syncFitnessSession.getSequenceId();
            UUID byteStringToUuid = IOUtils.byteStringToUuid(syncFitnessSession.getSessionId());
            int ordinal = syncFitnessSession.getWorkoutState().ordinal();
            if (ordinal == 1) {
                workoutState = FitnessSession.WorkoutState.IDLE;
            } else if (ordinal == 2) {
                workoutState = FitnessSession.WorkoutState.ACTIVE;
            } else if (ordinal != 3) {
                workoutState = FitnessSession.WorkoutState.UNKNOWN;
            } else {
                workoutState = FitnessSession.WorkoutState.PAUSED;
            }
            return new FitnessSession(byteStringToUuid, sequenceId, workoutState);
        }
        throw new IOException("Cannot unmarshal a null SyncFitnessSession");
    }
}
