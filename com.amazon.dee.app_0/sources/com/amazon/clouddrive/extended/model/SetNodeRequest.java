package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import com.amazon.clouddrive.model.ContentProperties;
import com.amazon.clouddrive.model.EditableNodeRequest;
import com.amazon.clouddrive.model.ObjectComparator;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class SetNodeRequest extends EditableNodeRequest implements EditableNodeExtended {
    private String localId;
    private Boolean restricted;

    public SetNodeRequest(String str, String str2) {
        setId(str);
        setKind(str2);
    }

    public int compareTo(EditableNodeRequest editableNodeRequest) {
        if (editableNodeRequest == null) {
            return -1;
        }
        if (editableNodeRequest == this) {
            return 0;
        }
        if (!(editableNodeRequest instanceof SetNodeRequest)) {
            return 1;
        }
        SetNodeRequest setNodeRequest = (SetNodeRequest) editableNodeRequest;
        String localId = getLocalId();
        String localId2 = setNodeRequest.getLocalId();
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
        int compare = ObjectComparator.compare(isRestricted(), setNodeRequest.isRestricted());
        return compare != 0 ? compare : super.compareTo((CloudDriveRequest) editableNodeRequest);
    }

    @Override // com.amazon.clouddrive.model.EditableNodeRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof SetNodeRequest) && compareTo((EditableNodeRequest) ((SetNodeRequest) obj)) == 0;
    }

    public String getLocalId() {
        return this.localId;
    }

    @Override // com.amazon.clouddrive.model.EditableNodeRequest
    public int hashCode() {
        int i = 0;
        int hashCode = (getLocalId() == null ? 0 : getLocalId().hashCode()) + 1;
        if (isRestricted() != null) {
            i = isRestricted().hashCode();
        }
        return ((hashCode + i) * 31) + super.hashCode();
    }

    @Override // com.amazon.clouddrive.extended.model.EditableNodeExtended
    public Boolean isRestricted() {
        return this.restricted;
    }

    public void setLocalId(String str) {
        this.localId = str;
    }

    @Override // com.amazon.clouddrive.extended.model.EditableNodeExtended
    public void setRestricted(Boolean bool) {
        this.restricted = bool;
    }

    public SetNodeRequest withContentProperties(ContentProperties contentProperties) {
        setContentProperties(contentProperties);
        return this;
    }

    public SetNodeRequest withDescription(String str) {
        setDescription(str);
        return this;
    }

    public SetNodeRequest withKind(String str) {
        setKind(str);
        return this;
    }

    public SetNodeRequest withLabels(List<String> list) {
        setLabels(list);
        return this;
    }

    public SetNodeRequest withLocalId(String str) {
        setLocalId(str);
        return this;
    }

    public SetNodeRequest withParents(List<String> list) {
        setParents(list);
        return this;
    }

    public SetNodeRequest withProperties(Map<String, Map<String, String>> map) {
        setProperties(map);
        return this;
    }

    public SetNodeRequest withRestricted(Boolean bool) {
        setRestricted(bool);
        return this;
    }

    public SetNodeRequest withSubKindProperties(Map<String, Map<String, String>> map) {
        setSubKindProperties(map);
        return this;
    }
}
