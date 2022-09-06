package com.amazon.clouddrive.cdasdk.cdus;

import androidx.annotation.NonNull;
import com.amazon.clouddrive.cdasdk.cds.common.ContentSignatureType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class UploadContentRequest extends UpdateContentRequest {
    @JsonProperty("contentSignature")
    private String contentSignature;
    @JsonProperty("contentSignatureType")
    private ContentSignatureType contentSignatureType;
    @JsonProperty("fallbackContentDate")
    private String fallbackContentDate;
    @JsonProperty("localId")
    private String localId;
    @JsonProperty("name")
    private String name;

    public UploadContentRequest(@NonNull String str, @NonNull Long l) {
        super(l);
        this.name = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.UpdateContentRequest, com.amazon.clouddrive.cdasdk.cdus.ServiceRequest
    protected boolean canEqual(Object obj) {
        return obj instanceof UploadContentRequest;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.UpdateContentRequest, com.amazon.clouddrive.cdasdk.cdus.ServiceRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UploadContentRequest)) {
            return false;
        }
        UploadContentRequest uploadContentRequest = (UploadContentRequest) obj;
        if (!uploadContentRequest.canEqual(this) || !super.equals(obj)) {
            return false;
        }
        String name = getName();
        String name2 = uploadContentRequest.getName();
        if (name != null ? !name.equals(name2) : name2 != null) {
            return false;
        }
        String fallbackContentDate = getFallbackContentDate();
        String fallbackContentDate2 = uploadContentRequest.getFallbackContentDate();
        if (fallbackContentDate != null ? !fallbackContentDate.equals(fallbackContentDate2) : fallbackContentDate2 != null) {
            return false;
        }
        String localId = getLocalId();
        String localId2 = uploadContentRequest.getLocalId();
        if (localId != null ? !localId.equals(localId2) : localId2 != null) {
            return false;
        }
        String contentSignature = getContentSignature();
        String contentSignature2 = uploadContentRequest.getContentSignature();
        if (contentSignature != null ? !contentSignature.equals(contentSignature2) : contentSignature2 != null) {
            return false;
        }
        ContentSignatureType contentSignatureType = getContentSignatureType();
        ContentSignatureType contentSignatureType2 = uploadContentRequest.getContentSignatureType();
        return contentSignatureType != null ? contentSignatureType.equals(contentSignatureType2) : contentSignatureType2 == null;
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

    public String getLocalId() {
        return this.localId;
    }

    public String getName() {
        return this.name;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.UpdateContentRequest, com.amazon.clouddrive.cdasdk.cdus.ServiceRequest
    public int hashCode() {
        int hashCode = super.hashCode();
        String name = getName();
        int i = 43;
        int hashCode2 = (hashCode * 59) + (name == null ? 43 : name.hashCode());
        String fallbackContentDate = getFallbackContentDate();
        int hashCode3 = (hashCode2 * 59) + (fallbackContentDate == null ? 43 : fallbackContentDate.hashCode());
        String localId = getLocalId();
        int hashCode4 = (hashCode3 * 59) + (localId == null ? 43 : localId.hashCode());
        String contentSignature = getContentSignature();
        int hashCode5 = (hashCode4 * 59) + (contentSignature == null ? 43 : contentSignature.hashCode());
        ContentSignatureType contentSignatureType = getContentSignatureType();
        int i2 = hashCode5 * 59;
        if (contentSignatureType != null) {
            i = contentSignatureType.hashCode();
        }
        return i2 + i;
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

    @JsonProperty("localId")
    public void setLocalId(String str) {
        this.localId = str;
    }

    @JsonProperty("name")
    public void setName(String str) {
        this.name = str;
    }

    @Override // com.amazon.clouddrive.cdasdk.cdus.UpdateContentRequest, com.amazon.clouddrive.cdasdk.cdus.ServiceRequest
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UploadContentRequest(name=");
        outline107.append(getName());
        outline107.append(", fallbackContentDate=");
        outline107.append(getFallbackContentDate());
        outline107.append(", localId=");
        outline107.append(getLocalId());
        outline107.append(", contentSignature=");
        outline107.append(getContentSignature());
        outline107.append(", contentSignatureType=");
        outline107.append(getContentSignatureType());
        outline107.append(")");
        return outline107.toString();
    }
}
