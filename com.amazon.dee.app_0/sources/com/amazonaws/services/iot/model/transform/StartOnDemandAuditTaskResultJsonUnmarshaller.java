package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.StartOnDemandAuditTaskResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class StartOnDemandAuditTaskResultJsonUnmarshaller implements Unmarshaller<StartOnDemandAuditTaskResult, JsonUnmarshallerContext> {
    private static StartOnDemandAuditTaskResultJsonUnmarshaller instance;

    public static StartOnDemandAuditTaskResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new StartOnDemandAuditTaskResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public StartOnDemandAuditTaskResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        StartOnDemandAuditTaskResult startOnDemandAuditTaskResult = new StartOnDemandAuditTaskResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("taskId")) {
                startOnDemandAuditTaskResult.setTaskId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return startOnDemandAuditTaskResult;
    }
}
