package com.amazon.clouddrive.model;

import com.amazon.clouddrive.model.INode;
import java.util.List;
/* loaded from: classes11.dex */
public interface NodeListingResponse<T extends INode> extends CloudDriveResponse {
    long getCount();

    List<T> getData();

    String getNextToken();

    void setCount(long j);

    void setData(List<T> list);

    void setNextToken(String str);
}
