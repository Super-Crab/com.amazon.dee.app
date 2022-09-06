package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveRequest;
import java.util.List;
/* loaded from: classes11.dex */
public interface BulkAddRemoveRequest extends CloudDriveRequest {
    List<String> getNodeIds();

    String getOp();

    String getResourceVersion();

    void setNodeIds(List<String> list);

    void setOp(String str);

    void setResourceVersion(String str);
}
