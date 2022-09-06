package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class PostNodeRequest extends EditableNodeRequest {
    private String conflictResolution;
    private String localId;

    public int compareTo(EditableNodeRequest editableNodeRequest) {
        if (editableNodeRequest == null) {
            return -1;
        }
        if (editableNodeRequest == this) {
            return 0;
        }
        if (!(editableNodeRequest instanceof PostNodeRequest)) {
            return 1;
        }
        PostNodeRequest postNodeRequest = (PostNodeRequest) editableNodeRequest;
        String localId = getLocalId();
        String localId2 = postNodeRequest.getLocalId();
        if (localId != localId2) {
            if (localId == null) {
                return -1;
            }
            if (localId2 == null) {
                return 1;
            }
            int compareTo = localId.compareTo(localId2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String conflictResolution = getConflictResolution();
        String conflictResolution2 = postNodeRequest.getConflictResolution();
        if (conflictResolution != conflictResolution2) {
            if (conflictResolution == null) {
                return -1;
            }
            if (conflictResolution2 == null) {
                return 1;
            }
            int compareTo2 = conflictResolution.compareTo(conflictResolution2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        return super.compareTo((CloudDriveRequest) editableNodeRequest);
    }

    @Override // com.amazon.clouddrive.model.EditableNodeRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof PostNodeRequest) && compareTo((EditableNodeRequest) ((PostNodeRequest) obj)) == 0;
    }

    public String getConflictResolution() {
        return this.conflictResolution;
    }

    public String getLocalId() {
        return this.localId;
    }

    @Override // com.amazon.clouddrive.model.EditableNodeRequest
    public int hashCode() {
        return (((getLocalId() == null ? 0 : getLocalId().hashCode()) + 1) * 31) + super.hashCode();
    }

    public void setConflictResolution(String str) {
        this.conflictResolution = str;
    }

    public void setLocalId(String str) {
        this.localId = str;
    }
}
