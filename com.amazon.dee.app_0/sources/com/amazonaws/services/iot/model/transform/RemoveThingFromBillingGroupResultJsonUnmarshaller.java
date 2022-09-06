package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.RemoveThingFromBillingGroupResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class RemoveThingFromBillingGroupResultJsonUnmarshaller implements Unmarshaller<RemoveThingFromBillingGroupResult, JsonUnmarshallerContext> {
    private static RemoveThingFromBillingGroupResultJsonUnmarshaller instance;

    public static RemoveThingFromBillingGroupResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new RemoveThingFromBillingGroupResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public RemoveThingFromBillingGroupResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new RemoveThingFromBillingGroupResult();
    }
}
