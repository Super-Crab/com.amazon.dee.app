package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.DeleteAccountAuditConfigurationResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class DeleteAccountAuditConfigurationResultJsonUnmarshaller implements Unmarshaller<DeleteAccountAuditConfigurationResult, JsonUnmarshallerContext> {
    private static DeleteAccountAuditConfigurationResultJsonUnmarshaller instance;

    public static DeleteAccountAuditConfigurationResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteAccountAuditConfigurationResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public DeleteAccountAuditConfigurationResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new DeleteAccountAuditConfigurationResult();
    }
}
