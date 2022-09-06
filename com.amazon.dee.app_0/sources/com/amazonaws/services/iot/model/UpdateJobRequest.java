package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class UpdateJobRequest extends AmazonWebServiceRequest implements Serializable {
    private AbortConfig abortConfig;
    private String description;
    private JobExecutionsRolloutConfig jobExecutionsRolloutConfig;
    private String jobId;
    private PresignedUrlConfig presignedUrlConfig;
    private TimeoutConfig timeoutConfig;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateJobRequest)) {
            return false;
        }
        UpdateJobRequest updateJobRequest = (UpdateJobRequest) obj;
        if ((updateJobRequest.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        if (updateJobRequest.getJobId() != null && !updateJobRequest.getJobId().equals(getJobId())) {
            return false;
        }
        if ((updateJobRequest.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (updateJobRequest.getDescription() != null && !updateJobRequest.getDescription().equals(getDescription())) {
            return false;
        }
        if ((updateJobRequest.getPresignedUrlConfig() == null) ^ (getPresignedUrlConfig() == null)) {
            return false;
        }
        if (updateJobRequest.getPresignedUrlConfig() != null && !updateJobRequest.getPresignedUrlConfig().equals(getPresignedUrlConfig())) {
            return false;
        }
        if ((updateJobRequest.getJobExecutionsRolloutConfig() == null) ^ (getJobExecutionsRolloutConfig() == null)) {
            return false;
        }
        if (updateJobRequest.getJobExecutionsRolloutConfig() != null && !updateJobRequest.getJobExecutionsRolloutConfig().equals(getJobExecutionsRolloutConfig())) {
            return false;
        }
        if ((updateJobRequest.getAbortConfig() == null) ^ (getAbortConfig() == null)) {
            return false;
        }
        if (updateJobRequest.getAbortConfig() != null && !updateJobRequest.getAbortConfig().equals(getAbortConfig())) {
            return false;
        }
        if ((updateJobRequest.getTimeoutConfig() == null) ^ (getTimeoutConfig() == null)) {
            return false;
        }
        return updateJobRequest.getTimeoutConfig() == null || updateJobRequest.getTimeoutConfig().equals(getTimeoutConfig());
    }

    public AbortConfig getAbortConfig() {
        return this.abortConfig;
    }

    public String getDescription() {
        return this.description;
    }

    public JobExecutionsRolloutConfig getJobExecutionsRolloutConfig() {
        return this.jobExecutionsRolloutConfig;
    }

    public String getJobId() {
        return this.jobId;
    }

    public PresignedUrlConfig getPresignedUrlConfig() {
        return this.presignedUrlConfig;
    }

    public TimeoutConfig getTimeoutConfig() {
        return this.timeoutConfig;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((getJobId() == null ? 0 : getJobId().hashCode()) + 31) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getPresignedUrlConfig() == null ? 0 : getPresignedUrlConfig().hashCode())) * 31) + (getJobExecutionsRolloutConfig() == null ? 0 : getJobExecutionsRolloutConfig().hashCode())) * 31) + (getAbortConfig() == null ? 0 : getAbortConfig().hashCode())) * 31;
        if (getTimeoutConfig() != null) {
            i = getTimeoutConfig().hashCode();
        }
        return hashCode + i;
    }

    public void setAbortConfig(AbortConfig abortConfig) {
        this.abortConfig = abortConfig;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setJobExecutionsRolloutConfig(JobExecutionsRolloutConfig jobExecutionsRolloutConfig) {
        this.jobExecutionsRolloutConfig = jobExecutionsRolloutConfig;
    }

    public void setJobId(String str) {
        this.jobId = str;
    }

    public void setPresignedUrlConfig(PresignedUrlConfig presignedUrlConfig) {
        this.presignedUrlConfig = presignedUrlConfig;
    }

    public void setTimeoutConfig(TimeoutConfig timeoutConfig) {
        this.timeoutConfig = timeoutConfig;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getJobId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("jobId: ");
            outline1072.append(getJobId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getDescription() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("description: ");
            outline1073.append(getDescription());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getPresignedUrlConfig() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("presignedUrlConfig: ");
            outline1074.append(getPresignedUrlConfig());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getJobExecutionsRolloutConfig() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("jobExecutionsRolloutConfig: ");
            outline1075.append(getJobExecutionsRolloutConfig());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getAbortConfig() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("abortConfig: ");
            outline1076.append(getAbortConfig());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getTimeoutConfig() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("timeoutConfig: ");
            outline1077.append(getTimeoutConfig());
            outline107.append(outline1077.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public UpdateJobRequest withAbortConfig(AbortConfig abortConfig) {
        this.abortConfig = abortConfig;
        return this;
    }

    public UpdateJobRequest withDescription(String str) {
        this.description = str;
        return this;
    }

    public UpdateJobRequest withJobExecutionsRolloutConfig(JobExecutionsRolloutConfig jobExecutionsRolloutConfig) {
        this.jobExecutionsRolloutConfig = jobExecutionsRolloutConfig;
        return this;
    }

    public UpdateJobRequest withJobId(String str) {
        this.jobId = str;
        return this;
    }

    public UpdateJobRequest withPresignedUrlConfig(PresignedUrlConfig presignedUrlConfig) {
        this.presignedUrlConfig = presignedUrlConfig;
        return this;
    }

    public UpdateJobRequest withTimeoutConfig(TimeoutConfig timeoutConfig) {
        this.timeoutConfig = timeoutConfig;
        return this;
    }
}
