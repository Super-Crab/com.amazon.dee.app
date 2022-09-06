package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
import java.util.List;
/* loaded from: classes11.dex */
public class GetFacesForPersonRequest implements CloudDriveRequest {
    private List<String> nodeIds;
    private String personId;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetFacesForPersonRequest) && compareTo((CloudDriveRequest) ((GetFacesForPersonRequest) obj)) == 0;
    }

    public List<String> getNodeIds() {
        return this.nodeIds;
    }

    public String getPersonId() {
        return this.personId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getPersonId() == null ? 0 : getPersonId().hashCode()) + 1;
        if (getNodeIds() != null) {
            i = getNodeIds().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setNodeIds(List<String> list) {
        this.nodeIds = list;
    }

    public void setPersonId(String str) {
        this.personId = str;
    }

    public GetFacesForPersonRequest withNodeIds(List<String> list) {
        setNodeIds(list);
        return this;
    }

    public GetFacesForPersonRequest withPersonId(String str) {
        setPersonId(str);
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
        if (!(cloudDriveRequest instanceof GetFacesForPersonRequest)) {
            return 1;
        }
        GetFacesForPersonRequest getFacesForPersonRequest = (GetFacesForPersonRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getPersonId(), getFacesForPersonRequest.getPersonId());
        if (compare != 0) {
            return compare;
        }
        int compareCollections = ObjectComparator.compareCollections(getNodeIds(), getFacesForPersonRequest.getNodeIds());
        if (compareCollections == 0) {
            return 0;
        }
        return compareCollections;
    }
}
