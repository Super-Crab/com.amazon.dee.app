package com.amazon.alexa.accessory.repositories.nonhfpcalling;

import java.util.Objects;
import java.util.UUID;
/* loaded from: classes6.dex */
public final class CallInfo {
    private final CallDirection callDirection;
    private final CallState callState;
    private final long connectionTimestampInMs;
    private final String displayName;
    private final long lastUpdateTimestampInMs;
    private final String phoneNumber;
    private final UUID uuid;

    /* loaded from: classes6.dex */
    public enum CallDirection {
        UNKNOWN,
        INCOMING,
        OUTGOING
    }

    /* loaded from: classes6.dex */
    public enum CallState {
        IDLE,
        DIALING,
        RINGING,
        ACTIVE,
        HOLDING,
        DISCONNECTING,
        DISCONNECTED
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CallInfo.class != obj.getClass()) {
            return false;
        }
        CallInfo callInfo = (CallInfo) obj;
        return this.connectionTimestampInMs == callInfo.connectionTimestampInMs && this.lastUpdateTimestampInMs == callInfo.lastUpdateTimestampInMs && this.uuid.equals(callInfo.uuid) && this.phoneNumber.equals(callInfo.phoneNumber) && this.displayName.equals(callInfo.displayName) && this.callDirection == callInfo.callDirection && this.callState == callInfo.callState;
    }

    public CallDirection getCallDirection() {
        return this.callDirection;
    }

    public CallState getCallState() {
        return this.callState;
    }

    public long getConnectionTimestampInMs() {
        return this.connectionTimestampInMs;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public long getLastUpdateTimestampInMs() {
        return this.lastUpdateTimestampInMs;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public int hashCode() {
        return Objects.hash(this.uuid, this.phoneNumber, this.displayName, this.callDirection, this.callState, Long.valueOf(this.connectionTimestampInMs), Long.valueOf(this.lastUpdateTimestampInMs));
    }

    public boolean isSameCall(CallInfo callInfo) {
        return callInfo != null && this.uuid.equals(callInfo.getUuid());
    }

    private CallInfo(Builder builder) {
        this.uuid = builder.uuid;
        this.phoneNumber = builder.phoneNumber;
        this.displayName = builder.displayName;
        this.callDirection = builder.callDirection;
        this.callState = builder.callState;
        this.connectionTimestampInMs = builder.connectionTimestampInMs;
        this.lastUpdateTimestampInMs = builder.lastUpdateTimestampInMs;
    }

    /* loaded from: classes6.dex */
    public static class Builder {
        private CallDirection callDirection;
        private CallState callState;
        private long connectionTimestampInMs;
        private String displayName;
        private long lastUpdateTimestampInMs;
        private String phoneNumber;
        private UUID uuid;

        public Builder() {
            this.connectionTimestampInMs = -1L;
            this.lastUpdateTimestampInMs = -1L;
        }

        public CallInfo build() {
            if (this.uuid == null) {
                this.uuid = UUID.randomUUID();
            }
            if (this.phoneNumber == null) {
                this.phoneNumber = "";
            }
            if (this.displayName == null) {
                this.displayName = "";
            }
            if (this.callDirection == null) {
                this.callDirection = CallDirection.UNKNOWN;
            }
            if (this.callState == null) {
                this.callState = CallState.IDLE;
            }
            if (this.lastUpdateTimestampInMs < 0) {
                this.lastUpdateTimestampInMs = System.currentTimeMillis();
            }
            return new CallInfo(this);
        }

        public Builder setCallDirection(CallDirection callDirection) {
            this.callDirection = callDirection;
            return this;
        }

        public Builder setCallState(CallState callState) {
            this.callState = callState;
            return this;
        }

        public Builder setConnectionTimestampInMs(long j) {
            this.connectionTimestampInMs = j;
            return this;
        }

        public Builder setDisplayName(String str) {
            this.displayName = str;
            return this;
        }

        public Builder setLastUpdateTimeStampInMs(long j) {
            this.lastUpdateTimestampInMs = j;
            return this;
        }

        public Builder setPhoneNumber(String str) {
            this.phoneNumber = str;
            return this;
        }

        public Builder setUuid(UUID uuid) {
            this.uuid = uuid;
            return this;
        }

        public Builder(CallInfo callInfo) {
            this.uuid = callInfo.uuid;
            this.phoneNumber = callInfo.phoneNumber;
            this.displayName = callInfo.displayName;
            this.callDirection = callInfo.callDirection;
            this.callState = callInfo.callState;
            this.connectionTimestampInMs = callInfo.connectionTimestampInMs;
            this.lastUpdateTimestampInMs = -1L;
        }
    }
}
