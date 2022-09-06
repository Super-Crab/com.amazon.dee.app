package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class GetProfileRequest implements CloudDriveRequest {
    private String customerId;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetProfileRequest) && compareTo((CloudDriveRequest) ((GetProfileRequest) obj)) == 0;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public int hashCode() {
        return (((getCustomerId() == null ? 0 : getCustomerId().hashCode()) + 1) * 31) + super.hashCode();
    }

    public void setCustomerId(String str) {
        this.customerId = str;
    }

    public GetProfileRequest withCustomerId(String str) {
        setCustomerId(str);
        return this;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof GetProfileRequest)) {
            return 1;
        }
        int compare = ObjectComparator.compare(getCustomerId(), ((GetProfileRequest) cloudDriveRequest).getCustomerId());
        if (compare == 0) {
            return 0;
        }
        return compare;
    }
}
