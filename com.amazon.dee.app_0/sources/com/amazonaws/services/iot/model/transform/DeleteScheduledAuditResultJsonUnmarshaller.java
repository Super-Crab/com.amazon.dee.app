package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeleteScheduledAuditResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class DeleteScheduledAuditResultJsonUnmarshaller implements Unmarshaller<DeleteScheduledAuditResult, JsonUnmarshallerContext> {
    private static DeleteScheduledAuditResultJsonUnmarshaller instance;

    public static DeleteScheduledAuditResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteScheduledAuditResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DeleteScheduledAuditResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeleteScheduledAuditResult();
    }
}
