package com.amazon.clouddrive.cdasdk.cds.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class AssetProperties {
    @JsonProperty("assetType")
    private String assetType;

    protected boolean canEqual(Object obj) {
        return obj instanceof AssetProperties;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AssetProperties)) {
            return false;
        }
        AssetProperties assetProperties = (AssetProperties) obj;
        if (!assetProperties.canEqual(this)) {
            return false;
        }
        String assetType = getAssetType();
        String assetType2 = assetProperties.getAssetType();
        return assetType != null ? assetType.equals(assetType2) : assetType2 == null;
    }

    public String getAssetType() {
        return this.assetType;
    }

    public int hashCode() {
        String assetType = getAssetType();
        return 59 + (assetType == null ? 43 : assetType.hashCode());
    }

    @JsonProperty("assetType")
    public void setAssetType(String str) {
        this.assetType = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AssetProperties(assetType=");
        outline107.append(getAssetType());
        outline107.append(")");
        return outline107.toString();
    }
}
