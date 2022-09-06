package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ResourcePolicy implements Serializable {
    private Long lastUpdatedTime;
    private String policyDocument;
    private String policyName;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ResourcePolicy)) {
            return false;
        }
        ResourcePolicy resourcePolicy = (ResourcePolicy) obj;
        if ((resourcePolicy.getPolicyName() == null) ^ (getPolicyName() == null)) {
            return false;
        }
        if (resourcePolicy.getPolicyName() != null && !resourcePolicy.getPolicyName().equals(getPolicyName())) {
            return false;
        }
        if ((resourcePolicy.getPolicyDocument() == null) ^ (getPolicyDocument() == null)) {
            return false;
        }
        if (resourcePolicy.getPolicyDocument() != null && !resourcePolicy.getPolicyDocument().equals(getPolicyDocument())) {
            return false;
        }
        if ((resourcePolicy.getLastUpdatedTime() == null) ^ (getLastUpdatedTime() == null)) {
            return false;
        }
        return resourcePolicy.getLastUpdatedTime() == null || resourcePolicy.getLastUpdatedTime().equals(getLastUpdatedTime());
    }

    public Long getLastUpdatedTime() {
        return this.lastUpdatedTime;
    }

    public String getPolicyDocument() {
        return this.policyDocument;
    }

    public String getPolicyName() {
        return this.policyName;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getPolicyName() == null ? 0 : getPolicyName().hashCode()) + 31) * 31) + (getPolicyDocument() == null ? 0 : getPolicyDocument().hashCode())) * 31;
        if (getLastUpdatedTime() != null) {
            i = getLastUpdatedTime().hashCode();
        }
        return hashCode + i;
    }

    public void setLastUpdatedTime(Long l) {
        this.lastUpdatedTime = l;
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
        if (getPolicyDocument() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("policyDocument: ");
            outline1073.append(getPolicyDocument());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getLastUpdatedTime() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("lastUpdatedTime: ");
            outline1074.append(getLastUpdatedTime());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ResourcePolicy withLastUpdatedTime(Long l) {
        this.lastUpdatedTime = l;
        return this;
    }

    public ResourcePolicy withPolicyDocument(String str) {
        this.policyDocument = str;
        return this;
    }

    public ResourcePolicy withPolicyName(String str) {
        this.policyName = str;
        return this;
    }
}
