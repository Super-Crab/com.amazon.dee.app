package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListJobExecutionsForJobResult implements Serializable {
    private List<JobExecutionSummaryForJob> executionSummaries;
    private String nextToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListJobExecutionsForJobResult)) {
            return false;
        }
        ListJobExecutionsForJobResult listJobExecutionsForJobResult = (ListJobExecutionsForJobResult) obj;
        if ((listJobExecutionsForJobResult.getExecutionSummaries() == null) ^ (getExecutionSummaries() == null)) {
            return false;
        }
        if (listJobExecutionsForJobResult.getExecutionSummaries() != null && !listJobExecutionsForJobResult.getExecutionSummaries().equals(getExecutionSummaries())) {
            return false;
        }
        if ((listJobExecutionsForJobResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listJobExecutionsForJobResult.getNextToken() == null || listJobExecutionsForJobResult.getNextToken().equals(getNextToken());
    }

    public List<JobExecutionSummaryForJob> getExecutionSummaries() {
        return this.executionSummaries;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getExecutionSummaries() == null ? 0 : getExecutionSummaries().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setExecutionSummaries(Collection<JobExecutionSummaryForJob> collection) {
        if (collection == null) {
            this.executionSummaries = null;
        } else {
            this.executionSummaries = new ArrayList(collection);
        }
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getExecutionSummaries() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("executionSummaries: ");
            outline1072.append(getExecutionSummaries());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1073.append(getNextToken());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public ListJobExecutionsForJobResult withExecutionSummaries(JobExecutionSummaryForJob... jobExecutionSummaryForJobArr) {
        if (getExecutionSummaries() == null) {
            this.executionSummaries = new ArrayList(jobExecutionSummaryForJobArr.length);
        }
        for (JobExecutionSummaryForJob jobExecutionSummaryForJob : jobExecutionSummaryForJobArr) {
            this.executionSummaries.add(jobExecutionSummaryForJob);
        }
        return this;
    }

    public ListJobExecutionsForJobResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListJobExecutionsForJobResult withExecutionSummaries(Collection<JobExecutionSummaryForJob> collection) {
        setExecutionSummaries(collection);
        return this;
    }
}
