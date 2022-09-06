package com.amazon.alexa.fitness.model.event;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessSessionEvent.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lcom/amazon/alexa/fitness/model/event/EndedReason;", "", "value", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "USER_REQUESTED", "DEVICE_COMPLETED", "GOAL_BASED", "DEVICE_DISCONNECTED", "DEVICE_UNREACHABLE", "NO_FITNESS_DATA", "STALE_RECOVERY", "RECOVERY_FAILED", "UNKNOWN", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public enum EndedReason {
    USER_REQUESTED("UserRequestedCompletion"),
    DEVICE_COMPLETED("DeviceCompletedCompletion"),
    GOAL_BASED("GoalBasedCompletion"),
    DEVICE_DISCONNECTED("DeviceDisconnectedCompletion"),
    DEVICE_UNREACHABLE("DeviceUnreachableCompletion"),
    NO_FITNESS_DATA("NoFitnessDataCompletion"),
    STALE_RECOVERY("StaleRecoveryCompletion"),
    RECOVERY_FAILED("RecoveryFailedCompletion"),
    UNKNOWN("Unknown");
    
    @NotNull
    private final String value;

    EndedReason(String str) {
        this.value = str;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }
}
