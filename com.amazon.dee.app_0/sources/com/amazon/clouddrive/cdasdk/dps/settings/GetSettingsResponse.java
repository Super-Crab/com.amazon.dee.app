package com.amazon.clouddrive.cdasdk.dps.settings;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class GetSettingsResponse {
    @JsonProperty("providerCollections")
    private List<ProviderCollection> providerCollections;

    protected boolean canEqual(Object obj) {
        return obj instanceof GetSettingsResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetSettingsResponse)) {
            return false;
        }
        GetSettingsResponse getSettingsResponse = (GetSettingsResponse) obj;
        if (!getSettingsResponse.canEqual(this)) {
            return false;
        }
        List<ProviderCollection> providerCollections = getProviderCollections();
        List<ProviderCollection> providerCollections2 = getSettingsResponse.getProviderCollections();
        return providerCollections != null ? providerCollections.equals(providerCollections2) : providerCollections2 == null;
    }

    public List<ProviderCollection> getProviderCollections() {
        return this.providerCollections;
    }

    public int hashCode() {
        List<ProviderCollection> providerCollections = getProviderCollections();
        return 59 + (providerCollections == null ? 43 : providerCollections.hashCode());
    }

    @JsonProperty("providerCollections")
    public void setProviderCollections(List<ProviderCollection> list) {
        this.providerCollections = list;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetSettingsResponse(providerCollections=");
        outline107.append(getProviderCollections());
        outline107.append(")");
        return outline107.toString();
    }
}
