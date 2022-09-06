package com.amazon.whisperjoin.deviceprovisioningservice.arcus.data;

import com.amazon.devicesetupservice.reporting.Origin;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes13.dex */
public class FFRSettings {
    private final FFRProvisionerSettings provisionerSettings;

    public FFRSettings(FFRProvisionerSettings fFRProvisionerSettings) {
        this.provisionerSettings = fFRProvisionerSettings;
    }

    public static FFRSettings fromJSONObject(JSONObject jSONObject) {
        return new FFRSettings(jSONObject);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && FFRSettings.class == obj.getClass()) {
            return new EqualsBuilder().append(this.provisionerSettings, ((FFRSettings) obj).provisionerSettings).isEquals();
        }
        return false;
    }

    public FFRProvisionerSettings getProvisionerSettings() {
        return this.provisionerSettings;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.provisionerSettings).toHashCode();
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(Origin.PROVISIONER, this.provisionerSettings.toJSONObject());
        return jSONObject;
    }

    public String toString() {
        return new ToStringBuilder(this).append("provisionerSettings", this.provisionerSettings).toString();
    }

    public FFRSettings() {
        this.provisionerSettings = new FFRProvisionerSettings();
    }

    public FFRSettings(JSONObject jSONObject) {
        this.provisionerSettings = FFRProvisionerSettings.fromJSONObject(jSONObject.has(Origin.PROVISIONER) ? jSONObject.optJSONObject(Origin.PROVISIONER) : new JSONObject());
    }
}
