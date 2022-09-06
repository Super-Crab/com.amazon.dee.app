package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class PutRetentionPolicyRequest extends AmazonWebServiceRequest implements Serializable {
    private String logGroupName;
    private Integer retentionInDays;

    public PutRetentionPolicyRequest() {
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PutRetentionPolicyRequest)) {
            return false;
        }
        PutRetentionPolicyRequest putRetentionPolicyRequest = (PutRetentionPolicyRequest) obj;
        if ((putRetentionPolicyRequest.getLogGroupName() == null) ^ (getLogGroupName() == null)) {
            return false;
        }
        if (putRetentionPolicyRequest.getLogGroupName() != null && !putRetentionPolicyRequest.getLogGroupName().equals(getLogGroupName())) {
            return false;
        }
        if ((putRetentionPolicyRequest.getRetentionInDays() == null) ^ (getRetentionInDays() == null)) {
            return false;
        }
        return putRetentionPolicyRequest.getRetentionInDays() == null || putRetentionPolicyRequest.getRetentionInDays().equals(getRetentionInDays());
    }

    public String getLogGroupName() {
        return this.logGroupName;
    }

    public Integer getRetentionInDays() {
        return this.retentionInDays;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getLogGroupName() == null ? 0 : getLogGroupName().hashCode()) + 31) * 31;
        if (getRetentionInDays() != null) {
            i = getRetentionInDays().hashCode();
        }
        return hashCode + i;
    }

    public void setLogGroupName(String str) {
        this.logGroupName = str;
    }

    public void setRetentionInDays(Integer num) {
        this.retentionInDays = num;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getLogGroupName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("logGroupName: ");
            outline1072.append(getLogGroupName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getRetentionInDays() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("retentionInDays: ");
            outline1073.append(getRetentionInDays());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public PutRetentionPolicyRequest withLogGroupName(String str) {
        this.logGroupName = str;
        return this;
    }

    public PutRetentionPolicyRequest withRetentionInDays(Integer num) {
        this.retentionInDays = num;
        return this;
    }

    public PutRetentionPolicyRequest(String str, Integer num) {
        setLogGroupName(str);
        setRetentionInDays(num);
    }
}
