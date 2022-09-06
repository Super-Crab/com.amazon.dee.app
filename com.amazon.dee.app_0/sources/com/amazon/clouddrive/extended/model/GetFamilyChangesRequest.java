package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
/* loaded from: classes11.dex */
public class GetFamilyChangesRequest implements CloudDriveRequest {
    public static int DEFAULT_ITEM_LIMIT = 2000;
    private String mCheckpoint;
    private String mIncludePurged;
    private int mRequestedItemCount = DEFAULT_ITEM_LIMIT;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetFamilyChangesRequest) && compareTo((CloudDriveRequest) ((GetFamilyChangesRequest) obj)) == 0;
    }

    public String getCheckpoint() {
        return this.mCheckpoint;
    }

    public String getIncludePurged() {
        return this.mIncludePurged;
    }

    public int getRequestedItemCount() {
        return this.mRequestedItemCount;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getCheckpoint() == null ? 0 : getCheckpoint().hashCode()) + 1;
        if (getIncludePurged() != null) {
            i = getIncludePurged().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setCheckpoint(String str) {
        this.mCheckpoint = str;
    }

    public void setIncludePurged(String str) {
        this.mIncludePurged = str;
    }

    public void setRequestedItemCount(int i) {
        this.mRequestedItemCount = i;
    }

    public GetFamilyChangesRequest withCheckpoint(String str) {
        setCheckpoint(str);
        return this;
    }

    public GetFamilyChangesRequest withIncludePurged(String str) {
        setIncludePurged(str);
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
        if (!(cloudDriveRequest instanceof GetFamilyChangesRequest)) {
            return 1;
        }
        GetFamilyChangesRequest getFamilyChangesRequest = (GetFamilyChangesRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getCheckpoint(), getFamilyChangesRequest.getCheckpoint());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getIncludePurged(), getFamilyChangesRequest.getIncludePurged());
        if (compare2 == 0) {
            return 0;
        }
        return compare2;
    }
}
