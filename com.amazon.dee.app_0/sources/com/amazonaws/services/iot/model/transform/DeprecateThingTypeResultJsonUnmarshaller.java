package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeprecateThingTypeResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class DeprecateThingTypeResultJsonUnmarshaller implements Unmarshaller<DeprecateThingTypeResult, JsonUnmarshallerContext> {
    private static DeprecateThingTypeResultJsonUnmarshaller instance;

    public static DeprecateThingTypeResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeprecateThingTypeResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DeprecateThingTypeResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeprecateThingTypeResult();
    }
}
