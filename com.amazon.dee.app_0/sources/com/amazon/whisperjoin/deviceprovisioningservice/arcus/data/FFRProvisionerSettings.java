package com.amazon.whisperjoin.deviceprovisioningservice.arcus.data;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes13.dex */
public class FFRProvisionerSettings {
    private final boolean enabled;

    public FFRProvisionerSettings(boolean z) {
        this.enabled = z;
    }

    public static FFRProvisionerSettings fromJSONObject(JSONObject jSONObject) {
        return new FFRProvisionerSettings(jSONObject);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && FFRProvisionerSettings.class == obj.getClass()) {
            return new EqualsBuilder().append(this.enabled, ((FFRProvisionerSettings) obj).enabled).isEquals();
        }
        return false;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.enabled).toHashCode();
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("ffrEnabled", this.enabled);
        return jSONObject;
    }

    public String toString() {
        return new ToStringBuilder(this).append("enabled", this.enabled).toString();
    }

    public FFRProvisionerSettings() {
        this.enabled = true;
    }

    public FFRProvisionerSettings(JSONObject jSONObject) {
        this.enabled = jSONObject.optBoolean("ffrEnabled", true);
    }
}
