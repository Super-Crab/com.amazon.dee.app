package com.amazon.alexa.location.phase3.sensor.alexageofence;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.location.phase3.evaluator.GeofenceTriggerEvent;
/* loaded from: classes9.dex */
public final class AlexaGeofenceTriggerEvent extends GeofenceTriggerEvent {
    private static final long DWELL_THRESHOLD_IN_MS = 7200000;
    static final String FENCE_ID_INVALID = "INVALID_GEOFENCE";
    private static final String TYPE = "ESTIMATED_EVENT_ALEXA_GEOFENCE";

    public AlexaGeofenceTriggerEvent(long j, int i, @NonNull String str) {
        super(TYPE, j, i, str);
    }

    @Nullable
    public static AlexaGeofenceTriggerEvent fromStates(@NonNull AlexaGeofenceState alexaGeofenceState, @NonNull AlexaGeofenceState alexaGeofenceState2) {
        if (AlexaGeofenceState.STATE_UNDETERMINED.equals(alexaGeofenceState.state) || AlexaGeofenceState.STATE_UNDETERMINED.equals(alexaGeofenceState2.state)) {
            return null;
        }
        if (alexaGeofenceState.timestamp <= alexaGeofenceState2.timestamp) {
            alexaGeofenceState2 = alexaGeofenceState;
            alexaGeofenceState = alexaGeofenceState2;
        }
        long j = alexaGeofenceState.timestamp;
        String str = alexaGeofenceState2.geofenceId;
        if (!str.equals(alexaGeofenceState.geofenceId)) {
            return null;
        }
        int i = -1;
        if (!alexaGeofenceState2.state.equals(alexaGeofenceState.state)) {
            i = !AlexaGeofenceState.STATE_IN_FENCE.equals(alexaGeofenceState.state) ? 1 : 0;
        } else if (AlexaGeofenceState.STATE_IN_FENCE.equals(alexaGeofenceState.state) && j - alexaGeofenceState2.timestamp >= DWELL_THRESHOLD_IN_MS) {
            i = 2;
        }
        return new AlexaGeofenceTriggerEvent(j, i, str);
    }
}
