package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class JobExecutionSummary implements Serializable {
    private Long executionNumber;
    private Date lastUpdatedAt;
    private Date queuedAt;
    private Date startedAt;
    private String status;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof JobExecutionSummary)) {
            return false;
        }
        JobExecutionSummary jobExecutionSummary = (JobExecutionSummary) obj;
        if ((jobExecutionSummary.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (jobExecutionSummary.getStatus() != null && !jobExecutionSummary.getStatus().equals(getStatus())) {
            return false;
        }
        if ((jobExecutionSummary.getQueuedAt() == null) ^ (getQueuedAt() == null)) {
            return false;
        }
        if (jobExecutionSummary.getQueuedAt() != null && !jobExecutionSummary.getQueuedAt().equals(getQueuedAt())) {
            return false;
        }
        if ((jobExecutionSummary.getStartedAt() == null) ^ (getStartedAt() == null)) {
            return false;
        }
        if (jobExecutionSummary.getStartedAt() != null && !jobExecutionSummary.getStartedAt().equals(getStartedAt())) {
            return false;
        }
        if ((jobExecutionSummary.getLastUpdatedAt() == null) ^ (getLastUpdatedAt() == null)) {
            return false;
        }
        if (jobExecutionSummary.getLastUpdatedAt() != null && !jobExecutionSummary.getLastUpdatedAt().equals(getLastUpdatedAt())) {
            return false;
        }
        if ((jobExecutionSummary.getExecutionNumber() == null) ^ (getExecutionNumber() == null)) {
            return false;
        }
        return jobExecutionSummary.getExecutionNumber() == null || jobExecutionSummary.getExecutionNumber().equals(getExecutionNumber());
    }

    public Long getExecutionNumber() {
        return this.executionNumber;
    }

    public Date getLastUpdatedAt() {
        return this.lastUpdatedAt;
    }

    public Date getQueuedAt() {
        return this.queuedAt;
    }

    public Date getStartedAt() {
        return this.startedAt;
    }

    public String getStatus() {
        return this.status;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getStatus() == null ? 0 : getStatus().hashCode()) + 31) * 31) + (getQueuedAt() == null ? 0 : getQueuedAt().hashCode())) * 31) + (getStartedAt() == null ? 0 : getStartedAt().hashCode())) * 31) + (getLastUpdatedAt() == null ? 0 : getLastUpdatedAt().hashCode())) * 31;
        if (getExecutionNumber() != null) {
            i = getExecutionNumber().hashCode();
        }
        return hashCode + i;
    }

    public void setExecutionNumber(Long l) {
        this.executionNumber = l;
    }

    public void setLastUpdatedAt(Date date) {
        this.lastUpdatedAt = date;
    }

    public void setQueuedAt(Date date) {
        this.queuedAt = date;
    }

    public void setStartedAt(Date date) {
        this.startedAt = date;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getStatus() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("status: ");
            outline1072.append(getStatus());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getQueuedAt() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("queuedAt: ");
            outline1073.append(getQueuedAt());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getStartedAt() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("startedAt: ");
            outline1074.append(getStartedAt());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getLastUpdatedAt() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("lastUpdatedAt: ");
            outline1075.append(getLastUpdatedAt());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getExecutionNumber() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("executionNumber: ");
            outline1076.append(getExecutionNumber());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public JobExecutionSummary withExecutionNumber(Long l) {
        this.executionNumber = l;
        return this;
    }

    public JobExecutionSummary withLastUpdatedAt(Date date) {
        this.lastUpdatedAt = date;
        return this;
    }

    public JobExecutionSummary withQueuedAt(Date date) {
        this.queuedAt = date;
        return this;
    }

    public JobExecutionSummary withStartedAt(Date date) {
        this.startedAt = date;
        return this;
    }

    public JobExecutionSummary withStatus(String str) {
        this.status = str;
        return this;
    }

    public void setStatus(JobExecutionStatus jobExecutionStatus) {
        this.status = jobExecutionStatus.toString();
    }

    public JobExecutionSummary withStatus(JobExecutionStatus jobExecutionStatus) {
        this.status = jobExecutionStatus.toString();
        return this;
    }
}
