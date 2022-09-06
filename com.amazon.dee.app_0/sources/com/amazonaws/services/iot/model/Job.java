package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
/* loaded from: classes13.dex */
public class Job implements Serializable {
    private AbortConfig abortConfig;
    private String comment;
    private Date completedAt;
    private Date createdAt;
    private String description;
    private Boolean forceCanceled;
    private String jobArn;
    private JobExecutionsRolloutConfig jobExecutionsRolloutConfig;
    private String jobId;
    private JobProcessDetails jobProcessDetails;
    private Date lastUpdatedAt;
    private PresignedUrlConfig presignedUrlConfig;
    private String reasonCode;
    private String status;
    private String targetSelection;
    private List<String> targets;
    private TimeoutConfig timeoutConfig;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Job)) {
            return false;
        }
        Job job = (Job) obj;
        if ((job.getJobArn() == null) ^ (getJobArn() == null)) {
            return false;
        }
        if (job.getJobArn() != null && !job.getJobArn().equals(getJobArn())) {
            return false;
        }
        if ((job.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        if (job.getJobId() != null && !job.getJobId().equals(getJobId())) {
            return false;
        }
        if ((job.getTargetSelection() == null) ^ (getTargetSelection() == null)) {
            return false;
        }
        if (job.getTargetSelection() != null && !job.getTargetSelection().equals(getTargetSelection())) {
            return false;
        }
        if ((job.getStatus() == null) ^ (getStatus() == null)) {
            return false;
        }
        if (job.getStatus() != null && !job.getStatus().equals(getStatus())) {
            return false;
        }
        if ((job.getForceCanceled() == null) ^ (getForceCanceled() == null)) {
            return false;
        }
        if (job.getForceCanceled() != null && !job.getForceCanceled().equals(getForceCanceled())) {
            return false;
        }
        if ((job.getReasonCode() == null) ^ (getReasonCode() == null)) {
            return false;
        }
        if (job.getReasonCode() != null && !job.getReasonCode().equals(getReasonCode())) {
            return false;
        }
        if ((job.getComment() == null) ^ (getComment() == null)) {
            return false;
        }
        if (job.getComment() != null && !job.getComment().equals(getComment())) {
            return false;
        }
        if ((job.getTargets() == null) ^ (getTargets() == null)) {
            return false;
        }
        if (job.getTargets() != null && !job.getTargets().equals(getTargets())) {
            return false;
        }
        if ((job.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (job.getDescription() != null && !job.getDescription().equals(getDescription())) {
            return false;
        }
        if ((job.getPresignedUrlConfig() == null) ^ (getPresignedUrlConfig() == null)) {
            return false;
        }
        if (job.getPresignedUrlConfig() != null && !job.getPresignedUrlConfig().equals(getPresignedUrlConfig())) {
            return false;
        }
        if ((job.getJobExecutionsRolloutConfig() == null) ^ (getJobExecutionsRolloutConfig() == null)) {
            return false;
        }
        if (job.getJobExecutionsRolloutConfig() != null && !job.getJobExecutionsRolloutConfig().equals(getJobExecutionsRolloutConfig())) {
            return false;
        }
        if ((job.getAbortConfig() == null) ^ (getAbortConfig() == null)) {
            return false;
        }
        if (job.getAbortConfig() != null && !job.getAbortConfig().equals(getAbortConfig())) {
            return false;
        }
        if ((job.getCreatedAt() == null) ^ (getCreatedAt() == null)) {
            return false;
        }
        if (job.getCreatedAt() != null && !job.getCreatedAt().equals(getCreatedAt())) {
            return false;
        }
        if ((job.getLastUpdatedAt() == null) ^ (getLastUpdatedAt() == null)) {
            return false;
        }
        if (job.getLastUpdatedAt() != null && !job.getLastUpdatedAt().equals(getLastUpdatedAt())) {
            return false;
        }
        if ((job.getCompletedAt() == null) ^ (getCompletedAt() == null)) {
            return false;
        }
        if (job.getCompletedAt() != null && !job.getCompletedAt().equals(getCompletedAt())) {
            return false;
        }
        if ((job.getJobProcessDetails() == null) ^ (getJobProcessDetails() == null)) {
            return false;
        }
        if (job.getJobProcessDetails() != null && !job.getJobProcessDetails().equals(getJobProcessDetails())) {
            return false;
        }
        if ((job.getTimeoutConfig() == null) ^ (getTimeoutConfig() == null)) {
            return false;
        }
        return job.getTimeoutConfig() == null || job.getTimeoutConfig().equals(getTimeoutConfig());
    }

    public AbortConfig getAbortConfig() {
        return this.abortConfig;
    }

    public String getComment() {
        return this.comment;
    }

    public Date getCompletedAt() {
        return this.completedAt;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean getForceCanceled() {
        return this.forceCanceled;
    }

    public String getJobArn() {
        return this.jobArn;
    }

    public JobExecutionsRolloutConfig getJobExecutionsRolloutConfig() {
        return this.jobExecutionsRolloutConfig;
    }

    public String getJobId() {
        return this.jobId;
    }

    public JobProcessDetails getJobProcessDetails() {
        return this.jobProcessDetails;
    }

    public Date getLastUpdatedAt() {
        return this.lastUpdatedAt;
    }

    public PresignedUrlConfig getPresignedUrlConfig() {
        return this.presignedUrlConfig;
    }

    public String getReasonCode() {
        return this.reasonCode;
    }

    public String getStatus() {
        return this.status;
    }

    public String getTargetSelection() {
        return this.targetSelection;
    }

    public List<String> getTargets() {
        return this.targets;
    }

    public TimeoutConfig getTimeoutConfig() {
        return this.timeoutConfig;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((((((((((((((((((((getJobArn() == null ? 0 : getJobArn().hashCode()) + 31) * 31) + (getJobId() == null ? 0 : getJobId().hashCode())) * 31) + (getTargetSelection() == null ? 0 : getTargetSelection().hashCode())) * 31) + (getStatus() == null ? 0 : getStatus().hashCode())) * 31) + (getForceCanceled() == null ? 0 : getForceCanceled().hashCode())) * 31) + (getReasonCode() == null ? 0 : getReasonCode().hashCode())) * 31) + (getComment() == null ? 0 : getComment().hashCode())) * 31) + (getTargets() == null ? 0 : getTargets().hashCode())) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getPresignedUrlConfig() == null ? 0 : getPresignedUrlConfig().hashCode())) * 31) + (getJobExecutionsRolloutConfig() == null ? 0 : getJobExecutionsRolloutConfig().hashCode())) * 31) + (getAbortConfig() == null ? 0 : getAbortConfig().hashCode())) * 31) + (getCreatedAt() == null ? 0 : getCreatedAt().hashCode())) * 31) + (getLastUpdatedAt() == null ? 0 : getLastUpdatedAt().hashCode())) * 31) + (getCompletedAt() == null ? 0 : getCompletedAt().hashCode())) * 31) + (getJobProcessDetails() == null ? 0 : getJobProcessDetails().hashCode())) * 31;
        if (getTimeoutConfig() != null) {
            i = getTimeoutConfig().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isForceCanceled() {
        return this.forceCanceled;
    }

    public void setAbortConfig(AbortConfig abortConfig) {
        this.abortConfig = abortConfig;
    }

    public void setComment(String str) {
        this.comment = str;
    }

    public void setCompletedAt(Date date) {
        this.completedAt = date;
    }

    public void setCreatedAt(Date date) {
        this.createdAt = date;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setForceCanceled(Boolean bool) {
        this.forceCanceled = bool;
    }

    public void setJobArn(String str) {
        this.jobArn = str;
    }

    public void setJobExecutionsRolloutConfig(JobExecutionsRolloutConfig jobExecutionsRolloutConfig) {
        this.jobExecutionsRolloutConfig = jobExecutionsRolloutConfig;
    }

    public void setJobId(String str) {
        this.jobId = str;
    }

    public void setJobProcessDetails(JobProcessDetails jobProcessDetails) {
        this.jobProcessDetails = jobProcessDetails;
    }

    public void setLastUpdatedAt(Date date) {
        this.lastUpdatedAt = date;
    }

    public void setPresignedUrlConfig(PresignedUrlConfig presignedUrlConfig) {
        this.presignedUrlConfig = presignedUrlConfig;
    }

    public void setReasonCode(String str) {
        this.reasonCode = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setTargetSelection(String str) {
        this.targetSelection = str;
    }

    public void setTargets(Collection<String> collection) {
        if (collection == null) {
            this.targets = null;
        } else {
            this.targets = new ArrayList(collection);
        }
    }

    public void setTimeoutConfig(TimeoutConfig timeoutConfig) {
        this.timeoutConfig = timeoutConfig;
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
        if (getTargetSelection() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("targetSelection: ");
            outline1074.append(getTargetSelection());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getStatus() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("status: ");
            outline1075.append(getStatus());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getForceCanceled() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("forceCanceled: ");
            outline1076.append(getForceCanceled());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getReasonCode() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("reasonCode: ");
            outline1077.append(getReasonCode());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getComment() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("comment: ");
            outline1078.append(getComment());
            outline1078.append(",");
            outline107.append(outline1078.toString());
        }
        if (getTargets() != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("targets: ");
            outline1079.append(getTargets());
            outline1079.append(",");
            outline107.append(outline1079.toString());
        }
        if (getDescription() != null) {
            StringBuilder outline10710 = GeneratedOutlineSupport1.outline107("description: ");
            outline10710.append(getDescription());
            outline10710.append(",");
            outline107.append(outline10710.toString());
        }
        if (getPresignedUrlConfig() != null) {
            StringBuilder outline10711 = GeneratedOutlineSupport1.outline107("presignedUrlConfig: ");
            outline10711.append(getPresignedUrlConfig());
            outline10711.append(",");
            outline107.append(outline10711.toString());
        }
        if (getJobExecutionsRolloutConfig() != null) {
            StringBuilder outline10712 = GeneratedOutlineSupport1.outline107("jobExecutionsRolloutConfig: ");
            outline10712.append(getJobExecutionsRolloutConfig());
            outline10712.append(",");
            outline107.append(outline10712.toString());
        }
        if (getAbortConfig() != null) {
            StringBuilder outline10713 = GeneratedOutlineSupport1.outline107("abortConfig: ");
            outline10713.append(getAbortConfig());
            outline10713.append(",");
            outline107.append(outline10713.toString());
        }
        if (getCreatedAt() != null) {
            StringBuilder outline10714 = GeneratedOutlineSupport1.outline107("createdAt: ");
            outline10714.append(getCreatedAt());
            outline10714.append(",");
            outline107.append(outline10714.toString());
        }
        if (getLastUpdatedAt() != null) {
            StringBuilder outline10715 = GeneratedOutlineSupport1.outline107("lastUpdatedAt: ");
            outline10715.append(getLastUpdatedAt());
            outline10715.append(",");
            outline107.append(outline10715.toString());
        }
        if (getCompletedAt() != null) {
            StringBuilder outline10716 = GeneratedOutlineSupport1.outline107("completedAt: ");
            outline10716.append(getCompletedAt());
            outline10716.append(",");
            outline107.append(outline10716.toString());
        }
        if (getJobProcessDetails() != null) {
            StringBuilder outline10717 = GeneratedOutlineSupport1.outline107("jobProcessDetails: ");
            outline10717.append(getJobProcessDetails());
            outline10717.append(",");
            outline107.append(outline10717.toString());
        }
        if (getTimeoutConfig() != null) {
            StringBuilder outline10718 = GeneratedOutlineSupport1.outline107("timeoutConfig: ");
            outline10718.append(getTimeoutConfig());
            outline107.append(outline10718.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public Job withAbortConfig(AbortConfig abortConfig) {
        this.abortConfig = abortConfig;
        return this;
    }

    public Job withComment(String str) {
        this.comment = str;
        return this;
    }

    public Job withCompletedAt(Date date) {
        this.completedAt = date;
        return this;
    }

    public Job withCreatedAt(Date date) {
        this.createdAt = date;
        return this;
    }

    public Job withDescription(String str) {
        this.description = str;
        return this;
    }

    public Job withForceCanceled(Boolean bool) {
        this.forceCanceled = bool;
        return this;
    }

    public Job withJobArn(String str) {
        this.jobArn = str;
        return this;
    }

    public Job withJobExecutionsRolloutConfig(JobExecutionsRolloutConfig jobExecutionsRolloutConfig) {
        this.jobExecutionsRolloutConfig = jobExecutionsRolloutConfig;
        return this;
    }

    public Job withJobId(String str) {
        this.jobId = str;
        return this;
    }

    public Job withJobProcessDetails(JobProcessDetails jobProcessDetails) {
        this.jobProcessDetails = jobProcessDetails;
        return this;
    }

    public Job withLastUpdatedAt(Date date) {
        this.lastUpdatedAt = date;
        return this;
    }

    public Job withPresignedUrlConfig(PresignedUrlConfig presignedUrlConfig) {
        this.presignedUrlConfig = presignedUrlConfig;
        return this;
    }

    public Job withReasonCode(String str) {
        this.reasonCode = str;
        return this;
    }

    public Job withStatus(String str) {
        this.status = str;
        return this;
    }

    public Job withTargetSelection(String str) {
        this.targetSelection = str;
        return this;
    }

    public Job withTargets(String... strArr) {
        if (getTargets() == null) {
            this.targets = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.targets.add(str);
        }
        return this;
    }

    public Job withTimeoutConfig(TimeoutConfig timeoutConfig) {
        this.timeoutConfig = timeoutConfig;
        return this;
    }

    public void setStatus(JobStatus jobStatus) {
        this.status = jobStatus.toString();
    }

    public void setTargetSelection(TargetSelection targetSelection) {
        this.targetSelection = targetSelection.toString();
    }

    public Job withStatus(JobStatus jobStatus) {
        this.status = jobStatus.toString();
        return this;
    }

    public Job withTargetSelection(TargetSelection targetSelection) {
        this.targetSelection = targetSelection.toString();
        return this;
    }

    public Job withTargets(Collection<String> collection) {
        setTargets(collection);
        return this;
    }
}
