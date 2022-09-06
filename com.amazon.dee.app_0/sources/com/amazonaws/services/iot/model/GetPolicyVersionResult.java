package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class GetPolicyVersionResult implements Serializable {
    private Date creationDate;
    private String generationId;
    private Boolean isDefaultVersion;
    private Date lastModifiedDate;
    private String policyArn;
    private String policyDocument;
    private String policyName;
    private String policyVersionId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetPolicyVersionResult)) {
            return false;
        }
        GetPolicyVersionResult getPolicyVersionResult = (GetPolicyVersionResult) obj;
        if ((getPolicyVersionResult.getPolicyArn() == null) ^ (getPolicyArn() == null)) {
            return false;
        }
        if (getPolicyVersionResult.getPolicyArn() != null && !getPolicyVersionResult.getPolicyArn().equals(getPolicyArn())) {
            return false;
        }
        if ((getPolicyVersionResult.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        if (getPolicyVersionResult.getPolicyName() != null && !getPolicyVersionResult.getPolicyName().equals(getPolicyName())) {
            return false;
        }
        if ((getPolicyVersionResult.getPolicyDocument() == null) ^ (getPolicyDocument() == null)) {
            return false;
        }
        if (getPolicyVersionResult.getPolicyDocument() != null && !getPolicyVersionResult.getPolicyDocument().equals(getPolicyDocument())) {
            return false;
        }
        if ((getPolicyVersionResult.getPolicyVersionId() == null) ^ (getPolicyVersionId() == null)) {
            return false;
        }
        if (getPolicyVersionResult.getPolicyVersionId() != null && !getPolicyVersionResult.getPolicyVersionId().equals(getPolicyVersionId())) {
            return false;
        }
        if ((getPolicyVersionResult.getIsDefaultVersion() == null) ^ (getIsDefaultVersion() == null)) {
            return false;
        }
        if (getPolicyVersionResult.getIsDefaultVersion() != null && !getPolicyVersionResult.getIsDefaultVersion().equals(getIsDefaultVersion())) {
            return false;
        }
        if ((getPolicyVersionResult.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (getPolicyVersionResult.getCreationDate() != null && !getPolicyVersionResult.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((getPolicyVersionResult.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        if (getPolicyVersionResult.getLastModifiedDate() != null && !getPolicyVersionResult.getLastModifiedDate().equals(getLastModifiedDate())) {
            return false;
        }
        if ((getPolicyVersionResult.getGenerationId() == null) ^ (getGenerationId() == null)) {
            return false;
        }
        return getPolicyVersionResult.getGenerationId() == null || getPolicyVersionResult.getGenerationId().equals(getGenerationId());
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public String getGenerationId() {
        return this.generationId;
    }

    public Boolean getIsDefaultVersion() {
        return this.isDefaultVersion;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public String getPolicyArn() {
        return this.policyArn;
    }

    public String getPolicyDocument() {
        return this.policyDocument;
    }

    public String getPolicyName() {
        return this.policyName;
    }

    public String getPolicyVersionId() {
        return this.policyVersionId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((getPolicyArn() == null ? 0 : getPolicyArn().hashCode()) + 31) * 31) + (getPolicyName() == null ? 0 : getPolicyName().hashCode())) * 31) + (getPolicyDocument() == null ? 0 : getPolicyDocument().hashCode())) * 31) + (getPolicyVersionId() == null ? 0 : getPolicyVersionId().hashCode())) * 31) + (getIsDefaultVersion() == null ? 0 : getIsDefaultVersion().hashCode())) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31) + (getLastModifiedDate() == null ? 0 : getLastModifiedDate().hashCode())) * 31;
        if (getGenerationId() != null) {
            i = getGenerationId().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isIsDefaultVersion() {
        return this.isDefaultVersion;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public void setGenerationId(String str) {
        this.generationId = str;
    }

    public void setIsDefaultVersion(Boolean bool) {
        this.isDefaultVersion = bool;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public void setPolicyArn(String str) {
        this.policyArn = str;
    }

    public void setPolicyDocument(String str) {
        this.policyDocument = str;
    }

    public void setPolicyName(String str) {
        this.policyName = str;
    }

    public void setPolicyVersionId(String str) {
        this.policyVersionId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getPolicyArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("policyArn: ");
            outline1072.append(getPolicyArn());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getPolicyName() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("policyName: ");
            outline1073.append(getPolicyName());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getPolicyDocument() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("policyDocument: ");
            outline1074.append(getPolicyDocument());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getPolicyVersionId() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("policyVersionId: ");
            outline1075.append(getPolicyVersionId());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getIsDefaultVersion() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("isDefaultVersion: ");
            outline1076.append(getIsDefaultVersion());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getCreationDate() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("creationDate: ");
            outline1077.append(getCreationDate());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getLastModifiedDate() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("lastModifiedDate: ");
            outline1078.append(getLastModifiedDate());
            outline1078.append(",");
            outline107.append(outline1078.toString());
        }
        if (getGenerationId() != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("generationId: ");
            outline1079.append(getGenerationId());
            outline107.append(outline1079.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetPolicyVersionResult withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public GetPolicyVersionResult withGenerationId(String str) {
        this.generationId = str;
        return this;
    }

    public GetPolicyVersionResult withIsDefaultVersion(Boolean bool) {
        this.isDefaultVersion = bool;
        return this;
    }

    public GetPolicyVersionResult withLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
        return this;
    }

    public GetPolicyVersionResult withPolicyArn(String str) {
        this.policyArn = str;
        return this;
    }

    public GetPolicyVersionResult withPolicyDocument(String str) {
        this.policyDocument = str;
        return this;
    }

    public GetPolicyVersionResult withPolicyName(String str) {
        this.policyName = str;
        return this;
    }

    public GetPolicyVersionResult withPolicyVersionId(String str) {
        this.policyVersionId = str;
        return this;
    }
}
