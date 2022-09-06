package com.amazon.clouddrive.model;

import com.amazon.clouddrive.model.INode;
import java.util.List;
/* loaded from: classes11.dex */
public interface IGetChangesResponse<T extends INode> extends CloudDriveResponse {
    String getCheckpoint();

    List<T> getNodes();

    boolean isReset();

    void setCheckpoint(String str);

    void setNodes(List<T> list);

    void setReset(boolean z);
}
