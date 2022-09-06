package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class GetPolicyResult implements Serializable {
    private Date creationDate;
    private String defaultVersionId;
    private String generationId;
    private Date lastModifiedDate;
    private String policyArn;
    private String policyDocument;
    private String policyName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetPolicyResult)) {
            return false;
        }
        GetPolicyResult getPolicyResult = (GetPolicyResult) obj;
        if ((getPolicyResult.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        if (getPolicyResult.getPolicyName() != null && !getPolicyResult.getPolicyName().equals(getPolicyName())) {
            return false;
        }
        if ((getPolicyResult.getPolicyArn() == null) ^ (getPolicyArn() == null)) {
            return false;
        }
        if (getPolicyResult.getPolicyArn() != null && !getPolicyResult.getPolicyArn().equals(getPolicyArn())) {
            return false;
        }
        if ((getPolicyResult.getPolicyDocument() == null) ^ (getPolicyDocument() == null)) {
            return false;
        }
        if (getPolicyResult.getPolicyDocument() != null && !getPolicyResult.getPolicyDocument().equals(getPolicyDocument())) {
            return false;
        }
        if ((getPolicyResult.getDefaultVersionId() == null) ^ (getDefaultVersionId() == null)) {
            return false;
        }
        if (getPolicyResult.getDefaultVersionId() != null && !getPolicyResult.getDefaultVersionId().equals(getDefaultVersionId())) {
            return false;
        }
        if ((getPolicyResult.getCreationDate() == null) ^ (getCreationDate() == null)) {
            return false;
        }
        if (getPolicyResult.getCreationDate() != null && !getPolicyResult.getCreationDate().equals(getCreationDate())) {
            return false;
        }
        if ((getPolicyResult.getLastModifiedDate() == null) ^ (getLastModifiedDate() == null)) {
            return false;
        }
        if (getPolicyResult.getLastModifiedDate() != null && !getPolicyResult.getLastModifiedDate().equals(getLastModifiedDate())) {
            return false;
        }
        if ((getPolicyResult.getGenerationId() == null) ^ (getGenerationId() == null)) {
            return false;
        }
        return getPolicyResult.getGenerationId() == null || getPolicyResult.getGenerationId().equals(getGenerationId());
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public String getDefaultVersionId() {
        return this.defaultVersionId;
    }

    public String getGenerationId() {
        return this.generationId;
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

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((getPolicyName() == null ? 0 : getPolicyName().hashCode()) + 31) * 31) + (getPolicyArn() == null ? 0 : getPolicyArn().hashCode())) * 31) + (getPolicyDocument() == null ? 0 : getPolicyDocument().hashCode())) * 31) + (getDefaultVersionId() == null ? 0 : getDefaultVersionId().hashCode())) * 31) + (getCreationDate() == null ? 0 : getCreationDate().hashCode())) * 31) + (getLastModifiedDate() == null ? 0 : getLastModifiedDate().hashCode())) * 31;
        if (getGenerationId() != null) {
            i = getGenerationId().hashCode();
        }
        return hashCode + i;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public void setDefaultVersionId(String str) {
        this.defaultVersionId = str;
    }

    public void setGenerationId(String str) {
        this.generationId = str;
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

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getPolicyName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("policyName: ");
            outline1072.append(getPolicyName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getPolicyArn() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("policyArn: ");
            outline1073.append(getPolicyArn());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getPolicyDocument() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("policyDocument: ");
            outline1074.append(getPolicyDocument());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getDefaultVersionId() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("defaultVersionId: ");
            outline1075.append(getDefaultVersionId());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getCreationDate() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("creationDate: ");
            outline1076.append(getCreationDate());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getLastModifiedDate() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("lastModifiedDate: ");
            outline1077.append(getLastModifiedDate());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getGenerationId() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("generationId: ");
            outline1078.append(getGenerationId());
            outline107.append(outline1078.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public GetPolicyResult withCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

    public GetPolicyResult withDefaultVersionId(String str) {
        this.defaultVersionId = str;
        return this;
    }

    public GetPolicyResult withGenerationId(String str) {
        this.generationId = str;
        return this;
    }

    public GetPolicyResult withLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
        return this;
    }

    public GetPolicyResult withPolicyArn(String str) {
        this.policyArn = str;
        return this;
    }

    public GetPolicyResult withPolicyDocument(String str) {
        this.policyDocument = str;
        return this;
    }

    public GetPolicyResult withPolicyName(String str) {
        this.policyName = str;
        return this;
    }
}
