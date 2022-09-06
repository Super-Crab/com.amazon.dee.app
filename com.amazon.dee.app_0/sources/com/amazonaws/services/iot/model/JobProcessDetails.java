package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class JobProcessDetails implements Serializable {
    private Integer numberOfCanceledThings;
    private Integer numberOfFailedThings;
    private Integer numberOfInProgressThings;
    private Integer numberOfQueuedThings;
    private Integer numberOfRejectedThings;
    private Integer numberOfRemovedThings;
    private Integer numberOfSucceededThings;
    private Integer numberOfTimedOutThings;
    private List<String> processingTargets;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof JobProcessDetails)) {
            return false;
        }
        JobProcessDetails jobProcessDetails = (JobProcessDetails) obj;
        if ((jobProcessDetails.getProcessingTargets() == null) ^ (getProcessingTargets() == null)) {
            return false;
        }
        if (jobProcessDetails.getProcessingTargets() != null && !jobProcessDetails.getProcessingTargets().equals(getProcessingTargets())) {
            return false;
        }
        if ((jobProcessDetails.getNumberOfCanceledThings() == null) ^ (getNumberOfCanceledThings() == null)) {
            return false;
        }
        if (jobProcessDetails.getNumberOfCanceledThings() != null && !jobProcessDetails.getNumberOfCanceledThings().equals(getNumberOfCanceledThings())) {
            return false;
        }
        if ((jobProcessDetails.getNumberOfSucceededThings() == null) ^ (getNumberOfSucceededThings() == null)) {
            return false;
        }
        if (jobProcessDetails.getNumberOfSucceededThings() != null && !jobProcessDetails.getNumberOfSucceededThings().equals(getNumberOfSucceededThings())) {
            return false;
        }
        if ((jobProcessDetails.getNumberOfFailedThings() == null) ^ (getNumberOfFailedThings() == null)) {
            return false;
        }
        if (jobProcessDetails.getNumberOfFailedThings() != null && !jobProcessDetails.getNumberOfFailedThings().equals(getNumberOfFailedThings())) {
            return false;
        }
        if ((jobProcessDetails.getNumberOfRejectedThings() == null) ^ (getNumberOfRejectedThings() == null)) {
            return false;
        }
        if (jobProcessDetails.getNumberOfRejectedThings() != null && !jobProcessDetails.getNumberOfRejectedThings().equals(getNumberOfRejectedThings())) {
            return false;
        }
        if ((jobProcessDetails.getNumberOfQueuedThings() == null) ^ (getNumberOfQueuedThings() == null)) {
            return false;
        }
        if (jobProcessDetails.getNumberOfQueuedThings() != null && !jobProcessDetails.getNumberOfQueuedThings().equals(getNumberOfQueuedThings())) {
            return false;
        }
        if ((jobProcessDetails.getNumberOfInProgressThings() == null) ^ (getNumberOfInProgressThings() == null)) {
            return false;
        }
        if (jobProcessDetails.getNumberOfInProgressThings() != null && !jobProcessDetails.getNumberOfInProgressThings().equals(getNumberOfInProgressThings())) {
            return false;
        }
        if ((jobProcessDetails.getNumberOfRemovedThings() == null) ^ (getNumberOfRemovedThings() == null)) {
            return false;
        }
        if (jobProcessDetails.getNumberOfRemovedThings() != null && !jobProcessDetails.getNumberOfRemovedThings().equals(getNumberOfRemovedThings())) {
            return false;
        }
        if ((jobProcessDetails.getNumberOfTimedOutThings() == null) ^ (getNumberOfTimedOutThings() == null)) {
            return false;
        }
        return jobProcessDetails.getNumberOfTimedOutThings() == null || jobProcessDetails.getNumberOfTimedOutThings().equals(getNumberOfTimedOutThings());
    }

    public Integer getNumberOfCanceledThings() {
        return this.numberOfCanceledThings;
    }

    public Integer getNumberOfFailedThings() {
        return this.numberOfFailedThings;
    }

    public Integer getNumberOfInProgressThings() {
        return this.numberOfInProgressThings;
    }

    public Integer getNumberOfQueuedThings() {
        return this.numberOfQueuedThings;
    }

    public Integer getNumberOfRejectedThings() {
        return this.numberOfRejectedThings;
    }

    public Integer getNumberOfRemovedThings() {
        return this.numberOfRemovedThings;
    }

    public Integer getNumberOfSucceededThings() {
        return this.numberOfSucceededThings;
    }

    public Integer getNumberOfTimedOutThings() {
        return this.numberOfTimedOutThings;
    }

    public List<String> getProcessingTargets() {
        return this.processingTargets;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((((((getProcessingTargets() == null ? 0 : getProcessingTargets().hashCode()) + 31) * 31) + (getNumberOfCanceledThings() == null ? 0 : getNumberOfCanceledThings().hashCode())) * 31) + (getNumberOfSucceededThings() == null ? 0 : getNumberOfSucceededThings().hashCode())) * 31) + (getNumberOfFailedThings() == null ? 0 : getNumberOfFailedThings().hashCode())) * 31) + (getNumberOfRejectedThings() == null ? 0 : getNumberOfRejectedThings().hashCode())) * 31) + (getNumberOfQueuedThings() == null ? 0 : getNumberOfQueuedThings().hashCode())) * 31) + (getNumberOfInProgressThings() == null ? 0 : getNumberOfInProgressThings().hashCode())) * 31) + (getNumberOfRemovedThings() == null ? 0 : getNumberOfRemovedThings().hashCode())) * 31;
        if (getNumberOfTimedOutThings() != null) {
            i = getNumberOfTimedOutThings().hashCode();
        }
        return hashCode + i;
    }

    public void setNumberOfCanceledThings(Integer num) {
        this.numberOfCanceledThings = num;
    }

    public void setNumberOfFailedThings(Integer num) {
        this.numberOfFailedThings = num;
    }

    public void setNumberOfInProgressThings(Integer num) {
        this.numberOfInProgressThings = num;
    }

    public void setNumberOfQueuedThings(Integer num) {
        this.numberOfQueuedThings = num;
    }

    public void setNumberOfRejectedThings(Integer num) {
        this.numberOfRejectedThings = num;
    }

    public void setNumberOfRemovedThings(Integer num) {
        this.numberOfRemovedThings = num;
    }

    public void setNumberOfSucceededThings(Integer num) {
        this.numberOfSucceededThings = num;
    }

    public void setNumberOfTimedOutThings(Integer num) {
        this.numberOfTimedOutThings = num;
    }

    public void setProcessingTargets(Collection<String> collection) {
        if (collection == null) {
            this.processingTargets = null;
        } else {
            this.processingTargets = new ArrayList(collection);
        }
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getProcessingTargets() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("processingTargets: ");
            outline1072.append(getProcessingTargets());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNumberOfCanceledThings() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("numberOfCanceledThings: ");
            outline1073.append(getNumberOfCanceledThings());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getNumberOfSucceededThings() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("numberOfSucceededThings: ");
            outline1074.append(getNumberOfSucceededThings());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getNumberOfFailedThings() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("numberOfFailedThings: ");
            outline1075.append(getNumberOfFailedThings());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getNumberOfRejectedThings() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("numberOfRejectedThings: ");
            outline1076.append(getNumberOfRejectedThings());
            outline1076.append(",");
            outline107.append(outline1076.toString());
        }
        if (getNumberOfQueuedThings() != null) {
            StringBuilder outline1077 = GeneratedOutlineSupport1.outline107("numberOfQueuedThings: ");
            outline1077.append(getNumberOfQueuedThings());
            outline1077.append(",");
            outline107.append(outline1077.toString());
        }
        if (getNumberOfInProgressThings() != null) {
            StringBuilder outline1078 = GeneratedOutlineSupport1.outline107("numberOfInProgressThings: ");
            outline1078.append(getNumberOfInProgressThings());
            outline1078.append(",");
            outline107.append(outline1078.toString());
        }
        if (getNumberOfRemovedThings() != null) {
            StringBuilder outline1079 = GeneratedOutlineSupport1.outline107("numberOfRemovedThings: ");
            outline1079.append(getNumberOfRemovedThings());
            outline1079.append(",");
            outline107.append(outline1079.toString());
        }
        if (getNumberOfTimedOutThings() != null) {
            StringBuilder outline10710 = GeneratedOutlineSupport1.outline107("numberOfTimedOutThings: ");
            outline10710.append(getNumberOfTimedOutThings());
            outline107.append(outline10710.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public JobProcessDetails withNumberOfCanceledThings(Integer num) {
        this.numberOfCanceledThings = num;
        return this;
    }

    public JobProcessDetails withNumberOfFailedThings(Integer num) {
        this.numberOfFailedThings = num;
        return this;
    }

    public JobProcessDetails withNumberOfInProgressThings(Integer num) {
        this.numberOfInProgressThings = num;
        return this;
    }

    public JobProcessDetails withNumberOfQueuedThings(Integer num) {
        this.numberOfQueuedThings = num;
        return this;
    }

    public JobProcessDetails withNumberOfRejectedThings(Integer num) {
        this.numberOfRejectedThings = num;
        return this;
    }

    public JobProcessDetails withNumberOfRemovedThings(Integer num) {
        this.numberOfRemovedThings = num;
        return this;
    }

    public JobProcessDetails withNumberOfSucceededThings(Integer num) {
        this.numberOfSucceededThings = num;
        return this;
    }

    public JobProcessDetails withNumberOfTimedOutThings(Integer num) {
        this.numberOfTimedOutThings = num;
        return this;
    }

    public JobProcessDetails withProcessingTargets(String... strArr) {
        if (getProcessingTargets() == null) {
            this.processingTargets = new ArrayList(strArr.length);
        }
        for (String str : strArr) {
            this.processingTargets.add(str);
        }
        return this;
    }

    public JobProcessDetails withProcessingTargets(Collection<String> collection) {
        setProcessingTargets(collection);
        return this;
    }
}
