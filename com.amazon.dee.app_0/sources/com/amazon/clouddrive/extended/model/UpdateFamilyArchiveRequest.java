package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ObjectComparator;
import java.util.List;
/* loaded from: classes11.dex */
public class UpdateFamilyArchiveRequest implements CloudDriveRequest {
    private String mFamilyId;
    private List<String> mNodeIdsToAdd;
    private List<String> mNodeIdsToRemove;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof UpdateFamilyArchiveRequest) && compareTo((CloudDriveRequest) ((UpdateFamilyArchiveRequest) obj)) == 0;
    }

    public String getFamilyId() {
        return this.mFamilyId;
    }

    public List<String> getNodesToAdd() {
        return this.mNodeIdsToAdd;
    }

    public List<String> getNodesToRemove() {
        return this.mNodeIdsToRemove;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getFamilyId() == null ? 0 : getFamilyId().hashCode()) + 1 + (getNodesToAdd() == null ? 0 : getNodesToAdd().hashCode());
        if (getNodesToRemove() != null) {
            i = getNodesToRemove().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    public void setFamilyId(String str) {
        this.mFamilyId = str;
    }

    public void setNodesToAdd(List<String> list) {
        this.mNodeIdsToAdd = list;
    }

    public void setNodesToRemove(List<String> list) {
        this.mNodeIdsToRemove = list;
    }

    public UpdateFamilyArchiveRequest withNodesToAdd(List<String> list) {
        setNodesToAdd(list);
        return this;
    }

    public UpdateFamilyArchiveRequest withNodesToRemove(List<String> list) {
        setNodesToAdd(list);
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
        if (!(cloudDriveRequest instanceof UpdateFamilyArchiveRequest)) {
            return 1;
        }
        UpdateFamilyArchiveRequest updateFamilyArchiveRequest = (UpdateFamilyArchiveRequest) cloudDriveRequest;
        int compare = ObjectComparator.compare(getFamilyId(), updateFamilyArchiveRequest.getFamilyId());
        if (compare != 0) {
            return compare;
        }
        int compareCollections = ObjectComparator.compareCollections(getNodesToAdd(), updateFamilyArchiveRequest.getNodesToAdd());
        if (compareCollections != 0) {
            return compareCollections;
        }
        int compareCollections2 = ObjectComparator.compareCollections(getNodesToRemove(), updateFamilyArchiveRequest.getNodesToRemove());
        if (compareCollections2 == 0) {
            return 0;
        }
        return compareCollections2;
    }
}
