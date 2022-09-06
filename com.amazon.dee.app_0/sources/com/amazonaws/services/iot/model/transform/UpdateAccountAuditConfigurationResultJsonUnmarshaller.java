package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.UpdateAccountAuditConfigurationResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.Unmarshaller;
/* loaded from: classes13.dex */
public class UpdateAccountAuditConfigurationResultJsonUnmarshaller implements Unmarshaller<UpdateAccountAuditConfigurationResult, JsonUnmarshallerContext> {
    private static UpdateAccountAuditConfigurationResultJsonUnmarshaller instance;

    public static UpdateAccountAuditConfigurationResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new UpdateAccountAuditConfigurationResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public UpdateAccountAuditConfigurationResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        return new UpdateAccountAuditConfigurationResult();
    }
}
