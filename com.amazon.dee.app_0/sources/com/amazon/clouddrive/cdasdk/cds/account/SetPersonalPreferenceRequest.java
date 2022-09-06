package com.amazon.clouddrive.cdasdk.cds.account;

import com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest;
import com.amazon.clouddrive.cdasdk.cds.common.PreferenceKey;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class SetPersonalPreferenceRequest extends CloudDriveRequest {
    @JsonProperty("preferenceKey")
    private PreferenceKey preferenceKey;
    @JsonProperty("preferenceValue")
    private String preferenceValue;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof SetPersonalPreferenceRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SetPersonalPreferenceRequest)) {
            return false;
        }
        SetPersonalPreferenceRequest setPersonalPreferenceRequest = (SetPersonalPreferenceRequest) obj;
        if (!setPersonalPreferenceRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        PreferenceKey preferenceKey = getPreferenceKey();
        PreferenceKey preferenceKey2 = setPersonalPreferenceRequest.getPreferenceKey();
        if (preferenceKey != null ? !preferenceKey.equals(preferenceKey2) : preferenceKey2 != null) {
            return false;
        }
        String preferenceValue = getPreferenceValue();
        String preferenceValue2 = setPersonalPreferenceRequest.getPreferenceValue();
        return preferenceValue != null ? preferenceValue.equals(preferenceValue2) : preferenceValue2 == null;
    }

    public PreferenceKey getPreferenceKey() {
        return this.preferenceKey;
    }

    public String getPreferenceValue() {
        return this.preferenceValue;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        PreferenceKey preferenceKey = getPreferenceKey();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (preferenceKey == null ? 43 : preferenceKey.hashCode());
        String preferenceValue = getPreferenceValue();
        int i2 = hashCode2 * 59;
        if (preferenceValue != null) {
            i = preferenceValue.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("preferenceKey")
    public void setPreferenceKey(PreferenceKey preferenceKey) {
        this.preferenceKey = preferenceKey;
    }

    @JsonProperty("preferenceValue")
    public void setPreferenceValue(String str) {
        this.preferenceValue = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SetPersonalPreferenceRequest(preferenceKey=");
        outline107.append(getPreferenceKey());
        outline107.append(", preferenceValue=");
        outline107.append(getPreferenceValue());
        outline107.append(")");
        return outline107.toString();
    }
}
