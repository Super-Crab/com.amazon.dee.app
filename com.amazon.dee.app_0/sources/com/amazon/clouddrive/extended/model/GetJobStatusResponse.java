package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class GetJobStatusResponse implements CloudDriveResponse {
    private Long count;
    private Long failedJobs;
    private Long lastModifiedTimestamp;
    private Long startTimestamp;
    private String status;
    private Long succeedJobs;
    private String taskId;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetJobStatusResponse) && compareTo((CloudDriveResponse) ((GetJobStatusResponse) obj)) == 0;
    }

    public Long getCount() {
        return this.count;
    }

    public Long getFailedJobs() {
        return this.failedJobs;
    }

    public Long getLastModifiedTimestamp() {
        return this.lastModifiedTimestamp;
    }

    public Long getStartTimestamp() {
        return this.startTimestamp;
    }

    public String getStatus() {
        return this.status;
    }

    public Long getSucceedJobs() {
        return this.succeedJobs;
    }

    public String getTaskId() {
        return this.taskId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getStartTimestamp() == null ? 0 : getStartTimestamp().hashCode()) + 1 + (getLastModifiedTimestamp() == null ? 0 : getLastModifiedTimestamp().hashCode()) + (getCount() == null ? 0 : getCount().hashCode()) + (getSucceedJobs() == null ? 0 : getSucceedJobs().hashCode()) + (getFailedJobs() == null ? 0 : getFailedJobs().hashCode()) + (getStatus() == null ? 0 : getStatus().hashCode());
        if (getTaskId() != null) {
            i = getTaskId().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setCount(Long l) {
        this.count = l;
    }

    public void setFailedJobs(Long l) {
        this.failedJobs = l;
    }

    public void setLastModifiedTimestamp(Long l) {
        this.lastModifiedTimestamp = l;
    }

    public void setStartTimestamp(Long l) {
        this.startTimestamp = l;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public void setSucceedJobs(Long l) {
        this.succeedJobs = l;
    }

    public void setTaskId(String str) {
        this.taskId = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (!(cloudDriveResponse instanceof GetJobStatusResponse)) {
            return 1;
        }
        GetJobStatusResponse getJobStatusResponse = (GetJobStatusResponse) cloudDriveResponse;
        int compare = ObjectComparator.compare(getStartTimestamp(), getJobStatusResponse.getStartTimestamp());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getLastModifiedTimestamp(), getJobStatusResponse.getLastModifiedTimestamp());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getCount(), getJobStatusResponse.getCount());
        if (compare3 != 0) {
            return compare3;
        }
        int compare4 = ObjectComparator.compare(getSucceedJobs(), getJobStatusResponse.getSucceedJobs());
        if (compare4 != 0) {
            return compare4;
        }
        int compare5 = ObjectComparator.compare(getFailedJobs(), getJobStatusResponse.getFailedJobs());
        if (compare5 != 0) {
            return compare5;
        }
        int compare6 = ObjectComparator.compare(getStatus(), getJobStatusResponse.getStatus());
        if (compare6 != 0) {
            return compare6;
        }
        int compare7 = ObjectComparator.compare(getTaskId(), getJobStatusResponse.getTaskId());
        if (compare7 == 0) {
            return 0;
        }
        return compare7;
    }
}
