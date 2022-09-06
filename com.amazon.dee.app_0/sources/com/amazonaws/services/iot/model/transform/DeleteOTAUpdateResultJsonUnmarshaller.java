package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeleteOTAUpdateResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class DeleteOTAUpdateResultJsonUnmarshaller implements Unmarshaller<DeleteOTAUpdateResult, JsonUnmarshallerContext> {
    private static DeleteOTAUpdateResultJsonUnmarshaller instance;

    public static DeleteOTAUpdateResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteOTAUpdateResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DeleteOTAUpdateResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeleteOTAUpdateResult();
    }
}
