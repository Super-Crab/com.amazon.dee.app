package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class CreateJobResult implements Serializable {
    private String description;
    private String jobArn;
    private String jobId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateJobResult)) {
            return false;
        }
        CreateJobResult createJobResult = (CreateJobResult) obj;
        if ((createJobResult.getJobArn() == null) ^ (getJobArn() == null)) {
            return false;
        }
        if (createJobResult.getJobArn() != null && !createJobResult.getJobArn().equals(getJobArn())) {
            return false;
        }
        if ((createJobResult.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        if (createJobResult.getJobId() != null && !createJobResult.getJobId().equals(getJobId())) {
            return false;
        }
        if ((createJobResult.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        return createJobResult.getDescription() == null || createJobResult.getDescription().equals(getDescription());
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

    public CreateJobResult withDescription(String str) {
        this.description = str;
        return this;
    }

    public CreateJobResult withJobArn(String str) {
        this.jobArn = str;
        return this;
    }

    public CreateJobResult withJobId(String str) {
        this.jobId = str;
        return this;
    }
}
