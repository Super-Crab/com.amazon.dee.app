package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class AssociateTargetsWithJobRequest extends AmazonWebServiceRequest implements Serializable {
    private String comment;
    private String jobId;
    private List<String> targets;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AssociateTargetsWithJobRequest)) {
            return false;
        }
        AssociateTargetsWithJobRequest associateTargetsWithJobRequest = (AssociateTargetsWithJobRequest) obj;
        if ((associateTargetsWithJobRequest.getTargets() == null) ^ (getTargets() == null)) {
            return false;
        }
        if (associateTargetsWithJobRequest.getTargets() != null && !associateTargetsWithJobRequest.getTargets().equals(getTargets())) {
            return false;
        }
        if ((associateTargetsWithJobRequest.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        if (associateTargetsWithJobRequest.getJobId() != null && !associateTargetsWithJobRequest.getJobId().equals(getJobId())) {
            return false;
        }
        if ((associateTargetsWithJobRequest.getComment() == null) ^ (getComment() == null)) {
            return false;
        }
        return associateTargetsWithJobRequest.getComment() == null || associateTargetsWithJobRequest.getComment().equals(getComment());
    }

    public String getComment() {
        return this.comment;
    }

    public String getJobId() {
        return this.jobId;
    }

    public List<String> getTargets() {
        return this.targets;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getTargets() == null ? 0 : getTargets().hashCode()) + 31) * 31) + (getJobId() == null ? 0 : getJobId().hashCode())) * 31;
        if (getComment() != null) {
            i = getComment().hashCode();
        }
        return hashCode + i;
    }

    public void setComment(String str) {
        this.comment = str;
    }

    public void setJobId(String str) {
        this.jobId = str;
    }

    public void setTargets(Collection<String> collection) {
        if (collection == null) {
            this.targets = null;
        } else {
            this.targets = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getTargets() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("targets: ");
            outline1072.append(getTargets());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getJobId() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("jobId: ");
            outline1073.append(getJobId());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getComment() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("comment: ");
            outline1074.append(getComment());
            outline107.append(outline1074.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public AssociateTargetsWithJobRequest withComment(String str) {
        this.comment = str;
        return this;
    }

    public AssociateTargetsWithJobRequest withJobId(String str) {
        this.jobId = str;
        return this;
    }

    public AssociateTargetsWithJobRequest withTargets(String... strArr) {
        if (getTargets() == null) {
            this.targets = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.targets.add(str);
        }
        return this;
    }

    public AssociateTargetsWithJobRequest withTargets(Collection<String> collection) {
        setTargets(collection);
        return this;
    }
}
