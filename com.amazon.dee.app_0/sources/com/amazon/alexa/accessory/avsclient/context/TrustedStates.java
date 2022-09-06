package com.amazon.alexa.accessory.avsclient.context;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.JsonUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class TrustedStates implements JsonObjectSerializable {
    private static final String LAST_TIME_IN_UNLOCKED_STATE = "lastTimeInUnlockedState";
    private static final String SESSION_STATES = "sessionStates";
    private static final String UNLOCK_METHOD = "unlockMethod";
    private static final String UNLOCK_STATE = "unlockState";
    String lastTimeInUnlockedState;
    final List<SessionState> sessionStates;
    String unlockMethod;
    final String unlockState;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class SessionState implements JsonObjectSerializable {
        private static final String DEVICE_SERIAL_NUMBER = "deviceSerialNumber";
        private static final String DEVICE_TYPE = "deviceType";
        private static final String LONGEST_TIME_UNTRUSTED = "longestTimeUntrustedInMilliseconds";
        private static final String SESSION_START_TIME = "sessionStartTime";
        private static final String TRUST_SESSION_START_TIME = "trustSessionStartTime";
        final String deviceSerialNumber;
        final String deviceType;
        final long longestTimeUntrustedInMilliseconds;
        final String sessionStartTime;
        final String trustSessionStartTime;

        public SessionState(String str, String str2, long j, String str3, String str4) {
            Preconditions.notNull(str, SESSION_START_TIME);
            Preconditions.notNull(str3, "deviceType");
            Preconditions.notNull(str4, "deviceSerialNumber");
            this.sessionStartTime = str;
            this.trustSessionStartTime = str2;
            this.longestTimeUntrustedInMilliseconds = j;
            this.deviceType = str3;
            this.deviceSerialNumber = str4;
        }

        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
        public JSONObject toJsonObject() throws JSONException {
            return new JSONObject().put(SESSION_START_TIME, this.sessionStartTime).put(TRUST_SESSION_START_TIME, this.trustSessionStartTime).put(LONGEST_TIME_UNTRUSTED, this.longestTimeUntrustedInMilliseconds).put("deviceType", this.deviceType).put("deviceSerialNumber", this.deviceSerialNumber);
        }
    }

    public TrustedStates(String str, String str2, String str3, List<SessionState> list) {
        Preconditions.notNull(str, UNLOCK_STATE);
        Preconditions.notNull(list, SESSION_STATES);
        this.unlockState = str;
        this.lastTimeInUnlockedState = str2;
        this.unlockMethod = str3;
        this.sessionStates = Collections.unmodifiableList(list);
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put(UNLOCK_STATE, this.unlockState).put(LAST_TIME_IN_UNLOCKED_STATE, this.lastTimeInUnlockedState).put(UNLOCK_METHOD, this.unlockMethod).put(SESSION_STATES, JsonUtils.toJsonArray(this.sessionStates));
    }

    public String toString() {
        try {
            return toJsonObject().toString(4);
        } catch (JSONException unused) {
            return super.toString();
        }
    }
}
