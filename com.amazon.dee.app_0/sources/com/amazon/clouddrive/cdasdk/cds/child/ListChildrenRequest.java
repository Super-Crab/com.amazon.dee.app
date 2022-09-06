package com.amazon.clouddrive.cdasdk.cds.child;

import com.amazon.clouddrive.cdasdk.cds.common.AssetMapping;
import com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveRequest;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class ListChildrenRequest extends PaginatedCloudDriveRequest {
    @JsonProperty("assetMapping")
    private AssetMapping assetMapping;
    @JsonProperty("id")
    private String id;
    @JsonProperty(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY)
    private String ownerId;
    @JsonProperty("searchOnFamily")
    private String searchOnFamily;
    @JsonProperty("shareId")
    private String shareId;
    @JsonProperty("tempLink")
    private String tempLink;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof ListChildrenRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListChildrenRequest)) {
            return false;
        }
        ListChildrenRequest listChildrenRequest = (ListChildrenRequest) obj;
        if (!listChildrenRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        AssetMapping assetMapping = getAssetMapping();
        AssetMapping assetMapping2 = listChildrenRequest.getAssetMapping();
        if (assetMapping != null ? !assetMapping.equals(assetMapping2) : assetMapping2 != null) {
            return false;
        }
        String id = getId();
        String id2 = listChildrenRequest.getId();
        if (id != null ? !id.equals(id2) : id2 != null) {
            return false;
        }
        String tempLink = getTempLink();
        String tempLink2 = listChildrenRequest.getTempLink();
        if (tempLink != null ? !tempLink.equals(tempLink2) : tempLink2 != null) {
            return false;
        }
        String shareId = getShareId();
        String shareId2 = listChildrenRequest.getShareId();
        if (shareId != null ? !shareId.equals(shareId2) : shareId2 != null) {
            return false;
        }
        String searchOnFamily = getSearchOnFamily();
        String searchOnFamily2 = listChildrenRequest.getSearchOnFamily();
        if (searchOnFamily != null ? !searchOnFamily.equals(searchOnFamily2) : searchOnFamily2 != null) {
            return false;
        }
        String ownerId = getOwnerId();
        String ownerId2 = listChildrenRequest.getOwnerId();
        return ownerId != null ? ownerId.equals(ownerId2) : ownerId2 == null;
    }

    public AssetMapping getAssetMapping() {
        return this.assetMapping;
    }

    public String getId() {
        return this.id;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public String getSearchOnFamily() {
        return this.searchOnFamily;
    }

    public String getShareId() {
        return this.shareId;
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
        String id = getId();
        int hashCode3 = (hashCode2 * 59) + (id == null ? 43 : id.hashCode());
        String tempLink = getTempLink();
        int hashCode4 = (hashCode3 * 59) + (tempLink == null ? 43 : tempLink.hashCode());
        String shareId = getShareId();
        int hashCode5 = (hashCode4 * 59) + (shareId == null ? 43 : shareId.hashCode());
        String searchOnFamily = getSearchOnFamily();
        int hashCode6 = (hashCode5 * 59) + (searchOnFamily == null ? 43 : searchOnFamily.hashCode());
        String ownerId = getOwnerId();
        int i2 = hashCode6 * 59;
        if (ownerId != null) {
            i = ownerId.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("assetMapping")
    public void setAssetMapping(AssetMapping assetMapping) {
        this.assetMapping = assetMapping;
    }

    @JsonProperty("id")
    public void setId(String str) {
        this.id = str;
    }

    @JsonProperty(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY)
    public void setOwnerId(String str) {
        this.ownerId = str;
    }

    @JsonProperty("searchOnFamily")
    public void setSearchOnFamily(String str) {
        this.searchOnFamily = str;
    }

    @JsonProperty("shareId")
    public void setShareId(String str) {
        this.shareId = str;
    }

    @JsonProperty("tempLink")
    public void setTempLink(String str) {
        this.tempLink = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.PaginatedCloudDriveRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListChildrenRequest(assetMapping=");
        outline107.append(getAssetMapping());
        outline107.append(", id=");
        outline107.append(getId());
        outline107.append(", tempLink=");
        outline107.append(getTempLink());
        outline107.append(", shareId=");
        outline107.append(getShareId());
        outline107.append(", searchOnFamily=");
        outline107.append(getSearchOnFamily());
        outline107.append(", ownerId=");
        outline107.append(getOwnerId());
        outline107.append(")");
        return outline107.toString();
    }
}
