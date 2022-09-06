package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class CreateJobRequest extends AmazonWebServiceRequest implements Serializable {
    private AbortConfig abortConfig;
    private String description;
    private String document;
    private String documentSource;
    private JobExecutionsRolloutConfig jobExecutionsRolloutConfig;
    private String jobId;
    private PresignedUrlConfig presignedUrlConfig;
    private List<Tag> tags;
    private String targetSelection;
    private List<String> targets;
    private TimeoutConfig timeoutConfig;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateJobRequest)) {
            return false;
        }
        CreateJobRequest createJobRequest = (CreateJobRequest) obj;
        if ((createJobRequest.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        if (createJobRequest.getJobId() != null && !createJobRequest.getJobId().equals(getJobId())) {
            return false;
        }
        if ((createJobRequest.getTargets() == null) ^ (getTargets() == null)) {
            return false;
        }
        if (createJobRequest.getTargets() != null && !createJobRequest.getTargets().equals(getTargets())) {
            return false;
        }
        if ((createJobRequest.getDocumentSource() == null) ^ (getDocumentSource() == null)) {
            return false;
        }
        if (createJobRequest.getDocumentSource() != null && !createJobRequest.getDocumentSource().equals(getDocumentSource())) {
            return false;
        }
        if ((createJobRequest.getDocument() == null) ^ (getDocument() == null)) {
            return false;
        }
        if (createJobRequest.getDocument() != null && !createJobRequest.getDocument().equals(getDocument())) {
            return false;
        }
        if ((createJobRequest.getDescription() == null) ^ (getDescription() == null)) {
            return false;
        }
        if (createJobRequest.getDescription() != null && !createJobRequest.getDescription().equals(getDescription())) {
            return false;
        }
        if ((createJobRequest.getPresignedUrlConfig() == null) ^ (getPresignedUrlConfig() == null)) {
            return false;
        }
        if (createJobRequest.getPresignedUrlConfig() != null && !createJobRequest.getPresignedUrlConfig().equals(getPresignedUrlConfig())) {
            return false;
        }
        if ((createJobRequest.getTargetSelection() == null) ^ (getTargetSelection() == null)) {
            return false;
        }
        if (createJobRequest.getTargetSelection() != null && !createJobRequest.getTargetSelection().equals(getTargetSelection())) {
            return false;
        }
        if ((createJobRequest.getJobExecutionsRolloutConfig() == null) ^ (getJobExecutionsRolloutConfig() == null)) {
            return false;
        }
        if (createJobRequest.getJobExecutionsRolloutConfig() != null && !createJobRequest.getJobExecutionsRolloutConfig().equals(getJobExecutionsRolloutConfig())) {
            return false;
        }
        if ((createJobRequest.getAbortConfig() == null) ^ (getAbortConfig() == null)) {
            return false;
        }
        if (createJobRequest.getAbortConfig() != null && !createJobRequest.getAbortConfig().equals(getAbortConfig())) {
            return false;
        }
        if ((createJobRequest.getTimeoutConfig() == null) ^ (getTimeoutConfig() == null)) {
            return false;
        }
        if (createJobRequest.getTimeoutConfig() != null && !createJobRequest.getTimeoutConfig().equals(getTimeoutConfig())) {
            return false;
        }
        if ((createJobRequest.getTags() == null) ^ (getTags() == null)) {
            return false;
        }
        return createJobRequest.getTags() == null || createJobRequest.getTags().equals(getTags());
    }

    public AbortConfig getAbortConfig() {
        return this.abortConfig;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDocument() {
        return this.document;
    }

    public String getDocumentSource() {
        return this.documentSource;
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

    public List<Tag> getTags() {
        return this.tags;
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
        int hashCode = ((((((((((((((((((((getJobId() == null ? 0 : getJobId().hashCode()) + 31) * 31) + (getTargets() == null ? 0 : getTargets().hashCode())) * 31) + (getDocumentSource() == null ? 0 : getDocumentSource().hashCode())) * 31) + (getDocument() == null ? 0 : getDocument().hashCode())) * 31) + (getDescription() == null ? 0 : getDescription().hashCode())) * 31) + (getPresignedUrlConfig() == null ? 0 : getPresignedUrlConfig().hashCode())) * 31) + (getTargetSelection() == null ? 0 : getTargetSelection().hashCode())) * 31) + (getJobExecutionsRolloutConfig() == null ? 0 : getJobExecutionsRolloutConfig().hashCode())) * 31) + (getAbortConfig() == null ? 0 : getAbortConfig().hashCode())) * 31) + (getTimeoutConfig() == null ? 0 : getTimeoutConfig().hashCode())) * 31;
        if (getTags() != null) {
            i = getTags().hashCode();
        }
        return hashCode + i;
    }

    public void setAbortConfig(AbortConfig abortConfig) {
        this.abortConfig = abortConfig;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDocument(String str) {
        this.document = str;
    }

    public void setDocumentSource(String str) {
        this.documentSource = str;
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

    public void setTags(Collection<Tag> collection) {
        if (collection == null) {
            this.tags = null;
        } else {
            this.tags = new ArrayList(collection);
        }
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
        if (getJobId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("jobId: ");
            outline1072.append(getJobId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getTargets() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("targets: ");
            outline1073.append(getTargets());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getDocumentSource() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("documentSource: ");
            outline1074.append(getDocumentSource());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getDocument() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("document: ");
            outline1075.append(getDocument());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getDescription() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("description: ");
            outline1076.append(getDescription());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getPresignedUrlConfig() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("presignedUrlConfig: ");
            outline1077.append(getPresignedUrlConfig());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getTargetSelection() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("targetSelection: ");
            outline1078.append(getTargetSelection());
            outline1078.append(",");
            outline107.append(outline1078.toString());
        }
        if (getJobExecutionsRolloutConfig() != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("jobExecutionsRolloutConfig: ");
            outline1079.append(getJobExecutionsRolloutConfig());
            outline1079.append(",");
            outline107.append(outline1079.toString());
        }
        if (getAbortConfig() != null) {
            StringBuilder outline10710 = GeneratedOutlineSupport1.outline107("abortConfig: ");
            outline10710.append(getAbortConfig());
            outline10710.append(",");
            outline107.append(outline10710.toString());
        }
        if (getTimeoutConfig() != null) {
            StringBuilder outline10711 = GeneratedOutlineSupport1.outline107("timeoutConfig: ");
            outline10711.append(getTimeoutConfig());
            outline10711.append(",");
            outline107.append(outline10711.toString());
        }
        if (getTags() != null) {
            StringBuilder outline10712 = GeneratedOutlineSupport1.outline107("tags: ");
            outline10712.append(getTags());
            outline107.append(outline10712.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public CreateJobRequest withAbortConfig(AbortConfig abortConfig) {
        this.abortConfig = abortConfig;
        return this;
    }

    public CreateJobRequest withDescription(String str) {
        this.description = str;
        return this;
    }

    public CreateJobRequest withDocument(String str) {
        this.document = str;
        return this;
    }

    public CreateJobRequest withDocumentSource(String str) {
        this.documentSource = str;
        return this;
    }

    public CreateJobRequest withJobExecutionsRolloutConfig(JobExecutionsRolloutConfig jobExecutionsRolloutConfig) {
        this.jobExecutionsRolloutConfig = jobExecutionsRolloutConfig;
        return this;
    }

    public CreateJobRequest withJobId(String str) {
        this.jobId = str;
        return this;
    }

    public CreateJobRequest withPresignedUrlConfig(PresignedUrlConfig presignedUrlConfig) {
        this.presignedUrlConfig = presignedUrlConfig;
        return this;
    }

    public CreateJobRequest withTags(Tag... tagArr) {
        if (getTags() == null) {
            this.tags = new ArrayList(tagArr.length);
        }
        for (Tag tag : tagArr) {
            this.tags.add(tag);
        }
        return this;
    }

    public CreateJobRequest withTargetSelection(String str) {
        this.targetSelection = str;
        return this;
    }

    public CreateJobRequest withTargets(String... strArr) {
        if (getTargets() == null) {
            this.targets = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.targets.add(str);
        }
        return this;
    }

    public CreateJobRequest withTimeoutConfig(TimeoutConfig timeoutConfig) {
        this.timeoutConfig = timeoutConfig;
        return this;
    }

    public void setTargetSelection(TargetSelection targetSelection) {
        this.targetSelection = targetSelection.toString();
    }

    public CreateJobRequest withTargetSelection(TargetSelection targetSelection) {
        this.targetSelection = targetSelection.toString();
        return this;
    }

    public CreateJobRequest withTags(Collection<Tag> collection) {
        setTags(collection);
        return this;
    }

    public CreateJobRequest withTargets(Collection<String> collection) {
        setTargets(collection);
        return this;
    }
}
