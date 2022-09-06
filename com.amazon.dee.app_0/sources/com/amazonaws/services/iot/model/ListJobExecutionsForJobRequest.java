package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class ListJobExecutionsForJobRequest extends AmazonWebServiceRequest implements Serializable {
    private String jobId;
    private Integer maxResults;
    private String nextToken;
    private String status;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListJobExecutionsForJobRequest)) {
            return false;
        }
        ListJobExecutionsForJobRequest listJobExecutionsForJobRequest = (ListJobExecutionsForJobRequest) obj;
        if ((listJobExecutionsForJobRequest.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        if (listJobExecutionsForJobRequest.getJobId() != null && !listJobExecutionsForJobRequest.getJobId().equals(getJobId())) {
            return false;
        }
        if ((listJobExecutionsForJobRequest.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (listJobExecutionsForJobRequest.getStatus() != null && !listJobExecutionsForJobRequest.getStatus().equals(getStatus())) {
            return false;
        }
        if ((listJobExecutionsForJobRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (listJobExecutionsForJobRequest.getMaxResults() != null && !listJobExecutionsForJobRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((listJobExecutionsForJobRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listJobExecutionsForJobRequest.getNextToken() == null || listJobExecutionsForJobRequest.getNextToken().equals(getNextToken());
    }

    public String getJobId() {
        return this.jobId;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getStatus() {
        return this.status;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getJobId() == null ? 0 : getJobId().hashCode()) + 31) * 31) + (getStatus() == null ? 0 : getStatus().hashCode())) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setJobId(String str) {
        this.jobId = str;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getJobId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("jobId: ");
            outline1072.append(getJobId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getStatus() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("status: ");
            outline1073.append(getStatus());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getMaxResults() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("maxResults: ");
            outline1074.append(getMaxResults());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1075.append(getNextToken());
            outline107.append(outline1075.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListJobExecutionsForJobRequest withJobId(String str) {
        this.jobId = str;
        return this;
    }

    public ListJobExecutionsForJobRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public ListJobExecutionsForJobRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListJobExecutionsForJobRequest withStatus(String str) {
        this.status = str;
        return this;
    }

    public void setStatus(JobExecutionStatus jobExecutionStatus) {
        this.status = jobExecutionStatus.toString();
    }

    public ListJobExecutionsForJobRequest withStatus(JobExecutionStatus jobExecutionStatus) {
        this.status = jobExecutionStatus.toString();
        return this;
    }
}
