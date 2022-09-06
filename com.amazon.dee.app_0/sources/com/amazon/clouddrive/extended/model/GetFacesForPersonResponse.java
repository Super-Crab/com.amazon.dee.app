package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
import com.amazon.clouddrive.model.ObjectComparator;
import java.util.List;
/* loaded from: classes11.dex */
public class GetFacesForPersonResponse implements CloudDriveResponse {
    private List<NodeFace> faces;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetFacesForPersonResponse) && compareTo((CloudDriveResponse) ((GetFacesForPersonResponse) obj)) == 0;
    }

    public List<NodeFace> getFaces() {
        return this.faces;
    }

    public int hashCode() {
        return (((getFaces() == null ? 0 : getFaces().hashCode()) + 1) * 31) + super.hashCode();
    }

    public void setFaces(List<NodeFace> list) {
        this.faces = list;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        int compareCollections;
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse != this && (compareCollections = ObjectComparator.compareCollections(getFaces(), ((GetFacesForPersonResponse) cloudDriveResponse).getFaces())) != 0) {
            return compareCollections;
        }
        return 0;
    }
}
