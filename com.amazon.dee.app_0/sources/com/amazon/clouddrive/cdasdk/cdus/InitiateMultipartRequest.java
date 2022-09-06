package com.amazon.clouddrive.cdasdk.cdus;

import com.amazon.clouddrive.cdasdk.cdp.ConflictResolution;
import com.amazon.clouddrive.cdasdk.cds.common.ContentSignatureType;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class InitiateMultipartRequest extends ServiceRequest {
    @JsonProperty("accessRuleIds")
    private String accessRuleIds;
    @JsonProperty("addToFamilyArchive")
    private Boolean addToFamilyArchive;
    @JsonProperty("assetType")
    private String assetType;
    @JsonProperty("conflictResolution")
    private ConflictResolution conflictResolution;
    @JsonProperty("contentDate")
    private String contentDate;
    @JsonProperty("contentSignature")
    private String contentSignature;
    @JsonProperty("contentSignatureType")
    private ContentSignatureType contentSignatureType;
    @JsonProperty("fallbackContentDate")
    private String fallbackContentDate;
    @JsonProperty("isFavorite")
    private Boolean isFavorite;
    @JsonProperty("isHidden")
    private Boolean isHidden;
    @JsonProperty(ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME)
    private String kind;
    @JsonProperty("name")
    private String name;
    @JsonProperty("parentNodeId")
    private String parentNodeId;
    @JsonProperty("suppress")
    private String suppress;
    @JsonProperty("fileSize")
    private Long totalFileSize;

    public InitiateMultipartRequest(Long l, String str) {
        this.totalFileSize = l;
        this.name = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.ServiceRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof InitiateMultipartRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.ServiceRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof InitiateMultipartRequest)) {
            return false;
        }
        InitiateMultipartRequest initiateMultipartRequest = (InitiateMultipartRequest) obj;
        if (!initiateMultipartRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        Long totalFileSize = getTotalFileSize();
        Long totalFileSize2 = initiateMultipartRequest.getTotalFileSize();
        if (totalFileSize != null ? !totalFileSize.equals(totalFileSize2) : totalFileSize2 != null) {
            return false;
        }
        String name = getName();
        String name2 = initiateMultipartRequest.getName();
        if (name != null ? !name.equals(name2) : name2 != null) {
            return false;
        }
        String contentDate = getContentDate();
        String contentDate2 = initiateMultipartRequest.getContentDate();
        if (contentDate != null ? !contentDate.equals(contentDate2) : contentDate2 != null) {
            return false;
        }
        String fallbackContentDate = getFallbackContentDate();
        String fallbackContentDate2 = initiateMultipartRequest.getFallbackContentDate();
        if (fallbackContentDate != null ? !fallbackContentDate.equals(fallbackContentDate2) : fallbackContentDate2 != null) {
            return false;
        }
        ConflictResolution conflictResolution = getConflictResolution();
        ConflictResolution conflictResolution2 = initiateMultipartRequest.getConflictResolution();
        if (conflictResolution != null ? !conflictResolution.equals(conflictResolution2) : conflictResolution2 != null) {
            return false;
        }
        String suppress = getSuppress();
        String suppress2 = initiateMultipartRequest.getSuppress();
        if (suppress != null ? !suppress.equals(suppress2) : suppress2 != null) {
            return false;
        }
        String kind = getKind();
        String kind2 = initiateMultipartRequest.getKind();
        if (kind != null ? !kind.equals(kind2) : kind2 != null) {
            return false;
        }
        String assetType = getAssetType();
        String assetType2 = initiateMultipartRequest.getAssetType();
        if (assetType != null ? !assetType.equals(assetType2) : assetType2 != null) {
            return false;
        }
        Boolean addToFamilyArchive = getAddToFamilyArchive();
        Boolean addToFamilyArchive2 = initiateMultipartRequest.getAddToFamilyArchive();
        if (addToFamilyArchive != null ? !addToFamilyArchive.equals(addToFamilyArchive2) : addToFamilyArchive2 != null) {
            return false;
        }
        Boolean isFavorite = getIsFavorite();
        Boolean isFavorite2 = initiateMultipartRequest.getIsFavorite();
        if (isFavorite != null ? !isFavorite.equals(isFavorite2) : isFavorite2 != null) {
            return false;
        }
        Boolean isHidden = getIsHidden();
        Boolean isHidden2 = initiateMultipartRequest.getIsHidden();
        if (isHidden != null ? !isHidden.equals(isHidden2) : isHidden2 != null) {
            return false;
        }
        String parentNodeId = getParentNodeId();
        String parentNodeId2 = initiateMultipartRequest.getParentNodeId();
        if (parentNodeId != null ? !parentNodeId.equals(parentNodeId2) : parentNodeId2 != null) {
            return false;
        }
        String contentSignature = getContentSignature();
        String contentSignature2 = initiateMultipartRequest.getContentSignature();
        if (contentSignature != null ? !contentSignature.equals(contentSignature2) : contentSignature2 != null) {
            return false;
        }
        ContentSignatureType contentSignatureType = getContentSignatureType();
        ContentSignatureType contentSignatureType2 = initiateMultipartRequest.getContentSignatureType();
        if (contentSignatureType != null ? !contentSignatureType.equals(contentSignatureType2) : contentSignatureType2 != null) {
            return false;
        }
        String accessRuleIds = getAccessRuleIds();
        String accessRuleIds2 = initiateMultipartRequest.getAccessRuleIds();
        return accessRuleIds != null ? accessRuleIds.equals(accessRuleIds2) : accessRuleIds2 == null;
    }

    public String getAccessRuleIds() {
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

    public String getContentDate() {
        return this.contentDate;
    }

    public String getContentSignature() {
        return this.contentSignature;
    }

    public ContentSignatureType getContentSignatureType() {
        return this.contentSignatureType;
    }

    public String getFallbackContentDate() {
        return this.fallbackContentDate;
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

    public String getName() {
        return this.name;
    }

    public String getParentNodeId() {
        return this.parentNodeId;
    }

    public String getSuppress() {
        return this.suppress;
    }

    public Long getTotalFileSize() {
        return this.totalFileSize;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.ServiceRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        Long totalFileSize = getTotalFileSize();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (totalFileSize == null ? 43 : totalFileSize.hashCode());
        String name = getName();
        int hashCode3 = (hashCode2 * 59) + (name == null ? 43 : name.hashCode());
        String contentDate = getContentDate();
        int hashCode4 = (hashCode3 * 59) + (contentDate == null ? 43 : contentDate.hashCode());
        String fallbackContentDate = getFallbackContentDate();
        int hashCode5 = (hashCode4 * 59) + (fallbackContentDate == null ? 43 : fallbackContentDate.hashCode());
        ConflictResolution conflictResolution = getConflictResolution();
        int hashCode6 = (hashCode5 * 59) + (conflictResolution == null ? 43 : conflictResolution.hashCode());
        String suppress = getSuppress();
        int hashCode7 = (hashCode6 * 59) + (suppress == null ? 43 : suppress.hashCode());
        String kind = getKind();
        int hashCode8 = (hashCode7 * 59) + (kind == null ? 43 : kind.hashCode());
        String assetType = getAssetType();
        int hashCode9 = (hashCode8 * 59) + (assetType == null ? 43 : assetType.hashCode());
        Boolean addToFamilyArchive = getAddToFamilyArchive();
        int hashCode10 = (hashCode9 * 59) + (addToFamilyArchive == null ? 43 : addToFamilyArchive.hashCode());
        Boolean isFavorite = getIsFavorite();
        int hashCode11 = (hashCode10 * 59) + (isFavorite == null ? 43 : isFavorite.hashCode());
        Boolean isHidden = getIsHidden();
        int hashCode12 = (hashCode11 * 59) + (isHidden == null ? 43 : isHidden.hashCode());
        String parentNodeId = getParentNodeId();
        int hashCode13 = (hashCode12 * 59) + (parentNodeId == null ? 43 : parentNodeId.hashCode());
        String contentSignature = getContentSignature();
        int hashCode14 = (hashCode13 * 59) + (contentSignature == null ? 43 : contentSignature.hashCode());
        ContentSignatureType contentSignatureType = getContentSignatureType();
        int hashCode15 = (hashCode14 * 59) + (contentSignatureType == null ? 43 : contentSignatureType.hashCode());
        String accessRuleIds = getAccessRuleIds();
        int i2 = hashCode15 * 59;
        if (accessRuleIds != null) {
            i = accessRuleIds.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("accessRuleIds")
    public void setAccessRuleIds(String str) {
        this.accessRuleIds = str;
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

    @JsonProperty("contentDate")
    public void setContentDate(String str) {
        this.contentDate = str;
    }

    @JsonProperty("contentSignature")
    public void setContentSignature(String str) {
        this.contentSignature = str;
    }

    @JsonProperty("contentSignatureType")
    public void setContentSignatureType(ContentSignatureType contentSignatureType) {
        this.contentSignatureType = contentSignatureType;
    }

    @JsonProperty("fallbackContentDate")
    public void setFallbackContentDate(String str) {
        this.fallbackContentDate = str;
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

    @JsonProperty("name")
    public void setName(String str) {
        this.name = str;
    }

    @JsonProperty("parentNodeId")
    public void setParentNodeId(String str) {
        this.parentNodeId = str;
    }

    @JsonProperty("suppress")
    public void setSuppress(String str) {
        this.suppress = str;
    }

    @JsonProperty("fileSize")
    public void setTotalFileSize(Long l) {
        this.totalFileSize = l;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.ServiceRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("InitiateMultipartRequest(totalFileSize=");
        outline107.append(getTotalFileSize());
        outline107.append(", name=");
        outline107.append(getName());
        outline107.append(", contentDate=");
        outline107.append(getContentDate());
        outline107.append(", fallbackContentDate=");
        outline107.append(getFallbackContentDate());
        outline107.append(", conflictResolution=");
        outline107.append(getConflictResolution());
        outline107.append(", suppress=");
        outline107.append(getSuppress());
        outline107.append(", kind=");
        outline107.append(getKind());
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
        outline107.append(", contentSignature=");
        outline107.append(getContentSignature());
        outline107.append(", contentSignatureType=");
        outline107.append(getContentSignatureType());
        outline107.append(", accessRuleIds=");
        outline107.append(getAccessRuleIds());
        outline107.append(")");
        return outline107.toString();
    }
}
