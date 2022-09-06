package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeleteAuthorizerResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class DeleteAuthorizerResultJsonUnmarshaller implements Unmarshaller<DeleteAuthorizerResult, JsonUnmarshallerContext> {
    private static DeleteAuthorizerResultJsonUnmarshaller instance;

    public static DeleteAuthorizerResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteAuthorizerResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DeleteAuthorizerResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeleteAuthorizerResult();
    }
}
