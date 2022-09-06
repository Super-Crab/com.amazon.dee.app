package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.UpdateEventConfigurationsResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class UpdateEventConfigurationsResultJsonUnmarshaller implements Unmarshaller<UpdateEventConfigurationsResult, JsonUnmarshallerContext> {
    private static UpdateEventConfigurationsResultJsonUnmarshaller instance;

    public static UpdateEventConfigurationsResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateEventConfigurationsResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateEventConfigurationsResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new UpdateEventConfigurationsResult();
    }
}
