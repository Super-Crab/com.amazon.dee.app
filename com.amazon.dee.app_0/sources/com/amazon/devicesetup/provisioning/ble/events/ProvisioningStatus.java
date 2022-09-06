package com.amazon.devicesetup.provisioning.ble.events;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class ProvisioningStatus {
    private final boolean mInsecureModeSupported;
    private State mState;

    /* loaded from: classes12.dex */
    public enum State {
        PROVISIONING_SESSION_IDLE(0, "Provisioning Session Idle"),
        WAITING_FOR_PROVISIONER(1, "Wait for Provisioner"),
        AUTHORIZING_PROVISIONER(2, "Authorizing Provisioner"),
        ACTIVELY_PROVISIONING(3, "Actively Provisioning"),
        PROVISIONING_COMPLETE(4, "Provisioning Complete"),
        PROVISIONING_TERMINATED(5, "Provisioning Terminated"),
        CONNECTED_TO_PROVISIONER(6, "Connected to Provisioner"),
        ADVERTISEMENT_TIMEOUT(7, "Advertisement Timeout"),
        PROVISIONING_TIMEOUT(8, "Provisioning Timeout");
        
        private final String mString;
        private final int mValue;

        State(int i, String str) {
            this.mValue = i;
            this.mString = str;
        }

        public static State fromInt(int i) {
            switch (i) {
                case 0:
                    return PROVISIONING_SESSION_IDLE;
                case 1:
                    return WAITING_FOR_PROVISIONER;
                case 2:
                    return AUTHORIZING_PROVISIONER;
                case 3:
                    return ACTIVELY_PROVISIONING;
                case 4:
                    return PROVISIONING_COMPLETE;
                case 5:
                    return PROVISIONING_TERMINATED;
                case 6:
                    return CONNECTED_TO_PROVISIONER;
                case 7:
                    return ADVERTISEMENT_TIMEOUT;
                case 8:
                    return PROVISIONING_TIMEOUT;
                default:
                    throw new IllegalArgumentException("Unknown State encountered.");
            }
        }

        public String getString() {
            return this.mString;
        }

        public int getValue() {
            return this.mValue;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mString;
        }
    }

    public ProvisioningStatus(State state, boolean z) {
        this.mState = state;
        this.mInsecureModeSupported = z;
    }

    public State getProvisioningState() {
        return this.mState;
    }

    public boolean insecureModeSupported() {
        return this.mInsecureModeSupported;
    }

    public void setProvisioningState(State state) {
        this.mState = state;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ProvisioningStatus [state=");
        outline107.append(this.mState);
        outline107.append(", insecure-mode-supported=");
        return GeneratedOutlineSupport1.outline97(outline107, this.mInsecureModeSupported, "]");
    }
}
