package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
import java.util.List;
/* loaded from: classes11.dex */
public class CreatePersonRequest implements CloudDriveRequest {
    private List<NodeFacePair> faceList;
    private String newPersonName;
    private String sourcePersonId;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof CreatePersonRequest) && compareTo((CloudDriveRequest) ((CreatePersonRequest) obj)) == 0;
    }

    public List<NodeFacePair> getFaceList() {
        return this.faceList;
    }

    public String getNewPersonName() {
        return this.newPersonName;
    }

    public String getSourcePersonId() {
        return this.sourcePersonId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getNewPersonName() == null ? 0 : getNewPersonName().hashCode()) + 1 + (getSourcePersonId() == null ? 0 : getSourcePersonId().hashCode());
        if (getFaceList() != null) {
            i = getFaceList().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setFaceList(List<NodeFacePair> list) {
        this.faceList = list;
    }

    public void setNewPersonName(String str) {
        this.newPersonName = str;
    }

    public void setSourcePersonId(String str) {
        this.sourcePersonId = str;
    }

    public CreatePersonRequest withFaceList(List<NodeFacePair> list) {
        setFaceList(list);
        return this;
    }

    public CreatePersonRequest withNewPersonName(String str) {
        setNewPersonName(str);
        return this;
    }

    public CreatePersonRequest withSourcePersonId(String str) {
        setSourcePersonId(str);
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
        if (!(cloudDriveRequest instanceof CreatePersonRequest)) {
            return 1;
        }
        CreatePersonRequest createPersonRequest = (CreatePersonRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getNewPersonName(), createPersonRequest.getNewPersonName());
        if (compare != 0) {
            return compare;
        }
        int compare2 = ObjectComparator.compare(getSourcePersonId(), createPersonRequest.getSourcePersonId());
        if (compare2 != 0) {
            return compare2;
        }
        int compareCollections = ObjectComparator.compareCollections(getFaceList(), createPersonRequest.getFaceList());
        if (compareCollections == 0) {
            return 0;
        }
        return compareCollections;
    }
}
