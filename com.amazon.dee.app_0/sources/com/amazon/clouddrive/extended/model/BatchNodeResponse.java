package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
import java.util.List;
/* loaded from: classes11.dex */
public interface BatchNodeResponse extends CloudDriveResponse {
    List<BatchNodeError> getErrors();

    List<GroupCollection> getGroupCollections();

    List<NodeExtended> getNodes();

    void setErrors(List<BatchNodeError> list);

    void setGroupCollections(List<GroupCollection> list);

    void setNodes(List<NodeExtended> list);
}
