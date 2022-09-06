package com.amazon.clouddrive.cdasdk.cds.common;

import com.amazon.alexa.sharing.api.models.Message;
import com.amazon.deecomms.smsmessaging.messagingcontroller.MessagingControllerConstant;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class NodeInfo extends EditableNodeInfo {
    @JsonProperty("assets")
    private List<String> assets;
    @JsonProperty("createdDate")
    private ISO8601 createdDate;
    @JsonProperty("eTagRequest")
    private String eTagRequest;
    @JsonProperty("eTagResponse")
    private String eTagResponse;
    @JsonProperty("exclusivelyTrashed")
    private Boolean exclusivelyTrashed;
    @JsonProperty("familyModifiedDate")
    private ISO8601 familyModifiedDate;
    @JsonProperty("isRoot")
    private Boolean isRoot;
    @JsonProperty("isShared")
    private Boolean isShared;
    @JsonProperty("lowResThumbnail")
    private String lowResThumbnail;
    @JsonProperty(Message.SERIALIZED_NAME_MODIFIED_DATE)
    private ISO8601 modifiedDate;
    @JsonProperty(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER)
    private String owner;
    @JsonProperty(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY)
    private String ownerId;
    @JsonProperty("purgeReason")
    private String purgeReason;
    @JsonProperty("recursivelyTrashed")
    private Boolean recursivelyTrashed;
    @JsonProperty("shareId")
    private String shareId;
    @JsonProperty("shareURL")
    private String shareURL;
    @JsonProperty("tempLink")
    private String tempLink;
    @JsonProperty("version")
    private Long version;

    @Override // com.amazon.clouddrive.cdasdk.cds.common.EditableNodeInfo
    protected boolean canEqual(Object obj) {
        return obj instanceof NodeInfo;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.EditableNodeInfo
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NodeInfo)) {
            return false;
        }
        NodeInfo nodeInfo = (NodeInfo) obj;
        if (!nodeInfo.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String eTagRequest = getETagRequest();
        String eTagRequest2 = nodeInfo.getETagRequest();
        if (eTagRequest != null ? !eTagRequest.equals(eTagRequest2) : eTagRequest2 != null) {
            return false;
        }
        String eTagResponse = getETagResponse();
        String eTagResponse2 = nodeInfo.getETagResponse();
        if (eTagResponse != null ? !eTagResponse.equals(eTagResponse2) : eTagResponse2 != null) {
            return false;
        }
        Long version = getVersion();
        Long version2 = nodeInfo.getVersion();
        if (version != null ? !version.equals(version2) : version2 != null) {
            return false;
        }
        ISO8601 modifiedDate = getModifiedDate();
        ISO8601 modifiedDate2 = nodeInfo.getModifiedDate();
        if (modifiedDate != null ? !modifiedDate.equals(modifiedDate2) : modifiedDate2 != null) {
            return false;
        }
        ISO8601 createdDate = getCreatedDate();
        ISO8601 createdDate2 = nodeInfo.getCreatedDate();
        if (createdDate != null ? !createdDate.equals(createdDate2) : createdDate2 != null) {
            return false;
        }
        String owner = getOwner();
        String owner2 = nodeInfo.getOwner();
        if (owner != null ? !owner.equals(owner2) : owner2 != null) {
            return false;
        }
        List<String> assets = getAssets();
        List<String> assets2 = nodeInfo.getAssets();
        if (assets != null ? !assets.equals(assets2) : assets2 != null) {
            return false;
        }
        String tempLink = getTempLink();
        String tempLink2 = nodeInfo.getTempLink();
        if (tempLink != null ? !tempLink.equals(tempLink2) : tempLink2 != null) {
            return false;
        }
        Boolean exclusivelyTrashed = getExclusivelyTrashed();
        Boolean exclusivelyTrashed2 = nodeInfo.getExclusivelyTrashed();
        if (exclusivelyTrashed != null ? !exclusivelyTrashed.equals(exclusivelyTrashed2) : exclusivelyTrashed2 != null) {
            return false;
        }
        Boolean recursivelyTrashed = getRecursivelyTrashed();
        Boolean recursivelyTrashed2 = nodeInfo.getRecursivelyTrashed();
        if (recursivelyTrashed != null ? !recursivelyTrashed.equals(recursivelyTrashed2) : recursivelyTrashed2 != null) {
            return false;
        }
        Boolean isRoot = getIsRoot();
        Boolean isRoot2 = nodeInfo.getIsRoot();
        if (isRoot != null ? !isRoot.equals(isRoot2) : isRoot2 != null) {
            return false;
        }
        Boolean isShared = getIsShared();
        Boolean isShared2 = nodeInfo.getIsShared();
        if (isShared != null ? !isShared.equals(isShared2) : isShared2 != null) {
            return false;
        }
        String shareId = getShareId();
        String shareId2 = nodeInfo.getShareId();
        if (shareId != null ? !shareId.equals(shareId2) : shareId2 != null) {
            return false;
        }
        String shareURL = getShareURL();
        String shareURL2 = nodeInfo.getShareURL();
        if (shareURL != null ? !shareURL.equals(shareURL2) : shareURL2 != null) {
            return false;
        }
        String ownerId = getOwnerId();
        String ownerId2 = nodeInfo.getOwnerId();
        if (ownerId != null ? !ownerId.equals(ownerId2) : ownerId2 != null) {
            return false;
        }
        ISO8601 familyModifiedDate = getFamilyModifiedDate();
        ISO8601 familyModifiedDate2 = nodeInfo.getFamilyModifiedDate();
        if (familyModifiedDate != null ? !familyModifiedDate.equals(familyModifiedDate2) : familyModifiedDate2 != null) {
            return false;
        }
        String purgeReason = getPurgeReason();
        String purgeReason2 = nodeInfo.getPurgeReason();
        if (purgeReason != null ? !purgeReason.equals(purgeReason2) : purgeReason2 != null) {
            return false;
        }
        String lowResThumbnail = getLowResThumbnail();
        String lowResThumbnail2 = nodeInfo.getLowResThumbnail();
        return lowResThumbnail != null ? lowResThumbnail.equals(lowResThumbnail2) : lowResThumbnail2 == null;
    }

    public List<String> getAssets() {
        return this.assets;
    }

    public ISO8601 getCreatedDate() {
        return this.createdDate;
    }

    public String getETagRequest() {
        return this.eTagRequest;
    }

    public String getETagResponse() {
        return this.eTagResponse;
    }

    public Boolean getExclusivelyTrashed() {
        return this.exclusivelyTrashed;
    }

    public ISO8601 getFamilyModifiedDate() {
        return this.familyModifiedDate;
    }

    public Boolean getIsRoot() {
        return this.isRoot;
    }

    public Boolean getIsShared() {
        return this.isShared;
    }

    public String getLowResThumbnail() {
        return this.lowResThumbnail;
    }

    public ISO8601 getModifiedDate() {
        return this.modifiedDate;
    }

    public String getOwner() {
        return this.owner;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public String getPurgeReason() {
        return this.purgeReason;
    }

    public Boolean getRecursivelyTrashed() {
        return this.recursivelyTrashed;
    }

    public String getShareId() {
        return this.shareId;
    }

    public String getShareURL() {
        return this.shareURL;
    }

    public String getTempLink() {
        return this.tempLink;
    }

    public Long getVersion() {
        return this.version;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.EditableNodeInfo
    public int hashCode() {
        int hashCode = super.hashCode();
        String eTagRequest = getETagRequest();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (eTagRequest == null ? 43 : eTagRequest.hashCode());
        String eTagResponse = getETagResponse();
        int hashCode3 = (hashCode2 * 59) + (eTagResponse == null ? 43 : eTagResponse.hashCode());
        Long version = getVersion();
        int hashCode4 = (hashCode3 * 59) + (version == null ? 43 : version.hashCode());
        ISO8601 modifiedDate = getModifiedDate();
        int hashCode5 = (hashCode4 * 59) + (modifiedDate == null ? 43 : modifiedDate.hashCode());
        ISO8601 createdDate = getCreatedDate();
        int hashCode6 = (hashCode5 * 59) + (createdDate == null ? 43 : createdDate.hashCode());
        String owner = getOwner();
        int hashCode7 = (hashCode6 * 59) + (owner == null ? 43 : owner.hashCode());
        List<String> assets = getAssets();
        int hashCode8 = (hashCode7 * 59) + (assets == null ? 43 : assets.hashCode());
        String tempLink = getTempLink();
        int hashCode9 = (hashCode8 * 59) + (tempLink == null ? 43 : tempLink.hashCode());
        Boolean exclusivelyTrashed = getExclusivelyTrashed();
        int hashCode10 = (hashCode9 * 59) + (exclusivelyTrashed == null ? 43 : exclusivelyTrashed.hashCode());
        Boolean recursivelyTrashed = getRecursivelyTrashed();
        int hashCode11 = (hashCode10 * 59) + (recursivelyTrashed == null ? 43 : recursivelyTrashed.hashCode());
        Boolean isRoot = getIsRoot();
        int hashCode12 = (hashCode11 * 59) + (isRoot == null ? 43 : isRoot.hashCode());
        Boolean isShared = getIsShared();
        int hashCode13 = (hashCode12 * 59) + (isShared == null ? 43 : isShared.hashCode());
        String shareId = getShareId();
        int hashCode14 = (hashCode13 * 59) + (shareId == null ? 43 : shareId.hashCode());
        String shareURL = getShareURL();
        int hashCode15 = (hashCode14 * 59) + (shareURL == null ? 43 : shareURL.hashCode());
        String ownerId = getOwnerId();
        int hashCode16 = (hashCode15 * 59) + (ownerId == null ? 43 : ownerId.hashCode());
        ISO8601 familyModifiedDate = getFamilyModifiedDate();
        int hashCode17 = (hashCode16 * 59) + (familyModifiedDate == null ? 43 : familyModifiedDate.hashCode());
        String purgeReason = getPurgeReason();
        int hashCode18 = (hashCode17 * 59) + (purgeReason == null ? 43 : purgeReason.hashCode());
        String lowResThumbnail = getLowResThumbnail();
        int i2 = hashCode18 * 59;
        if (lowResThumbnail != null) {
            i = lowResThumbnail.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("assets")
    public void setAssets(List<String> list) {
        this.assets = list;
    }

    @JsonProperty("createdDate")
    public void setCreatedDate(ISO8601 iso8601) {
        this.createdDate = iso8601;
    }

    @JsonProperty("eTagRequest")
    public void setETagRequest(String str) {
        this.eTagRequest = str;
    }

    @JsonProperty("eTagResponse")
    public void setETagResponse(String str) {
        this.eTagResponse = str;
    }

    @JsonProperty("exclusivelyTrashed")
    public void setExclusivelyTrashed(Boolean bool) {
        this.exclusivelyTrashed = bool;
    }

    @JsonProperty("familyModifiedDate")
    public void setFamilyModifiedDate(ISO8601 iso8601) {
        this.familyModifiedDate = iso8601;
    }

    @JsonProperty("isRoot")
    public void setIsRoot(Boolean bool) {
        this.isRoot = bool;
    }

    @JsonProperty("isShared")
    public void setIsShared(Boolean bool) {
        this.isShared = bool;
    }

    @JsonProperty("lowResThumbnail")
    public void setLowResThumbnail(String str) {
        this.lowResThumbnail = str;
    }

    @JsonProperty(Message.SERIALIZED_NAME_MODIFIED_DATE)
    public void setModifiedDate(ISO8601 iso8601) {
        this.modifiedDate = iso8601;
    }

    @JsonProperty(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER)
    public void setOwner(String str) {
        this.owner = str;
    }

    @JsonProperty(MessagingControllerConstant.MESSAGING_CONTROLLER_OWNER_ID_KEY)
    public void setOwnerId(String str) {
        this.ownerId = str;
    }

    @JsonProperty("purgeReason")
    public void setPurgeReason(String str) {
        this.purgeReason = str;
    }

    @JsonProperty("recursivelyTrashed")
    public void setRecursivelyTrashed(Boolean bool) {
        this.recursivelyTrashed = bool;
    }

    @JsonProperty("shareId")
    public void setShareId(String str) {
        this.shareId = str;
    }

    @JsonProperty("shareURL")
    public void setShareURL(String str) {
        this.shareURL = str;
    }

    @JsonProperty("tempLink")
    public void setTempLink(String str) {
        this.tempLink = str;
    }

    @JsonProperty("version")
    public void setVersion(Long l) {
        this.version = l;
    }

    @Override // com.amazon.clouddrive.cdasdk.cds.common.EditableNodeInfo
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NodeInfo(eTagRequest=");
        outline107.append(getETagRequest());
        outline107.append(", eTagResponse=");
        outline107.append(getETagResponse());
        outline107.append(", version=");
        outline107.append(getVersion());
        outline107.append(", modifiedDate=");
        outline107.append(getModifiedDate());
        outline107.append(", createdDate=");
        outline107.append(getCreatedDate());
        outline107.append(", owner=");
        outline107.append(getOwner());
        outline107.append(", assets=");
        outline107.append(getAssets());
        outline107.append(", tempLink=");
        outline107.append(getTempLink());
        outline107.append(", exclusivelyTrashed=");
        outline107.append(getExclusivelyTrashed());
        outline107.append(", recursivelyTrashed=");
        outline107.append(getRecursivelyTrashed());
        outline107.append(", isRoot=");
        outline107.append(getIsRoot());
        outline107.append(", isShared=");
        outline107.append(getIsShared());
        outline107.append(", shareId=");
        outline107.append(getShareId());
        outline107.append(", shareURL=");
        outline107.append(getShareURL());
        outline107.append(", ownerId=");
        outline107.append(getOwnerId());
        outline107.append(", familyModifiedDate=");
        outline107.append(getFamilyModifiedDate());
        outline107.append(", purgeReason=");
        outline107.append(getPurgeReason());
        outline107.append(", lowResThumbnail=");
        outline107.append(getLowResThumbnail());
        outline107.append(")");
        return outline107.toString();
    }
}
