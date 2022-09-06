package com.amazon.whisperjoin.deviceprovisioningservice.arcus.data;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes13.dex */
public class ZeroTouchProvisioningSettings {
    private final boolean mEnablePhilpsBLEWorkflow;

    public ZeroTouchProvisioningSettings(boolean z) {
        this.mEnablePhilpsBLEWorkflow = z;
    }

    public static ZeroTouchProvisioningSettings fromJSONObject(JSONObject jSONObject) {
        return new ZeroTouchProvisioningSettings(jSONObject.optBoolean("enablePhilipsBleWorkflow", false));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ZeroTouchProvisioningSettings) && this.mEnablePhilpsBLEWorkflow == ((ZeroTouchProvisioningSettings) obj).mEnablePhilpsBLEWorkflow;
    }

    public int hashCode() {
        return Objects.hashCode(Boolean.valueOf(this.mEnablePhilpsBLEWorkflow));
    }

    public boolean isPhilipsBLEWorkflowEnabled() {
        return this.mEnablePhilpsBLEWorkflow;
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("enablePhilipsBleWorkflow", this.mEnablePhilpsBLEWorkflow);
        return jSONObject;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mEnablePhilpsBLEWorkflow", this.mEnablePhilpsBLEWorkflow).toString();
    }

    public ZeroTouchProvisioningSettings() {
        this.mEnablePhilpsBLEWorkflow = false;
    }
}
