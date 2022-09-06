package com.amazon.whisperjoin.provisioning.wifi;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class WifiConnectionFailure {
    public final String failureMessage;
    public final Reason reason;

    /* loaded from: classes13.dex */
    public enum Reason {
        AUTHENTICATION_FAILURE,
        UNKNOWN_FAILURE
    }

    public WifiConnectionFailure(Reason reason, String str) {
        this.reason = reason;
        this.failureMessage = str;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof WifiConnectionFailure;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WifiConnectionFailure)) {
            return false;
        }
        WifiConnectionFailure wifiConnectionFailure = (WifiConnectionFailure) obj;
        if (!wifiConnectionFailure.canEqual(this)) {
            return false;
        }
        Reason reason = getReason();
        Reason reason2 = wifiConnectionFailure.getReason();
        if (reason != null ? !reason.equals(reason2) : reason2 != null) {
            return false;
        }
        String failureMessage = getFailureMessage();
        String failureMessage2 = wifiConnectionFailure.getFailureMessage();
        return failureMessage != null ? failureMessage.equals(failureMessage2) : failureMessage2 == null;
    }

    public String getFailureMessage() {
        return this.failureMessage;
    }

    public Reason getReason() {
        return this.reason;
    }

    public int hashCode() {
        Reason reason = getReason();
        int i = 43;
        int hashCode = reason == null ? 43 : reason.hashCode();
        String failureMessage = getFailureMessage();
        int i2 = (hashCode + 59) * 59;
        if (failureMessage != null) {
            i = failureMessage.hashCode();
        }
        return i2 + i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WifiConnectionFailure(reason=");
        outline107.append(getReason());
        outline107.append(", failureMessage=");
        outline107.append(getFailureMessage());
        outline107.append(")");
        return outline107.toString();
    }
}
