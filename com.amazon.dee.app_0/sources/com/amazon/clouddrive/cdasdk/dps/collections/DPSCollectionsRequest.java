package com.amazon.clouddrive.cdasdk.dps.collections;

import com.amazon.clouddrive.cdasdk.dps.common.DPSRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class DPSCollectionsRequest extends DPSRequest {
    @JsonProperty("locale")
    private String locale;

    @Override // com.amazon.clouddrive.cdasdk.dps.common.DPSRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof DPSCollectionsRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.common.DPSRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DPSCollectionsRequest)) {
            return false;
        }
        DPSCollectionsRequest dPSCollectionsRequest = (DPSCollectionsRequest) obj;
        if (!dPSCollectionsRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String locale = getLocale();
        String locale2 = dPSCollectionsRequest.getLocale();
        return locale != null ? locale.equals(locale2) : locale2 == null;
    }

    public String getLocale() {
        return this.locale;
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.common.DPSRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        String locale = getLocale();
        return (hashCode * 59) + (locale == null ? 43 : locale.hashCode());
    }

    @JsonProperty("locale")
    public void setLocale(String str) {
        this.locale = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.dps.common.DPSRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DPSCollectionsRequest(locale=");
        outline107.append(getLocale());
        outline107.append(")");
        return outline107.toString();
    }
}
