package com.amazon.clouddrive.cdasdk.cds.node;

import com.amazon.clouddrive.cdasdk.cds.common.AssetMapping;
import com.amazon.clouddrive.cdasdk.cds.common.VersionedNodeRequest;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class GetNodeRequest extends VersionedNodeRequest {
    @JsonProperty("assetMapping")
    private AssetMapping assetMapping;
    @JsonProperty("fields")
    private String fields;
    @JsonProperty("groupShareToken")
    private String groupShareToken;
    @JsonProperty("id")
    private String id;
    @JsonProperty("lowResThumbnail")
    private Boolean lowResThumbnail;
    @JsonProperty(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY)
    private String ownerId;
    @JsonProperty("shareId")
    private String shareId;
    @JsonProperty("shareUriNonce")
    private String shareUriNonce;
    @JsonProperty("showAddress")
    private Boolean showAddress;
    @JsonProperty("tempLink")
    private Boolean tempLink;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.VersionedNodeRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof GetNodeRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.VersionedNodeRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetNodeRequest)) {
            return false;
        }
        GetNodeRequest getNodeRequest = (GetNodeRequest) obj;
        if (!getNodeRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        AssetMapping assetMapping = getAssetMapping();
        AssetMapping assetMapping2 = getNodeRequest.getAssetMapping();
        if (assetMapping != null ? !assetMapping.equals(assetMapping2) : assetMapping2 != null) {
            return false;
        }
        String id = getId();
        String id2 = getNodeRequest.getId();
        if (id != null ? !id.equals(id2) : id2 != null) {
            return false;
        }
        String fields = getFields();
        String fields2 = getNodeRequest.getFields();
        if (fields != null ? !fields.equals(fields2) : fields2 != null) {
            return false;
        }
        Boolean tempLink = getTempLink();
        Boolean tempLink2 = getNodeRequest.getTempLink();
        if (tempLink != null ? !tempLink.equals(tempLink2) : tempLink2 != null) {
            return false;
        }
        Boolean lowResThumbnail = getLowResThumbnail();
        Boolean lowResThumbnail2 = getNodeRequest.getLowResThumbnail();
        if (lowResThumbnail != null ? !lowResThumbnail.equals(lowResThumbnail2) : lowResThumbnail2 != null) {
            return false;
        }
        String shareId = getShareId();
        String shareId2 = getNodeRequest.getShareId();
        if (shareId != null ? !shareId.equals(shareId2) : shareId2 != null) {
            return false;
        }
        Boolean showAddress = getShowAddress();
        Boolean showAddress2 = getNodeRequest.getShowAddress();
        if (showAddress != null ? !showAddress.equals(showAddress2) : showAddress2 != null) {
            return false;
        }
        String ownerId = getOwnerId();
        String ownerId2 = getNodeRequest.getOwnerId();
        if (ownerId != null ? !ownerId.equals(ownerId2) : ownerId2 != null) {
            return false;
        }
        String shareUriNonce = getShareUriNonce();
        String shareUriNonce2 = getNodeRequest.getShareUriNonce();
        if (shareUriNonce != null ? !shareUriNonce.equals(shareUriNonce2) : shareUriNonce2 != null) {
            return false;
        }
        String groupShareToken = getGroupShareToken();
        String groupShareToken2 = getNodeRequest.getGroupShareToken();
        return groupShareToken != null ? groupShareToken.equals(groupShareToken2) : groupShareToken2 == null;
    }

    public AssetMapping getAssetMapping() {
        return this.assetMapping;
    }

    public String getFields() {
        return this.fields;
    }

    public String getGroupShareToken() {
        return this.groupShareToken;
    }

    public String getId() {
        return this.id;
    }

    public Boolean getLowResThumbnail() {
        return this.lowResThumbnail;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public String getShareId() {
        return this.shareId;
    }

    public String getShareUriNonce() {
        return this.shareUriNonce;
    }

    public Boolean getShowAddress() {
        return this.showAddress;
    }

    public Boolean getTempLink() {
        return this.tempLink;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.VersionedNodeRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        AssetMapping assetMapping = getAssetMapping();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (assetMapping == null ? 43 : assetMapping.hashCode());
        String id = getId();
        int hashCode3 = (hashCode2 * 59) + (id == null ? 43 : id.hashCode());
        String fields = getFields();
        int hashCode4 = (hashCode3 * 59) + (fields == null ? 43 : fields.hashCode());
        Boolean tempLink = getTempLink();
        int hashCode5 = (hashCode4 * 59) + (tempLink == null ? 43 : tempLink.hashCode());
        Boolean lowResThumbnail = getLowResThumbnail();
        int hashCode6 = (hashCode5 * 59) + (lowResThumbnail == null ? 43 : lowResThumbnail.hashCode());
        String shareId = getShareId();
        int hashCode7 = (hashCode6 * 59) + (shareId == null ? 43 : shareId.hashCode());
        Boolean showAddress = getShowAddress();
        int hashCode8 = (hashCode7 * 59) + (showAddress == null ? 43 : showAddress.hashCode());
        String ownerId = getOwnerId();
        int hashCode9 = (hashCode8 * 59) + (ownerId == null ? 43 : ownerId.hashCode());
        String shareUriNonce = getShareUriNonce();
        int hashCode10 = (hashCode9 * 59) + (shareUriNonce == null ? 43 : shareUriNonce.hashCode());
        String groupShareToken = getGroupShareToken();
        int i2 = hashCode10 * 59;
        if (groupShareToken != null) {
            i = groupShareToken.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("assetMapping")
    public void setAssetMapping(AssetMapping assetMapping) {
        this.assetMapping = assetMapping;
    }

    @JsonProperty("fields")
    public void setFields(String str) {
        this.fields = str;
    }

    @JsonProperty("groupShareToken")
    public void setGroupShareToken(String str) {
        this.groupShareToken = str;
    }

    @JsonProperty("id")
    public void setId(String str) {
        this.id = str;
    }

    @JsonProperty("lowResThumbnail")
    public void setLowResThumbnail(Boolean bool) {
        this.lowResThumbnail = bool;
    }

    @JsonProperty(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY)
    public void setOwnerId(String str) {
        this.ownerId = str;
    }

    @JsonProperty("shareId")
    public void setShareId(String str) {
        this.shareId = str;
    }

    @JsonProperty("shareUriNonce")
    public void setShareUriNonce(String str) {
        this.shareUriNonce = str;
    }

    @JsonProperty("showAddress")
    public void setShowAddress(Boolean bool) {
        this.showAddress = bool;
    }

    @JsonProperty("tempLink")
    public void setTempLink(Boolean bool) {
        this.tempLink = bool;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.VersionedNodeRequest, com.amazon.clouddrive.cdasdk.cds.common.CloudDriveRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetNodeRequest(assetMapping=");
        outline107.append(getAssetMapping());
        outline107.append(", id=");
        outline107.append(getId());
        outline107.append(", fields=");
        outline107.append(getFields());
        outline107.append(", tempLink=");
        outline107.append(getTempLink());
        outline107.append(", lowResThumbnail=");
        outline107.append(getLowResThumbnail());
        outline107.append(", shareId=");
        outline107.append(getShareId());
        outline107.append(", showAddress=");
        outline107.append(getShowAddress());
        outline107.append(", ownerId=");
        outline107.append(getOwnerId());
        outline107.append(", shareUriNonce=");
        outline107.append(getShareUriNonce());
        outline107.append(", groupShareToken=");
        outline107.append(getGroupShareToken());
        outline107.append(")");
        return outline107.toString();
    }
}
