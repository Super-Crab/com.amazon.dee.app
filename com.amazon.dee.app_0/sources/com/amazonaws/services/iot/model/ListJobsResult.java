package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes13.dex */
public class ListJobsResult implements Serializable {
    private List<JobSummary> jobs;
    private String nextToken;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListJobsResult)) {
            return false;
        }
        ListJobsResult listJobsResult = (ListJobsResult) obj;
        if ((listJobsResult.getJobs() == null) ^ (getJobs() == null)) {
            return false;
        }
        if (listJobsResult.getJobs() != null && !listJobsResult.getJobs().equals(getJobs())) {
            return false;
        }
        if ((listJobsResult.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        return listJobsResult.getNextToken() == null || listJobsResult.getNextToken().equals(getNextToken());
    }

    public List<JobSummary> getJobs() {
        return this.jobs;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getJobs() == null ? 0 : getJobs().hashCode()) + 31) * 31;
        if (getNextToken() != null) {
            i = getNextToken().hashCode();
        }
        return hashCode + i;
    }

    public void setJobs(Collection<JobSummary> collection) {
        if (collection == null) {
            this.jobs = null;
        } else {
            this.jobs = new ArrayList(collection);
        }
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getJobs() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("jobs: ");
            outline1072.append(getJobs());
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

    public ListJobsResult withJobs(JobSummary... jobSummaryArr) {
        if (getJobs() == null) {
            this.jobs = new ArrayList(jobSummaryArr.length);
        }
        for (JobSummary jobSummary : jobSummaryArr) {
            this.jobs.add(jobSummary);
        }
        return this;
    }

    public ListJobsResult withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public ListJobsResult withJobs(Collection<JobSummary> collection) {
        setJobs(collection);
        return this;
    }
}
