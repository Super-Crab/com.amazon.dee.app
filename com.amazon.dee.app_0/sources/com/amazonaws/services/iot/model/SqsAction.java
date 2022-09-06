package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class SqsAction implements Serializable {
    private String queueUrl;
    private String roleArn;
    private Boolean useBase64;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SqsAction)) {
            return false;
        }
        SqsAction sqsAction = (SqsAction) obj;
        if ((sqsAction.getRoleArn() == null) ^ (getRoleArn() == null)) {
            return false;
        }
        if (sqsAction.getRoleArn() != null && !sqsAction.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        if ((sqsAction.getQueueUrl() == null) ^ (getQueueUrl() == null)) {
            return false;
        }
        if (sqsAction.getQueueUrl() != null && !sqsAction.getQueueUrl().equals(getQueueUrl())) {
            return false;
        }
        if ((sqsAction.getUseBase64() == null) ^ (getUseBase64() == null)) {
            return false;
        }
        return sqsAction.getUseBase64() == null || sqsAction.getUseBase64().equals(getUseBase64());
    }

    public String getQueueUrl() {
        return this.queueUrl;
    }

    public String getRoleArn() {
        return this.roleArn;
    }

    public Boolean getUseBase64() {
        return this.useBase64;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31) + (getQueueUrl() == null ? 0 : getQueueUrl().hashCode())) * 31;
        if (getUseBase64() != null) {
            i = getUseBase64().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isUseBase64() {
        return this.useBase64;
    }

    public void setQueueUrl(String str) {
        this.queueUrl = str;
    }

    public void setRoleArn(String str) {
        this.roleArn = str;
    }

    public void setUseBase64(Boolean bool) {
        this.useBase64 = bool;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getRoleArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("roleArn: ");
            outline1072.append(getRoleArn());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getQueueUrl() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("queueUrl: ");
            outline1073.append(getQueueUrl());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getUseBase64() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("useBase64: ");
            outline1074.append(getUseBase64());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public SqsAction withQueueUrl(String str) {
        this.queueUrl = str;
        return this;
    }

    public SqsAction withRoleArn(String str) {
        this.roleArn = str;
        return this;
    }

    public SqsAction withUseBase64(Boolean bool) {
        this.useBase64 = bool;
        return this;
    }
}
