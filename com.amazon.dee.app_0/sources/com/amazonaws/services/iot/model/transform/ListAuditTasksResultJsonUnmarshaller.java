package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ListAuditTasksResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class ListAuditTasksResultJsonUnmarshaller implements Unmarshaller<ListAuditTasksResult, JsonUnmarshallerContext> {
    private static ListAuditTasksResultJsonUnmarshaller instance;

    public static ListAuditTasksResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ListAuditTasksResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ListAuditTasksResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        ListAuditTasksResult listAuditTasksResult = new ListAuditTasksResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("tasks")) {
                listAuditTasksResult.setTasks(new ListUnmarshaller(AuditTaskMetadataJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("nextToken")) {
                listAuditTasksResult.setNextToken(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return listAuditTasksResult;
    }
}
