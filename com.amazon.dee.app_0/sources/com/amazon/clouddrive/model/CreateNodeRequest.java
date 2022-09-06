package com.amazon.clouddrive.model;

import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class CreateNodeRequest extends PostNodeRequest {
    public CreateNodeRequest(String str, String str2) {
        setName(str);
        setKind(str2);
    }

    @Override // com.amazon.clouddrive.model.PostNodeRequest
    public int compareTo(EditableNodeRequest editableNodeRequest) {
        if (editableNodeRequest == null) {
            return -1;
        }
        if (editableNodeRequest == this) {
            return 0;
        }
        if (editableNodeRequest instanceof CreateNodeRequest) {
            return super.compareTo(editableNodeRequest);
        }
        return 1;
    }

    @Override // com.amazon.clouddrive.model.PostNodeRequest, com.amazon.clouddrive.model.EditableNodeRequest
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof CreateNodeRequest) && compareTo((EditableNodeRequest) ((CreateNodeRequest) obj)) == 0;
    }

    @Override // com.amazon.clouddrive.model.PostNodeRequest, com.amazon.clouddrive.model.EditableNodeRequest
    public int hashCode() {
        return 31 + super.hashCode();
    }

    public CreateNodeRequest withConflictResolution(String str) {
        setConflictResolution(str);
        return this;
    }

    public CreateNodeRequest withContentProperties(ContentProperties contentProperties) {
        setContentProperties(contentProperties);
        return this;
    }

    public CreateNodeRequest withDescription(String str) {
        setDescription(str);
        return this;
    }

    public CreateNodeRequest withKind(String str) {
        setKind(str);
        return this;
    }

    public CreateNodeRequest withLabels(List<String> list) {
        setLabels(list);
        return this;
    }

    public CreateNodeRequest withLocalId(String str) {
        setLocalId(str);
        return this;
    }

    public CreateNodeRequest withParents(List<String> list) {
        setParents(list);
        return this;
    }

    public CreateNodeRequest withProperties(Map<String, Map<String, String>> map) {
        setProperties(map);
        return this;
    }

    public CreateNodeRequest withStatus(String str) {
        setStatus(str);
        return this;
    }

    public CreateNodeRequest withSubKindProperties(Map<String, Map<String, String>> map) {
        setSubKindProperties(map);
        return this;
    }

    public CreateNodeRequest withSubKinds(List<String> list) {
        setSubKinds(list);
        return this;
    }

    public CreateNodeRequest withSymbolicNodeHeroId(String str) {
        setSymbolicNodeHeroId(str);
        return this;
    }
}
