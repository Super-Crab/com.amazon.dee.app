package com.amazon.whisperjoin.deviceprovisioningservice.arcus.data;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes13.dex */
public class ArcusSettings {
    private final String syncPeriod;

    public ArcusSettings(String str) {
        this.syncPeriod = str;
    }

    public static ArcusSettings fromJSONObject(JSONObject jSONObject) {
        return new ArcusSettings(jSONObject);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && ArcusSettings.class == obj.getClass()) {
            return new EqualsBuilder().append(this.syncPeriod, ((ArcusSettings) obj).syncPeriod).isEquals();
        }
        return false;
    }

    public String getSyncPeriod() {
        return this.syncPeriod;
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(this.syncPeriod).toHashCode();
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("arcusSyncPeriod", this.syncPeriod);
        return jSONObject;
    }

    public String toString() {
        return new ToStringBuilder(this).append("syncPeriod", this.syncPeriod).toString();
    }

    public ArcusSettings() {
        this.syncPeriod = "P1D";
    }

    public ArcusSettings(JSONObject jSONObject) {
        this.syncPeriod = jSONObject.optString("arcusSyncPeriod", "P1D");
    }
}
