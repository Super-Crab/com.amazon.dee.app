package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeleteCACertificateResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class DeleteCACertificateResultJsonUnmarshaller implements Unmarshaller<DeleteCACertificateResult, JsonUnmarshallerContext> {
    private static DeleteCACertificateResultJsonUnmarshaller instance;

    public static DeleteCACertificateResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteCACertificateResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DeleteCACertificateResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeleteCACertificateResult();
    }
}
