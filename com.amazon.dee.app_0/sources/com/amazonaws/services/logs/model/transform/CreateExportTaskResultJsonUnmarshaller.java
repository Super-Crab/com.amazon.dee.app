package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.CreateExportTaskResult;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
public class CreateExportTaskResultJsonUnmarshaller implements Unmarshaller<CreateExportTaskResult, JsonUnmarshallerContext> {
    private static CreateExportTaskResultJsonUnmarshaller instance;

    public static CreateExportTaskResultJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new CreateExportTaskResultJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public CreateExportTaskResult unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        CreateExportTaskResult createExportTaskResult = new CreateExportTaskResult();
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("taskId")) {
                createExportTaskResult.setTaskId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return createExportTaskResult;
    }
}
