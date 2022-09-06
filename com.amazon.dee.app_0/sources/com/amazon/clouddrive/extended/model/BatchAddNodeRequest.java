package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.extended.model.BaseBatchNodeRequest;
import java.util.List;
/* loaded from: classes11.dex */
public class BatchAddNodeRequest extends BaseBatchNodeRequest {
    public BatchAddNodeRequest(List<String> list, List<String> list2, String str) {
        super(list, list2, BaseBatchNodeRequest.OperationType.ADD, str);
    }
}
