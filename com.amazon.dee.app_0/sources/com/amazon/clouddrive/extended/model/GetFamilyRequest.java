package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class GetFamilyRequest implements CloudDriveRequest {
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetFamilyRequest) && compareTo((CloudDriveRequest) ((GetFamilyRequest) obj)) == 0;
    }

    public int hashCode() {
        return super.hashCode();
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof GetFamilyRequest)) {
            return 1;
        }
        int compare = ObjectComparator.compare(Integer.valueOf(hashCode()), Integer.valueOf(((GetFamilyRequest) cloudDriveRequest).hashCode()));
        if (compare == 0) {
            return 0;
        }
        return compare;
    }
}
