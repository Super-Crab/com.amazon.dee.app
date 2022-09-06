package com.amazon.alexa.fitness.sdk.stateMachine;

import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionState;
import com.amazon.alexa.fitness.api.fitnessSdk.FitnessSessionTransition;
import com.amazon.alexa.fitness.sdk.stateMachine.StateMachinePlan;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessPlan.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¨\u0006\u0004"}, d2 = {"fitnessPlan", "Lcom/amazon/alexa/fitness/sdk/stateMachine/StateMachinePlan;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionTransition;", "AlexaMobileAndroidFitnessExtension_release"}, k = 2, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessPlanKt {
    @NotNull
    public static final StateMachinePlan<FitnessSessionState, FitnessSessionTransition> fitnessPlan() {
        StateMachinePlan.Builder builder = new StateMachinePlan.Builder(FitnessSessionState.IDLE);
        builder.add(FitnessSessionState.IDLE, FitnessSessionTransition.RECOVERY_COMMAND_RECEIVED, FitnessSessionState.RECOVERING);
        builder.add(FitnessSessionState.RECOVERING, FitnessSessionTransition.RECOVERED_NOTHING_TO_RECOVER, FitnessSessionState.IDLE);
        builder.add(FitnessSessionState.RECOVERING, FitnessSessionTransition.RECOVERED_WAS_ACTIVE_SUCCEEDED, FitnessSessionState.ACTIVE);
        builder.add(FitnessSessionState.RECOVERING, FitnessSessionTransition.RECOVERED_WAS_ACTIVE_FAILED, FitnessSessionState.STOPPING);
        builder.add(FitnessSessionState.RECOVERING, FitnessSessionTransition.RECOVERED_WAS_PAUSED, FitnessSessionState.PAUSED);
        builder.add(FitnessSessionState.RECOVERING, FitnessSessionTransition.RECOVERING_FAILED_NO_DATA, FitnessSessionState.IDLE);
        builder.add(FitnessSessionState.RECOVERING, FitnessSessionTransition.RECOVERING_FAILED_STALE_DATA, FitnessSessionState.STOPPING);
        builder.add(FitnessSessionState.RECOVERING, FitnessSessionTransition.RECOVERING_FAILED, FitnessSessionState.STOPPING);
        builder.add(FitnessSessionState.IDLE, FitnessSessionTransition.START_COMMAND_RECEIVED, FitnessSessionState.STARTING);
        builder.add(FitnessSessionState.STARTING, FitnessSessionTransition.STARTED, FitnessSessionState.ACTIVE);
        builder.add(FitnessSessionState.STARTING, FitnessSessionTransition.STARTING_FAILED, FitnessSessionState.FAILED_TO_START);
        builder.add(FitnessSessionState.FAILED_TO_START, FitnessSessionTransition.START_COMMAND_RECEIVED, FitnessSessionState.STARTING);
        builder.add(FitnessSessionState.FAILED_TO_START, FitnessSessionTransition.STOP_COMMAND_RECEIVED, FitnessSessionState.IDLE);
        builder.add(FitnessSessionState.ACTIVE, FitnessSessionTransition.PAUSE_COMMAND_RECEIVED, FitnessSessionState.PAUSED);
        builder.add(FitnessSessionState.ACTIVE, FitnessSessionTransition.AUTOSTOP_NO_DATA_TIMEOUT, FitnessSessionState.STOPPING);
        builder.add(FitnessSessionState.ACTIVE, FitnessSessionTransition.AUTOSTOP_SENSOR_DISCONNECTED, FitnessSessionState.STOPPING);
        builder.add(FitnessSessionState.PAUSED, FitnessSessionTransition.AUTOSTOP_NO_DATA_TIMEOUT, FitnessSessionState.STOPPING);
        builder.add(FitnessSessionState.PAUSED, FitnessSessionTransition.AUTOSTOP_SENSOR_DISCONNECTED, FitnessSessionState.STOPPING);
        builder.add(FitnessSessionState.PAUSED, FitnessSessionTransition.RESUME_COMMAND_RECEIVED, FitnessSessionState.RESUMING);
        builder.add(FitnessSessionState.RESUMING, FitnessSessionTransition.RESUMED, FitnessSessionState.ACTIVE);
        builder.add(FitnessSessionState.RESUMING, FitnessSessionTransition.RESUMING_FAILED, FitnessSessionState.FAILED_TO_RESUME);
        builder.add(FitnessSessionState.ACTIVE, FitnessSessionTransition.STOP_COMMAND_RECEIVED, FitnessSessionState.STOPPING);
        builder.add(FitnessSessionState.PAUSED, FitnessSessionTransition.STOP_COMMAND_RECEIVED, FitnessSessionState.STOPPING);
        builder.add(FitnessSessionState.RESUMING, FitnessSessionTransition.STOP_COMMAND_RECEIVED, FitnessSessionState.STOPPING);
        builder.add(FitnessSessionState.FAILED_TO_RESUME, FitnessSessionTransition.AUTOSTOP_SENSOR_DISCONNECTED, FitnessSessionState.STOPPING);
        builder.add(FitnessSessionState.FAILED_TO_RESUME, FitnessSessionTransition.STOP_COMMAND_RECEIVED, FitnessSessionState.STOPPING);
        builder.add(FitnessSessionState.FAILED_TO_RESUME, FitnessSessionTransition.RESUME_COMMAND_RECEIVED, FitnessSessionState.RESUMING);
        builder.add(FitnessSessionState.STOPPING, FitnessSessionTransition.STOPPED, FitnessSessionState.IDLE);
        return builder.build();
    }
}
