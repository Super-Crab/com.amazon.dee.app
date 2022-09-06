package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class PolicyVersion implements Serializable {
    private Date createDate;
    private Boolean isDefaultVersion;
    private String versionId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PolicyVersion)) {
            return false;
        }
        PolicyVersion policyVersion = (PolicyVersion) obj;
        if ((policyVersion.getVersionId() == null) ^ (getVersionId() == null)) {
            return false;
        }
        if (policyVersion.getVersionId() != null && !policyVersion.getVersionId().equals(getVersionId())) {
            return false;
        }
        if ((policyVersion.getIsDefaultVersion() == null) ^ (getIsDefaultVersion() == null)) {
            return false;
        }
        if (policyVersion.getIsDefaultVersion() != null && !policyVersion.getIsDefaultVersion().equals(getIsDefaultVersion())) {
            return false;
        }
        if ((policyVersion.getCreateDate() == null) ^ (getCreateDate() == null)) {
            return false;
        }
        return policyVersion.getCreateDate() == null || policyVersion.getCreateDate().equals(getCreateDate());
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public Boolean getIsDefaultVersion() {
        return this.isDefaultVersion;
    }

    public String getVersionId() {
        return this.versionId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getVersionId() == null ? 0 : getVersionId().hashCode()) + 31) * 31) + (getIsDefaultVersion() == null ? 0 : getIsDefaultVersion().hashCode())) * 31;
        if (getCreateDate() != null) {
            i = getCreateDate().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isIsDefaultVersion() {
        return this.isDefaultVersion;
    }

    public void setCreateDate(Date date) {
        this.createDate = date;
    }

    public void setIsDefaultVersion(Boolean bool) {
        this.isDefaultVersion = bool;
    }

    public void setVersionId(String str) {
        this.versionId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getVersionId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("versionId: ");
            outline1072.append(getVersionId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getIsDefaultVersion() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("isDefaultVersion: ");
            outline1073.append(getIsDefaultVersion());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getCreateDate() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("createDate: ");
            outline1074.append(getCreateDate());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public PolicyVersion withCreateDate(Date date) {
        this.createDate = date;
        return this;
    }

    public PolicyVersion withIsDefaultVersion(Boolean bool) {
        this.isDefaultVersion = bool;
        return this;
    }

    public PolicyVersion withVersionId(String str) {
        this.versionId = str;
        return this;
    }
}
