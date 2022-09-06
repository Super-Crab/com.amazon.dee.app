package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class JobSummary implements Serializable {
    private Date completedAt;
    private Date createdAt;
    private String jobArn;
    private String jobId;
    private Date lastUpdatedAt;
    private String status;
    private String targetSelection;
    private String thingGroupId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof JobSummary)) {
            return false;
        }
        JobSummary jobSummary = (JobSummary) obj;
        if ((jobSummary.getJobArn() == null) ^ (getJobArn() == null)) {
            return false;
        }
        if (jobSummary.getJobArn() != null && !jobSummary.getJobArn().equals(getJobArn())) {
            return false;
        }
        if ((jobSummary.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        if (jobSummary.getJobId() != null && !jobSummary.getJobId().equals(getJobId())) {
            return false;
        }
        if ((jobSummary.getThingGroupId() == null) ^ (getThingGroupId() == null)) {
            return false;
        }
        if (jobSummary.getThingGroupId() != null && !jobSummary.getThingGroupId().equals(getThingGroupId())) {
            return false;
        }
        if ((jobSummary.getTargetSelection() == null) ^ (getTargetSelection() == null)) {
            return false;
        }
        if (jobSummary.getTargetSelection() != null && !jobSummary.getTargetSelection().equals(getTargetSelection())) {
            return false;
        }
        if ((jobSummary.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (jobSummary.getStatus() != null && !jobSummary.getStatus().equals(getStatus())) {
            return false;
        }
        if ((jobSummary.getCreatedAt() == null) ^ (getCreatedAt() == null)) {
            return false;
        }
        if (jobSummary.getCreatedAt() != null && !jobSummary.getCreatedAt().equals(getCreatedAt())) {
            return false;
        }
        if ((jobSummary.getLastUpdatedAt() == null) ^ (getLastUpdatedAt() == null)) {
            return false;
        }
        if (jobSummary.getLastUpdatedAt() != null && !jobSummary.getLastUpdatedAt().equals(getLastUpdatedAt())) {
            return false;
        }
        if ((jobSummary.getCompletedAt() == null) ^ (getCompletedAt() == null)) {
            return false;
        }
        return jobSummary.getCompletedAt() == null || jobSummary.getCompletedAt().equals(getCompletedAt());
    }

    public Date getCompletedAt() {
        return this.completedAt;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public String getJobArn() {
        return this.jobArn;
    }

    public String getJobId() {
        return this.jobId;
    }

    public Date getLastUpdatedAt() {
        return this.lastUpdatedAt;
    }

    public String getStatus() {
        return this.status;
    }

    public String getTargetSelection() {
        return this.targetSelection;
    }

    public String getThingGroupId() {
        return this.thingGroupId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((getJobArn() == null ? 0 : getJobArn().hashCode()) + 31) * 31) + (getJobId() == null ? 0 : getJobId().hashCode())) * 31) + (getThingGroupId() == null ? 0 : getThingGroupId().hashCode())) * 31) + (getTargetSelection() == null ? 0 : getTargetSelection().hashCode())) * 31) + (getStatus() == null ? 0 : getStatus().hashCode())) * 31) + (getCreatedAt() == null ? 0 : getCreatedAt().hashCode())) * 31) + (getLastUpdatedAt() == null ? 0 : getLastUpdatedAt().hashCode())) * 31;
        if (getCompletedAt() != null) {
            i = getCompletedAt().hashCode();
        }
        return hashCode + i;
    }

    public void setCompletedAt(Date date) {
        this.completedAt = date;
    }

    public void setCreatedAt(Date date) {
        this.createdAt = date;
    }

    public void setJobArn(String str) {
        this.jobArn = str;
    }

    public void setJobId(String str) {
        this.jobId = str;
    }

    public void setLastUpdatedAt(Date date) {
        this.lastUpdatedAt = date;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setTargetSelection(String str) {
        this.targetSelection = str;
    }

    public void setThingGroupId(String str) {
        this.thingGroupId = str;
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
        if (getThingGroupId() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("thingGroupId: ");
            outline1074.append(getThingGroupId());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getTargetSelection() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("targetSelection: ");
            outline1075.append(getTargetSelection());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getStatus() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("status: ");
            outline1076.append(getStatus());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getCreatedAt() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("createdAt: ");
            outline1077.append(getCreatedAt());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getLastUpdatedAt() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("lastUpdatedAt: ");
            outline1078.append(getLastUpdatedAt());
            outline1078.append(",");
            outline107.append(outline1078.toString());
        }
        if (getCompletedAt() != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("completedAt: ");
            outline1079.append(getCompletedAt());
            outline107.append(outline1079.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public JobSummary withCompletedAt(Date date) {
        this.completedAt = date;
        return this;
    }

    public JobSummary withCreatedAt(Date date) {
        this.createdAt = date;
        return this;
    }

    public JobSummary withJobArn(String str) {
        this.jobArn = str;
        return this;
    }

    public JobSummary withJobId(String str) {
        this.jobId = str;
        return this;
    }

    public JobSummary withLastUpdatedAt(Date date) {
        this.lastUpdatedAt = date;
        return this;
    }

    public JobSummary withStatus(String str) {
        this.status = str;
        return this;
    }

    public JobSummary withTargetSelection(String str) {
        this.targetSelection = str;
        return this;
    }

    public JobSummary withThingGroupId(String str) {
        this.thingGroupId = str;
        return this;
    }

    public void setStatus(JobStatus jobStatus) {
        this.status = jobStatus.toString();
    }

    public void setTargetSelection(TargetSelection targetSelection) {
        this.targetSelection = targetSelection.toString();
    }

    public JobSummary withStatus(JobStatus jobStatus) {
        this.status = jobStatus.toString();
        return this;
    }

    public JobSummary withTargetSelection(TargetSelection targetSelection) {
        this.targetSelection = targetSelection.toString();
        return this;
    }
}
