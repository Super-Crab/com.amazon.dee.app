package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.ExportTaskStatus;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class ExportTaskStatusJsonUnmarshaller implements Unmarshaller<ExportTaskStatus, JsonUnmarshallerContext> {
    private static ExportTaskStatusJsonUnmarshaller instance;

    ExportTaskStatusJsonUnmarshaller() {
    }

    public static ExportTaskStatusJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ExportTaskStatusJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ExportTaskStatus unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ExportTaskStatus exportTaskStatus = new ExportTaskStatus();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("code")) {
                exportTaskStatus.setCode(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("message")) {
                exportTaskStatus.setMessage(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return exportTaskStatus;
    }
}
