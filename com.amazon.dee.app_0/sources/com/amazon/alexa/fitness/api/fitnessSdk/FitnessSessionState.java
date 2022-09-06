package com.amazon.alexa.fitness.api.fitnessSdk;

import com.amazon.alexa.presence.bleconn.service.PresenceBleService;
import com.amazon.devicesetup.common.v1.WifiConnectionState;
import kotlin.Metadata;
/* compiled from: FitnessSessionState.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\b\u000b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\u0006\u001a\u00020\u0004J\u0006\u0010\u0007\u001a\u00020\u0004J\u0006\u0010\b\u001a\u00020\u0004J%\u0010\t\u001a\u00020\u00042\u0016\u0010\n\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00000\u000b\"\u0004\u0018\u00010\u0000H\u0002¢\u0006\u0002\u0010\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015¨\u0006\u0016"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;", "", "(Ljava/lang/String;I)V", "isSessionActive", "", "isSessionInProgress", "isSessionInactive", "isSessionInitializing", "isSessionPaused", "isSessionStateOneOf", "fitnessSessionStates", "", "([Lcom/amazon/alexa/fitness/api/fitnessSdk/FitnessSessionState;)Z", WifiConnectionState.IDLE, "RECOVERING", PresenceBleService.ServiceState.STARTING, "FAILED_TO_START", "ACTIVE", "PAUSED", "RESUMING", "FAILED_TO_RESUME", PresenceBleService.ServiceState.STOPPING, "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public enum FitnessSessionState {
    IDLE,
    RECOVERING,
    STARTING,
    FAILED_TO_START,
    ACTIVE,
    PAUSED,
    RESUMING,
    FAILED_TO_RESUME,
    STOPPING;

    private final boolean isSessionStateOneOf(FitnessSessionState... fitnessSessionStateArr) {
        for (FitnessSessionState fitnessSessionState : fitnessSessionStateArr) {
            if (fitnessSessionState == this) {
                return true;
            }
        }
        return false;
    }

    public final boolean isSessionActive() {
        return isSessionStateOneOf(ACTIVE, STARTING, STOPPING);
    }

    public final boolean isSessionInProgress() {
        return !isSessionInactive();
    }

    public final boolean isSessionInactive() {
        return isSessionStateOneOf(IDLE, FAILED_TO_START);
    }

    public final boolean isSessionInitializing() {
        return isSessionStateOneOf(STARTING, RECOVERING);
    }

    public final boolean isSessionPaused() {
        return isSessionStateOneOf(PAUSED, RESUMING, FAILED_TO_RESUME);
    }
}
