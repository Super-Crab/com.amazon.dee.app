package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.Date;
/* loaded from: classes13.dex */
public class JobExecution implements Serializable {
    private Long approximateSecondsBeforeTimedOut;
    private Long executionNumber;
    private Boolean forceCanceled;
    private String jobId;
    private Date lastUpdatedAt;
    private Date queuedAt;
    private Date startedAt;
    private String status;
    private JobExecutionStatusDetails statusDetails;
    private String thingArn;
    private Long versionNumber;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof JobExecution)) {
            return false;
        }
        JobExecution jobExecution = (JobExecution) obj;
        if ((jobExecution.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        if (jobExecution.getJobId() != null && !jobExecution.getJobId().equals(getJobId())) {
            return false;
        }
        if ((jobExecution.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (jobExecution.getStatus() != null && !jobExecution.getStatus().equals(getStatus())) {
            return false;
        }
        if ((jobExecution.getForceCanceled() == null) ^ (getForceCanceled() == null)) {
            return false;
        }
        if (jobExecution.getForceCanceled() != null && !jobExecution.getForceCanceled().equals(getForceCanceled())) {
            return false;
        }
        if ((jobExecution.getStatusDetails() == null) ^ (getStatusDetails() == null)) {
            return false;
        }
        if (jobExecution.getStatusDetails() != null && !jobExecution.getStatusDetails().equals(getStatusDetails())) {
            return false;
        }
        if ((jobExecution.getThingArn() == null) ^ (getThingArn() == null)) {
            return false;
        }
        if (jobExecution.getThingArn() != null && !jobExecution.getThingArn().equals(getThingArn())) {
            return false;
        }
        if ((jobExecution.getQueuedAt() == null) ^ (getQueuedAt() == null)) {
            return false;
        }
        if (jobExecution.getQueuedAt() != null && !jobExecution.getQueuedAt().equals(getQueuedAt())) {
            return false;
        }
        if ((jobExecution.getStartedAt() == null) ^ (getStartedAt() == null)) {
            return false;
        }
        if (jobExecution.getStartedAt() != null && !jobExecution.getStartedAt().equals(getStartedAt())) {
            return false;
        }
        if ((jobExecution.getLastUpdatedAt() == null) ^ (getLastUpdatedAt() == null)) {
            return false;
        }
        if (jobExecution.getLastUpdatedAt() != null && !jobExecution.getLastUpdatedAt().equals(getLastUpdatedAt())) {
            return false;
        }
        if ((jobExecution.getExecutionNumber() == null) ^ (getExecutionNumber() == null)) {
            return false;
        }
        if (jobExecution.getExecutionNumber() != null && !jobExecution.getExecutionNumber().equals(getExecutionNumber())) {
            return false;
        }
        if ((jobExecution.getVersionNumber() == null) ^ (getVersionNumber() == null)) {
            return false;
        }
        if (jobExecution.getVersionNumber() != null && !jobExecution.getVersionNumber().equals(getVersionNumber())) {
            return false;
        }
        if ((jobExecution.getApproximateSecondsBeforeTimedOut() == null) ^ (getApproximateSecondsBeforeTimedOut() == null)) {
            return false;
        }
        return jobExecution.getApproximateSecondsBeforeTimedOut() == null || jobExecution.getApproximateSecondsBeforeTimedOut().equals(getApproximateSecondsBeforeTimedOut());
    }

    public Long getApproximateSecondsBeforeTimedOut() {
        return this.approximateSecondsBeforeTimedOut;
    }

    public Long getExecutionNumber() {
        return this.executionNumber;
    }

    public Boolean getForceCanceled() {
        return this.forceCanceled;
    }

    public String getJobId() {
        return this.jobId;
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

    public JobExecutionStatusDetails getStatusDetails() {
        return this.statusDetails;
    }

    public String getThingArn() {
        return this.thingArn;
    }

    public Long getVersionNumber() {
        return this.versionNumber;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((((((((getJobId() == null ? 0 : getJobId().hashCode()) + 31) * 31) + (getStatus() == null ? 0 : getStatus().hashCode())) * 31) + (getForceCanceled() == null ? 0 : getForceCanceled().hashCode())) * 31) + (getStatusDetails() == null ? 0 : getStatusDetails().hashCode())) * 31) + (getThingArn() == null ? 0 : getThingArn().hashCode())) * 31) + (getQueuedAt() == null ? 0 : getQueuedAt().hashCode())) * 31) + (getStartedAt() == null ? 0 : getStartedAt().hashCode())) * 31) + (getLastUpdatedAt() == null ? 0 : getLastUpdatedAt().hashCode())) * 31) + (getExecutionNumber() == null ? 0 : getExecutionNumber().hashCode())) * 31) + (getVersionNumber() == null ? 0 : getVersionNumber().hashCode())) * 31;
        if (getApproximateSecondsBeforeTimedOut() != null) {
            i = getApproximateSecondsBeforeTimedOut().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isForceCanceled() {
        return this.forceCanceled;
    }

    public void setApproximateSecondsBeforeTimedOut(Long l) {
        this.approximateSecondsBeforeTimedOut = l;
    }

    public void setExecutionNumber(Long l) {
        this.executionNumber = l;
    }

    public void setForceCanceled(Boolean bool) {
        this.forceCanceled = bool;
    }

    public void setJobId(String str) {
        this.jobId = str;
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

    public void setStatusDetails(JobExecutionStatusDetails jobExecutionStatusDetails) {
        this.statusDetails = jobExecutionStatusDetails;
    }

    public void setThingArn(String str) {
        this.thingArn = str;
    }

    public void setVersionNumber(Long l) {
        this.versionNumber = l;
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
        if (getForceCanceled() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("forceCanceled: ");
            outline1074.append(getForceCanceled());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getStatusDetails() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("statusDetails: ");
            outline1075.append(getStatusDetails());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getThingArn() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("thingArn: ");
            outline1076.append(getThingArn());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getQueuedAt() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("queuedAt: ");
            outline1077.append(getQueuedAt());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getStartedAt() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("startedAt: ");
            outline1078.append(getStartedAt());
            outline1078.append(",");
            outline107.append(outline1078.toString());
        }
        if (getLastUpdatedAt() != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("lastUpdatedAt: ");
            outline1079.append(getLastUpdatedAt());
            outline1079.append(",");
            outline107.append(outline1079.toString());
        }
        if (getExecutionNumber() != null) {
            StringBuilder outline10710 = GeneratedOutlineSupport1.outline107("executionNumber: ");
            outline10710.append(getExecutionNumber());
            outline10710.append(",");
            outline107.append(outline10710.toString());
        }
        if (getVersionNumber() != null) {
            StringBuilder outline10711 = GeneratedOutlineSupport1.outline107("versionNumber: ");
            outline10711.append(getVersionNumber());
            outline10711.append(",");
            outline107.append(outline10711.toString());
        }
        if (getApproximateSecondsBeforeTimedOut() != null) {
            StringBuilder outline10712 = GeneratedOutlineSupport1.outline107("approximateSecondsBeforeTimedOut: ");
            outline10712.append(getApproximateSecondsBeforeTimedOut());
            outline107.append(outline10712.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public JobExecution withApproximateSecondsBeforeTimedOut(Long l) {
        this.approximateSecondsBeforeTimedOut = l;
        return this;
    }

    public JobExecution withExecutionNumber(Long l) {
        this.executionNumber = l;
        return this;
    }

    public JobExecution withForceCanceled(Boolean bool) {
        this.forceCanceled = bool;
        return this;
    }

    public JobExecution withJobId(String str) {
        this.jobId = str;
        return this;
    }

    public JobExecution withLastUpdatedAt(Date date) {
        this.lastUpdatedAt = date;
        return this;
    }

    public JobExecution withQueuedAt(Date date) {
        this.queuedAt = date;
        return this;
    }

    public JobExecution withStartedAt(Date date) {
        this.startedAt = date;
        return this;
    }

    public JobExecution withStatus(String str) {
        this.status = str;
        return this;
    }

    public JobExecution withStatusDetails(JobExecutionStatusDetails jobExecutionStatusDetails) {
        this.statusDetails = jobExecutionStatusDetails;
        return this;
    }

    public JobExecution withThingArn(String str) {
        this.thingArn = str;
        return this;
    }

    public JobExecution withVersionNumber(Long l) {
        this.versionNumber = l;
        return this;
    }

    public void setStatus(JobExecutionStatus jobExecutionStatus) {
        this.status = jobExecutionStatus.toString();
    }

    public JobExecution withStatus(JobExecutionStatus jobExecutionStatus) {
        this.status = jobExecutionStatus.toString();
        return this;
    }
}
