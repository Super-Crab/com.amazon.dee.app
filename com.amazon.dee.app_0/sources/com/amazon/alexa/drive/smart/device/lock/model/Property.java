package com.amazon.alexa.drive.smart.device.lock.model;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes7.dex */
public class Property {
    @SerializedName("lockState")
    private String lockState;
    @SerializedName("reachabilityStatusValue")
    private String reachabilityStatusValue;

    public String getLockState() {
        return this.lockState;
    }

    public String getReachabilityStatusValue() {
        return this.reachabilityStatusValue;
    }

    public void setLockState(String str) {
        this.lockState = str;
    }

    public void setReachabilityStatusValue(String str) {
        this.reachabilityStatusValue = str;
    }
}
