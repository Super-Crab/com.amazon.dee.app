package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.UpdateIndexingConfigurationResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class UpdateIndexingConfigurationResultJsonUnmarshaller implements Unmarshaller<UpdateIndexingConfigurationResult, JsonUnmarshallerContext> {
    private static UpdateIndexingConfigurationResultJsonUnmarshaller instance;

    public static UpdateIndexingConfigurationResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateIndexingConfigurationResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateIndexingConfigurationResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new UpdateIndexingConfigurationResult();
    }
}
