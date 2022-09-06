package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.extended.utils.JobKeyUtils;
import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class GetJobStatusRequest implements CloudDriveRequest {
    private final String jobKey;

    public GetJobStatusRequest(String str, JobType jobType) {
        this.jobKey = JobKeyUtils.generateJobKey(str, jobType);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetJobStatusRequest) && compareTo((CloudDriveRequest) ((GetJobStatusRequest) obj)) == 0;
    }

    public String getJobKey() {
        return this.jobKey;
    }

    public int hashCode() {
        return (((getJobKey() == null ? 0 : getJobKey().hashCode()) + 1) * 31) + super.hashCode();
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof GetJobStatusRequest)) {
            return 1;
        }
        int compare = ObjectComparator.compare(getJobKey(), ((GetJobStatusRequest) cloudDriveRequest).getJobKey());
        if (compare == 0) {
            return 0;
        }
        return compare;
    }
}
