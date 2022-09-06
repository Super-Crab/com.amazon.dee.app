package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.extended.model.BaseBatchNodeRequest;
import java.util.List;
/* loaded from: classes11.dex */
public class BatchRemoveNodeRequest extends BaseBatchNodeRequest {
    public BatchRemoveNodeRequest(List<String> list, List<String> list2, String str) {
        super(list, list2, BaseBatchNodeRequest.OperationType.REMOVE, str);
    }
}
