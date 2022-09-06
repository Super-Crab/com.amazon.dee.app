package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
import java.util.List;
/* loaded from: classes11.dex */
public class RemoveFacesFromPersonRequest implements CloudDriveRequest {
    private List<NodeFacePair> faces;
    private String personId;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof RemoveFacesFromPersonRequest) && compareTo((CloudDriveRequest) ((RemoveFacesFromPersonRequest) obj)) == 0;
    }

    public List<NodeFacePair> getFaces() {
        return this.faces;
    }

    public String getPersonId() {
        return this.personId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getPersonId() == null ? 0 : getPersonId().hashCode()) + 1;
        if (getFaces() != null) {
            i = getFaces().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setFaces(List<NodeFacePair> list) {
        this.faces = list;
    }

    public void setPersonId(String str) {
        this.personId = str;
    }

    public RemoveFacesFromPersonRequest withFaces(List<NodeFacePair> list) {
        setFaces(list);
        return this;
    }

    public RemoveFacesFromPersonRequest withPersonId(String str) {
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
        if (!(cloudDriveRequest instanceof RemoveFacesFromPersonRequest)) {
            return 1;
        }
        RemoveFacesFromPersonRequest removeFacesFromPersonRequest = (RemoveFacesFromPersonRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getPersonId(), removeFacesFromPersonRequest.getPersonId());
        if (compare != 0) {
            return compare;
        }
        int compareCollections = ObjectComparator.compareCollections(getFaces(), removeFacesFromPersonRequest.getFaces());
        if (compareCollections == 0) {
            return 0;
        }
        return compareCollections;
    }
}
