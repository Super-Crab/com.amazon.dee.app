package com.amazon.clouddrive.cdasdk.cdus;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.cdp.ConflictResolution;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class UpdateContentRequest extends ServiceRequest {
    @JsonIgnore
    private List<String> accessRuleIds;
    @JsonProperty("addToFamilyArchive")
    private Boolean addToFamilyArchive;
    @JsonProperty("assetType")
    private String assetType;
    @JsonProperty("conflictResolution")
    private ConflictResolution conflictResolution;
    @JsonProperty("contactCount")
    private Long contactCount;
    @JsonProperty("contentDate")
    private String contentDate;
    @JsonProperty("fileSize")
    private Long fileSize;
    @JsonProperty("isExpirable")
    private Boolean isExpirable;
    @JsonProperty("isFavorite")
    private Boolean isFavorite;
    @JsonProperty("isHidden")
    private Boolean isHidden;
    @JsonProperty(ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME)
    private String kind;
    @JsonProperty("nonBillable")
    private Boolean nonBillable;
    @JsonProperty("parentNodeId")
    private String parentNodeId;
    @JsonProperty("restricted")
    private Boolean restricted;
    @JsonProperty("subkind")
    private String subkind;
    @JsonProperty("suppress")
    private String suppress;

    public UpdateContentRequest(@NonNull Long l) {
        this.fileSize = l;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.ServiceRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof UpdateContentRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.ServiceRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UpdateContentRequest)) {
            return false;
        }
        UpdateContentRequest updateContentRequest = (UpdateContentRequest) obj;
        if (!updateContentRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        Long fileSize = getFileSize();
        Long fileSize2 = updateContentRequest.getFileSize();
        if (fileSize != null ? !fileSize.equals(fileSize2) : fileSize2 != null) {
            return false;
        }
        String contentDate = getContentDate();
        String contentDate2 = updateContentRequest.getContentDate();
        if (contentDate != null ? !contentDate.equals(contentDate2) : contentDate2 != null) {
            return false;
        }
        ConflictResolution conflictResolution = getConflictResolution();
        ConflictResolution conflictResolution2 = updateContentRequest.getConflictResolution();
        if (conflictResolution != null ? !conflictResolution.equals(conflictResolution2) : conflictResolution2 != null) {
            return false;
        }
        String suppress = getSuppress();
        String suppress2 = updateContentRequest.getSuppress();
        if (suppress != null ? !suppress.equals(suppress2) : suppress2 != null) {
            return false;
        }
        String kind = getKind();
        String kind2 = updateContentRequest.getKind();
        if (kind != null ? !kind.equals(kind2) : kind2 != null) {
            return false;
        }
        String subkind = getSubkind();
        String subkind2 = updateContentRequest.getSubkind();
        if (subkind != null ? !subkind.equals(subkind2) : subkind2 != null) {
            return false;
        }
        String assetType = getAssetType();
        String assetType2 = updateContentRequest.getAssetType();
        if (assetType != null ? !assetType.equals(assetType2) : assetType2 != null) {
            return false;
        }
        Boolean addToFamilyArchive = getAddToFamilyArchive();
        Boolean addToFamilyArchive2 = updateContentRequest.getAddToFamilyArchive();
        if (addToFamilyArchive != null ? !addToFamilyArchive.equals(addToFamilyArchive2) : addToFamilyArchive2 != null) {
            return false;
        }
        Boolean isFavorite = getIsFavorite();
        Boolean isFavorite2 = updateContentRequest.getIsFavorite();
        if (isFavorite != null ? !isFavorite.equals(isFavorite2) : isFavorite2 != null) {
            return false;
        }
        Boolean isHidden = getIsHidden();
        Boolean isHidden2 = updateContentRequest.getIsHidden();
        if (isHidden != null ? !isHidden.equals(isHidden2) : isHidden2 != null) {
            return false;
        }
        String parentNodeId = getParentNodeId();
        String parentNodeId2 = updateContentRequest.getParentNodeId();
        if (parentNodeId != null ? !parentNodeId.equals(parentNodeId2) : parentNodeId2 != null) {
            return false;
        }
        Boolean nonBillable = getNonBillable();
        Boolean nonBillable2 = updateContentRequest.getNonBillable();
        if (nonBillable != null ? !nonBillable.equals(nonBillable2) : nonBillable2 != null) {
            return false;
        }
        Boolean isExpirable = getIsExpirable();
        Boolean isExpirable2 = updateContentRequest.getIsExpirable();
        if (isExpirable != null ? !isExpirable.equals(isExpirable2) : isExpirable2 != null) {
            return false;
        }
        Boolean restricted = getRestricted();
        Boolean restricted2 = updateContentRequest.getRestricted();
        if (restricted != null ? !restricted.equals(restricted2) : restricted2 != null) {
            return false;
        }
        List<String> accessRuleIds = getAccessRuleIds();
        List<String> accessRuleIds2 = updateContentRequest.getAccessRuleIds();
        if (accessRuleIds != null ? !accessRuleIds.equals(accessRuleIds2) : accessRuleIds2 != null) {
            return false;
        }
        Long contactCount = getContactCount();
        Long contactCount2 = updateContentRequest.getContactCount();
        return contactCount != null ? contactCount.equals(contactCount2) : contactCount2 == null;
    }

    public List<String> getAccessRuleIds() {
        return this.accessRuleIds;
    }

    public Boolean getAddToFamilyArchive() {
        return this.addToFamilyArchive;
    }

    public String getAssetType() {
        return this.assetType;
    }

    public ConflictResolution getConflictResolution() {
        return this.conflictResolution;
    }

    public Long getContactCount() {
        return this.contactCount;
    }

    public String getContentDate() {
        return this.contentDate;
    }

    public Long getFileSize() {
        return this.fileSize;
    }

    public Boolean getIsExpirable() {
        return this.isExpirable;
    }

    public Boolean getIsFavorite() {
        return this.isFavorite;
    }

    public Boolean getIsHidden() {
        return this.isHidden;
    }

    public String getKind() {
        return this.kind;
    }

    public Boolean getNonBillable() {
        return this.nonBillable;
    }

    public String getParentNodeId() {
        return this.parentNodeId;
    }

    public Boolean getRestricted() {
        return this.restricted;
    }

    public String getSubkind() {
        return this.subkind;
    }

    public String getSuppress() {
        return this.suppress;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.ServiceRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        Long fileSize = getFileSize();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (fileSize == null ? 43 : fileSize.hashCode());
        String contentDate = getContentDate();
        int hashCode3 = (hashCode2 * 59) + (contentDate == null ? 43 : contentDate.hashCode());
        ConflictResolution conflictResolution = getConflictResolution();
        int hashCode4 = (hashCode3 * 59) + (conflictResolution == null ? 43 : conflictResolution.hashCode());
        String suppress = getSuppress();
        int hashCode5 = (hashCode4 * 59) + (suppress == null ? 43 : suppress.hashCode());
        String kind = getKind();
        int hashCode6 = (hashCode5 * 59) + (kind == null ? 43 : kind.hashCode());
        String subkind = getSubkind();
        int hashCode7 = (hashCode6 * 59) + (subkind == null ? 43 : subkind.hashCode());
        String assetType = getAssetType();
        int hashCode8 = (hashCode7 * 59) + (assetType == null ? 43 : assetType.hashCode());
        Boolean addToFamilyArchive = getAddToFamilyArchive();
        int hashCode9 = (hashCode8 * 59) + (addToFamilyArchive == null ? 43 : addToFamilyArchive.hashCode());
        Boolean isFavorite = getIsFavorite();
        int hashCode10 = (hashCode9 * 59) + (isFavorite == null ? 43 : isFavorite.hashCode());
        Boolean isHidden = getIsHidden();
        int hashCode11 = (hashCode10 * 59) + (isHidden == null ? 43 : isHidden.hashCode());
        String parentNodeId = getParentNodeId();
        int hashCode12 = (hashCode11 * 59) + (parentNodeId == null ? 43 : parentNodeId.hashCode());
        Boolean nonBillable = getNonBillable();
        int hashCode13 = (hashCode12 * 59) + (nonBillable == null ? 43 : nonBillable.hashCode());
        Boolean isExpirable = getIsExpirable();
        int hashCode14 = (hashCode13 * 59) + (isExpirable == null ? 43 : isExpirable.hashCode());
        Boolean restricted = getRestricted();
        int hashCode15 = (hashCode14 * 59) + (restricted == null ? 43 : restricted.hashCode());
        List<String> accessRuleIds = getAccessRuleIds();
        int hashCode16 = (hashCode15 * 59) + (accessRuleIds == null ? 43 : accessRuleIds.hashCode());
        Long contactCount = getContactCount();
        int i2 = hashCode16 * 59;
        if (contactCount != null) {
            i = contactCount.hashCode();
        }
        return i2 + i;
    }

    public void setAccessRuleIds(List<String> list) {
        this.accessRuleIds = list;
    }

    @JsonProperty("addToFamilyArchive")
    public void setAddToFamilyArchive(Boolean bool) {
        this.addToFamilyArchive = bool;
    }

    @JsonProperty("assetType")
    public void setAssetType(String str) {
        this.assetType = str;
    }

    @JsonProperty("conflictResolution")
    public void setConflictResolution(ConflictResolution conflictResolution) {
        this.conflictResolution = conflictResolution;
    }

    @JsonProperty("contactCount")
    public void setContactCount(Long l) {
        this.contactCount = l;
    }

    @JsonProperty("contentDate")
    public void setContentDate(String str) {
        this.contentDate = str;
    }

    @JsonProperty("fileSize")
    public void setFileSize(Long l) {
        this.fileSize = l;
    }

    @JsonProperty("isExpirable")
    public void setIsExpirable(Boolean bool) {
        this.isExpirable = bool;
    }

    @JsonProperty("isFavorite")
    public void setIsFavorite(Boolean bool) {
        this.isFavorite = bool;
    }

    @JsonProperty("isHidden")
    public void setIsHidden(Boolean bool) {
        this.isHidden = bool;
    }

    @JsonProperty(ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME)
    public void setKind(String str) {
        this.kind = str;
    }

    @JsonProperty("nonBillable")
    public void setNonBillable(Boolean bool) {
        this.nonBillable = bool;
    }

    @JsonProperty("parentNodeId")
    public void setParentNodeId(String str) {
        this.parentNodeId = str;
    }

    @JsonProperty("restricted")
    public void setRestricted(Boolean bool) {
        this.restricted = bool;
    }

    @JsonProperty("subkind")
    public void setSubkind(String str) {
        this.subkind = str;
    }

    @JsonProperty("suppress")
    public void setSuppress(String str) {
        this.suppress = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.ServiceRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UpdateContentRequest(fileSize=");
        outline107.append(getFileSize());
        outline107.append(", contentDate=");
        outline107.append(getContentDate());
        outline107.append(", conflictResolution=");
        outline107.append(getConflictResolution());
        outline107.append(", suppress=");
        outline107.append(getSuppress());
        outline107.append(", kind=");
        outline107.append(getKind());
        outline107.append(", subkind=");
        outline107.append(getSubkind());
        outline107.append(", assetType=");
        outline107.append(getAssetType());
        outline107.append(", addToFamilyArchive=");
        outline107.append(getAddToFamilyArchive());
        outline107.append(", isFavorite=");
        outline107.append(getIsFavorite());
        outline107.append(", isHidden=");
        outline107.append(getIsHidden());
        outline107.append(", parentNodeId=");
        outline107.append(getParentNodeId());
        outline107.append(", nonBillable=");
        outline107.append(getNonBillable());
        outline107.append(", isExpirable=");
        outline107.append(getIsExpirable());
        outline107.append(", restricted=");
        outline107.append(getRestricted());
        outline107.append(", accessRuleIds=");
        outline107.append(getAccessRuleIds());
        outline107.append(", contactCount=");
        outline107.append(getContactCount());
        outline107.append(")");
        return outline107.toString();
    }
}
