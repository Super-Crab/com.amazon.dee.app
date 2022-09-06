package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
import com.amazon.clouddrive.model.ObjectComparator;
import java.util.List;
/* loaded from: classes11.dex */
public class BaseBatchNodeResponse implements BatchNodeResponse {
    private List<BatchNodeError> errors;
    private List<GroupCollection> groupCollections;
    private List<NodeExtended> nodes;

    protected boolean canEqual(Object obj) {
        return obj instanceof BaseBatchNodeResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BaseBatchNodeResponse)) {
            return false;
        }
        BaseBatchNodeResponse baseBatchNodeResponse = (BaseBatchNodeResponse) obj;
        if (!baseBatchNodeResponse.canEqual(this)) {
            return false;
        }
        List<BatchNodeError> errors = getErrors();
        List<BatchNodeError> errors2 = baseBatchNodeResponse.getErrors();
        if (errors != null ? !errors.equals(errors2) : errors2 != null) {
            return false;
        }
        List<NodeExtended> nodes = getNodes();
        List<NodeExtended> nodes2 = baseBatchNodeResponse.getNodes();
        if (nodes != null ? !nodes.equals(nodes2) : nodes2 != null) {
            return false;
        }
        List<GroupCollection> groupCollections = getGroupCollections();
        List<GroupCollection> groupCollections2 = baseBatchNodeResponse.getGroupCollections();
        return groupCollections != null ? groupCollections.equals(groupCollections2) : groupCollections2 == null;
    }

    @Override // com.amazon.clouddrive.extended.model.BatchNodeResponse
    public List<BatchNodeError> getErrors() {
        return this.errors;
    }

    @Override // com.amazon.clouddrive.extended.model.BatchNodeResponse
    public List<GroupCollection> getGroupCollections() {
        return this.groupCollections;
    }

    @Override // com.amazon.clouddrive.extended.model.BatchNodeResponse
    public List<NodeExtended> getNodes() {
        return this.nodes;
    }

    public int hashCode() {
        List<BatchNodeError> errors = getErrors();
        int i = 43;
        int hashCode = errors == null ? 43 : errors.hashCode();
        List<NodeExtended> nodes = getNodes();
        int hashCode2 = ((hashCode + 59) * 59) + (nodes == null ? 43 : nodes.hashCode());
        List<GroupCollection> groupCollections = getGroupCollections();
        int i2 = hashCode2 * 59;
        if (groupCollections != null) {
            i = groupCollections.hashCode();
        }
        return i2 + i;
    }

    @Override // com.amazon.clouddrive.extended.model.BatchNodeResponse
    public void setErrors(List<BatchNodeError> list) {
        this.errors = list;
    }

    @Override // com.amazon.clouddrive.extended.model.BatchNodeResponse
    public void setGroupCollections(List<GroupCollection> list) {
        this.groupCollections = list;
    }

    @Override // com.amazon.clouddrive.extended.model.BatchNodeResponse
    public void setNodes(List<NodeExtended> list) {
        this.nodes = list;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (!(cloudDriveResponse instanceof BaseBatchNodeResponse)) {
            return 1;
        }
        BaseBatchNodeResponse baseBatchNodeResponse = (BaseBatchNodeResponse) cloudDriveResponse;
        int compareCollections = ObjectComparator.compareCollections(getErrors(), baseBatchNodeResponse.getErrors());
        if (compareCollections != 0) {
            return compareCollections;
        }
        int compareCollections2 = ObjectComparator.compareCollections(getNodes(), baseBatchNodeResponse.getNodes());
        if (compareCollections2 != 0) {
            return compareCollections2;
        }
        int compareCollections3 = ObjectComparator.compareCollections(getGroupCollections(), baseBatchNodeResponse.getGroupCollections());
        if (compareCollections3 == 0) {
            return 0;
        }
        return compareCollections3;
    }
}
