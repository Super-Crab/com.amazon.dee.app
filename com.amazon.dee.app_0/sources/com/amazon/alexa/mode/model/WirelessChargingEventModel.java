package com.amazon.alexa.mode.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes9.dex */
public class WirelessChargingEventModel {
    @SerializedName("isChargingError")
    @Expose
    private boolean isChargingError;

    public WirelessChargingEventModel(boolean z) {
        this.isChargingError = z;
    }

    public boolean getIsChargingError() {
        return this.isChargingError;
    }

    public void setIsChargingError(boolean z) {
        this.isChargingError = z;
    }
}
