package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class DescribeJobExecutionResult implements Serializable {
    private JobExecution execution;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeJobExecutionResult)) {
            return false;
        }
        DescribeJobExecutionResult describeJobExecutionResult = (DescribeJobExecutionResult) obj;
        if ((describeJobExecutionResult.getExecution() == null) ^ (getExecution() == null)) {
            return false;
        }
        return describeJobExecutionResult.getExecution() == null || describeJobExecutionResult.getExecution().equals(getExecution());
    }

    public JobExecution getExecution() {
        return this.execution;
    }

    public int hashCode() {
        return 31 + (getExecution() == null ? 0 : getExecution().hashCode());
    }

    public void setExecution(JobExecution jobExecution) {
        this.execution = jobExecution;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getExecution() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("execution: ");
            outline1072.append(getExecution());
            outline107.append(outline1072.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public DescribeJobExecutionResult withExecution(JobExecution jobExecution) {
        this.execution = jobExecution;
        return this;
    }
}
