package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeleteBillingGroupResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class DeleteBillingGroupResultJsonUnmarshaller implements Unmarshaller<DeleteBillingGroupResult, JsonUnmarshallerContext> {
    private static DeleteBillingGroupResultJsonUnmarshaller instance;

    public static DeleteBillingGroupResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteBillingGroupResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DeleteBillingGroupResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeleteBillingGroupResult();
    }
}
