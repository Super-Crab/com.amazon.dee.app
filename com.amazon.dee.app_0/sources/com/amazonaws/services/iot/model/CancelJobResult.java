package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CancelJobResult implements Serializable {
    private String description;
    private String jobArn;
    private String jobId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CancelJobResult)) {
            return false;
        }
        CancelJobResult cancelJobResult = (CancelJobResult) obj;
        if ((cancelJobResult.getJobArn() == null) ^ (getJobArn() == null)) {
            return false;
        }
        if (cancelJobResult.getJobArn() != null && !cancelJobResult.getJobArn().equals(getJobArn())) {
            return false;
        }
        if ((cancelJobResult.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        if (cancelJobResult.getJobId() != null && !cancelJobResult.getJobId().equals(getJobId())) {
            return false;
        }
        if ((cancelJobResult.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        return cancelJobResult.getDescription() == null || cancelJobResult.getDescription().equals(getDescription());
    }

    public String getDescription() {
        return this.description;
    }

    public String getJobArn() {
        return this.jobArn;
    }

    public String getJobId() {
        return this.jobId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getJobArn() == null ? 0 : getJobArn().hashCode()) + 31) * 31) + (getJobId() == null ? 0 : getJobId().hashCode())) * 31;
        if (getDescription() != null) {
            i = getDescription().hashCode();
        }
        return hashCode + i;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setJobArn(String str) {
        this.jobArn = str;
    }

    public void setJobId(String str) {
        this.jobId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getJobArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("jobArn: ");
            outline1072.append(getJobArn());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getJobId() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("jobId: ");
            outline1073.append(getJobId());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getDescription() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("description: ");
            outline1074.append(getDescription());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CancelJobResult withDescription(String str) {
        this.description = str;
        return this;
    }

    public CancelJobResult withJobArn(String str) {
        this.jobArn = str;
        return this;
    }

    public CancelJobResult withJobId(String str) {
        this.jobId = str;
        return this;
    }
}
