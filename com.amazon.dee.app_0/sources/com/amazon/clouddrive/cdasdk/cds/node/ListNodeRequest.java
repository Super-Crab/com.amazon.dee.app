package com.amazon.clouddrive.cdasdk.cds.node;

import com.amazon.clouddrive.cdasdk.cds.common.AssetMapping;
import com.amazon.clouddrive.cdasdk.cds.common.DedupeContext;
import com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class ListNodeRequest extends PaginatedCloudDriveRequest {
    @JsonProperty("assetMapping")
    private AssetMapping assetMapping;
    @JsonProperty("dedupeContext")
    private DedupeContext dedupeContext;
    @JsonProperty("showAddress")
    private String showAddress;
    @JsonProperty("tempLink")
    private String tempLink;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof ListNodeRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListNodeRequest)) {
            return false;
        }
        ListNodeRequest listNodeRequest = (ListNodeRequest) obj;
        if (!listNodeRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        AssetMapping assetMapping = getAssetMapping();
        AssetMapping assetMapping2 = listNodeRequest.getAssetMapping();
        if (assetMapping != null ? !assetMapping.equals(assetMapping2) : assetMapping2 != null) {
            return false;
        }
        String tempLink = getTempLink();
        String tempLink2 = listNodeRequest.getTempLink();
        if (tempLink != null ? !tempLink.equals(tempLink2) : tempLink2 != null) {
            return false;
        }
        String showAddress = getShowAddress();
        String showAddress2 = listNodeRequest.getShowAddress();
        if (showAddress != null ? !showAddress.equals(showAddress2) : showAddress2 != null) {
            return false;
        }
        DedupeContext dedupeContext = getDedupeContext();
        DedupeContext dedupeContext2 = listNodeRequest.getDedupeContext();
        return dedupeContext != null ? dedupeContext.equals(dedupeContext2) : dedupeContext2 == null;
    }

    public AssetMapping getAssetMapping() {
        return this.assetMapping;
    }

    public DedupeContext getDedupeContext() {
        return this.dedupeContext;
    }

    public String getShowAddress() {
        return this.showAddress;
    }

    public String getTempLink() {
        return this.tempLink;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        AssetMapping assetMapping = getAssetMapping();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (assetMapping == null ? 43 : assetMapping.hashCode());
        String tempLink = getTempLink();
        int hashCode3 = (hashCode2 * 59) + (tempLink == null ? 43 : tempLink.hashCode());
        String showAddress = getShowAddress();
        int hashCode4 = (hashCode3 * 59) + (showAddress == null ? 43 : showAddress.hashCode());
        DedupeContext dedupeContext = getDedupeContext();
        int i2 = hashCode4 * 59;
        if (dedupeContext != null) {
            i = dedupeContext.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("assetMapping")
    public void setAssetMapping(AssetMapping assetMapping) {
        this.assetMapping = assetMapping;
    }

    @JsonProperty("dedupeContext")
    public void setDedupeContext(DedupeContext dedupeContext) {
        this.dedupeContext = dedupeContext;
    }

    @JsonProperty("showAddress")
    public void setShowAddress(String str) {
        this.showAddress = str;
    }

    @JsonProperty("tempLink")
    public void setTempLink(String str) {
        this.tempLink = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListNodeRequest(assetMapping=");
        outline107.append(getAssetMapping());
        outline107.append(", tempLink=");
        outline107.append(getTempLink());
        outline107.append(", showAddress=");
        outline107.append(getShowAddress());
        outline107.append(", dedupeContext=");
        outline107.append(getDedupeContext());
        outline107.append(")");
        return outline107.toString();
    }
}
