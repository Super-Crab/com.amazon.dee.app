package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class FFSWhiteListPolicyResponse {
    private int ffsScanningEnabled;
    private int hoursToNextCall;
    private int minBatteryLevel;

    public FFSWhiteListPolicyResponse(int i, int i2, int i3) {
        this.minBatteryLevel = i;
        this.hoursToNextCall = i2;
        this.ffsScanningEnabled = i3;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || FFSWhiteListPolicyResponse.class != obj.getClass()) {
            return false;
        }
        FFSWhiteListPolicyResponse fFSWhiteListPolicyResponse = (FFSWhiteListPolicyResponse) obj;
        return this.minBatteryLevel == fFSWhiteListPolicyResponse.minBatteryLevel && this.hoursToNextCall == fFSWhiteListPolicyResponse.hoursToNextCall && this.ffsScanningEnabled == fFSWhiteListPolicyResponse.ffsScanningEnabled;
    }

    public int getFfsScanningEnabled() {
        return this.ffsScanningEnabled;
    }

    public int getHoursToNextCall() {
        return this.hoursToNextCall;
    }

    public int getMinBatteryLevel() {
        return this.minBatteryLevel;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.minBatteryLevel), Integer.valueOf(this.hoursToNextCall), Integer.valueOf(this.ffsScanningEnabled));
    }

    public void setFfsScanningEnabled(int i) {
        this.ffsScanningEnabled = i;
    }

    public void setHoursToNextCall(int i) {
        this.hoursToNextCall = i;
    }

    public void setMinBatteryLevel(int i) {
        this.minBatteryLevel = i;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("minBatteryLevel", this.minBatteryLevel).add("hoursToNextCall", this.hoursToNextCall).add("ffsScanningEnabled", this.ffsScanningEnabled).toString();
    }

    public FFSWhiteListPolicyResponse() {
    }
}
