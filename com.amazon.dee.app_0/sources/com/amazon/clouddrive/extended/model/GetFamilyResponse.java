package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
import com.amazon.clouddrive.model.ObjectComparator;
import java.util.List;
/* loaded from: classes11.dex */
public class GetFamilyResponse implements CloudDriveResponse {
    private String adminId;
    private String callerId;
    private String familyId;
    private String familyName;
    private List<CustomerInfo> members;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetFamilyResponse) && compareTo((CloudDriveResponse) ((GetFamilyResponse) obj)) == 0;
    }

    public String getAdminId() {
        return this.adminId;
    }

    public String getCallerId() {
        return this.callerId;
    }

    public String getFamilyId() {
        return this.familyId;
    }

    public String getFamilyName() {
        return this.familyName;
    }

    public List<CustomerInfo> getMembers() {
        return this.members;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getFamilyId() == null ? 0 : getFamilyId().hashCode()) + 1 + (getAdminId() == null ? 0 : getAdminId().hashCode()) + (getCallerId() == null ? 0 : getCallerId().hashCode()) + (getMembers() == null ? 0 : getMembers().hashCode());
        if (getFamilyName() != null) {
            i = getFamilyName().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setAdminId(String str) {
        this.adminId = str;
    }

    public void setCallerId(String str) {
        this.callerId = str;
    }

    public void setFamilyId(String str) {
        this.familyId = str;
    }

    public void setFamilyName(String str) {
        this.familyName = str;
    }

    public void setMembers(List<CustomerInfo> list) {
        this.members = list;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (!(cloudDriveResponse instanceof GetFamilyResponse)) {
            return 1;
        }
        GetFamilyResponse getFamilyResponse = (GetFamilyResponse) cloudDriveResponse;
        int compare = ObjectComparator.compare(getFamilyId(), getFamilyResponse.getFamilyId());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getAdminId(), getFamilyResponse.getAdminId());
        if (compare2 != 0) {
            return compare2;
        }
        int compare3 = ObjectComparator.compare(getCallerId(), getFamilyResponse.getCallerId());
        if (compare3 != 0) {
            return compare3;
        }
        int compareCollections = ObjectComparator.compareCollections(getMembers(), getFamilyResponse.getMembers());
        if (compareCollections != 0) {
            return compareCollections;
        }
        int compare4 = ObjectComparator.compare(getFamilyName(), getFamilyResponse.getFamilyName());
        if (compare4 == 0) {
            return 0;
        }
        return compare4;
    }
}
