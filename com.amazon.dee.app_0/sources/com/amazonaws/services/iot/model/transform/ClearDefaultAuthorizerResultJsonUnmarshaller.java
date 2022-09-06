package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ClearDefaultAuthorizerResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class ClearDefaultAuthorizerResultJsonUnmarshaller implements Unmarshaller<ClearDefaultAuthorizerResult, JsonUnmarshallerContext> {
    private static ClearDefaultAuthorizerResultJsonUnmarshaller instance;

    public static ClearDefaultAuthorizerResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ClearDefaultAuthorizerResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ClearDefaultAuthorizerResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new ClearDefaultAuthorizerResult();
    }
}
