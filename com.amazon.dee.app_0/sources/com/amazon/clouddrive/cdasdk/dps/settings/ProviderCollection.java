package com.amazon.clouddrive.cdasdk.dps.settings;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class ProviderCollection {
    @JsonProperty("collectionIds")
    private List<String> collectionIds;
    @JsonProperty("providerId")
    private String providerId;

    protected boolean canEqual(Object obj) {
        return obj instanceof ProviderCollection;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ProviderCollection)) {
            return false;
        }
        ProviderCollection providerCollection = (ProviderCollection) obj;
        if (!providerCollection.canEqual(this)) {
            return false;
        }
        String providerId = getProviderId();
        String providerId2 = providerCollection.getProviderId();
        if (providerId != null ? !providerId.equals(providerId2) : providerId2 != null) {
            return false;
        }
        List<String> collectionIds = getCollectionIds();
        List<String> collectionIds2 = providerCollection.getCollectionIds();
        return collectionIds != null ? collectionIds.equals(collectionIds2) : collectionIds2 == null;
    }

    public List<String> getCollectionIds() {
        return this.collectionIds;
    }

    public String getProviderId() {
        return this.providerId;
    }

    public int hashCode() {
        String providerId = getProviderId();
        int i = 43;
        int hashCode = providerId == null ? 43 : providerId.hashCode();
        List<String> collectionIds = getCollectionIds();
        int i2 = (hashCode + 59) * 59;
        if (collectionIds != null) {
            i = collectionIds.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("collectionIds")
    public void setCollectionIds(List<String> list) {
        this.collectionIds = list;
    }

    @JsonProperty("providerId")
    public void setProviderId(String str) {
        this.providerId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ProviderCollection(providerId=");
        outline107.append(getProviderId());
        outline107.append(", collectionIds=");
        outline107.append(getCollectionIds());
        outline107.append(")");
        return outline107.toString();
    }
}
