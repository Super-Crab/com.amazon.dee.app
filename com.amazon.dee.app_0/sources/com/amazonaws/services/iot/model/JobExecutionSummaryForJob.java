package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class JobExecutionSummaryForJob implements Serializable {
    private JobExecutionSummary jobExecutionSummary;
    private String thingArn;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof JobExecutionSummaryForJob)) {
            return false;
        }
        JobExecutionSummaryForJob jobExecutionSummaryForJob = (JobExecutionSummaryForJob) obj;
        if ((jobExecutionSummaryForJob.getThingArn() == null) ^ (getThingArn() == null)) {
            return false;
        }
        if (jobExecutionSummaryForJob.getThingArn() != null && !jobExecutionSummaryForJob.getThingArn().equals(getThingArn())) {
            return false;
        }
        if ((jobExecutionSummaryForJob.getJobExecutionSummary() == null) ^ (getJobExecutionSummary() == null)) {
            return false;
        }
        return jobExecutionSummaryForJob.getJobExecutionSummary() == null || jobExecutionSummaryForJob.getJobExecutionSummary().equals(getJobExecutionSummary());
    }

    public JobExecutionSummary getJobExecutionSummary() {
        return this.jobExecutionSummary;
    }

    public String getThingArn() {
        return this.thingArn;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getThingArn() == null ? 0 : getThingArn().hashCode()) + 31) * 31;
        if (getJobExecutionSummary() != null) {
            i = getJobExecutionSummary().hashCode();
        }
        return hashCode + i;
    }

    public void setJobExecutionSummary(JobExecutionSummary jobExecutionSummary) {
        this.jobExecutionSummary = jobExecutionSummary;
    }

    public void setThingArn(String str) {
        this.thingArn = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getThingArn() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("thingArn: ");
            outline1072.append(getThingArn());
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

    public JobExecutionSummaryForJob withJobExecutionSummary(JobExecutionSummary jobExecutionSummary) {
        this.jobExecutionSummary = jobExecutionSummary;
        return this;
    }

    public JobExecutionSummaryForJob withThingArn(String str) {
        this.thingArn = str;
        return this;
    }
}
