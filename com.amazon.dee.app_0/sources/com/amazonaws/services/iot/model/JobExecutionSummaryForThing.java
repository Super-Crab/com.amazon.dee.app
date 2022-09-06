package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class JobExecutionSummaryForThing implements Serializable {
    private JobExecutionSummary jobExecutionSummary;
    private String jobId;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof JobExecutionSummaryForThing)) {
            return false;
        }
        JobExecutionSummaryForThing jobExecutionSummaryForThing = (JobExecutionSummaryForThing) obj;
        if ((jobExecutionSummaryForThing.getJobId() == null) ^ (getJobId() == null)) {
            return false;
        }
        if (jobExecutionSummaryForThing.getJobId() != null && !jobExecutionSummaryForThing.getJobId().equals(getJobId())) {
            return false;
        }
        if ((jobExecutionSummaryForThing.getJobExecutionSummary() == null) ^ (getJobExecutionSummary() == null)) {
            return false;
        }
        return jobExecutionSummaryForThing.getJobExecutionSummary() == null || jobExecutionSummaryForThing.getJobExecutionSummary().equals(getJobExecutionSummary());
    }

    public JobExecutionSummary getJobExecutionSummary() {
        return this.jobExecutionSummary;
    }

    public String getJobId() {
        return this.jobId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getJobId() == null ? 0 : getJobId().hashCode()) + 31) * 31;
        if (getJobExecutionSummary() != null) {
            i = getJobExecutionSummary().hashCode();
        }
        return hashCode + i;
    }

    public void setJobExecutionSummary(JobExecutionSummary jobExecutionSummary) {
        this.jobExecutionSummary = jobExecutionSummary;
    }

    public void setJobId(String str) {
        this.jobId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getJobId() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("jobId: ");
            outline1072.append(getJobId());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getJobExecutionSummary() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("jobExecutionSummary: ");
            outline1073.append(getJobExecutionSummary());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public JobExecutionSummaryForThing withJobExecutionSummary(JobExecutionSummary jobExecutionSummary) {
        this.jobExecutionSummary = jobExecutionSummary;
        return this;
    }

    public JobExecutionSummaryForThing withJobId(String str) {
        this.jobId = str;
        return this;
    }
}
