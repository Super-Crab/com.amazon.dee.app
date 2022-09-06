package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.ExportTaskExecutionInfo;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class ExportTaskExecutionInfoJsonUnmarshaller implements Unmarshaller<ExportTaskExecutionInfo, JsonUnmarshallerContext> {
    private static ExportTaskExecutionInfoJsonUnmarshaller instance;

    ExportTaskExecutionInfoJsonUnmarshaller() {
    }

    public static ExportTaskExecutionInfoJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ExportTaskExecutionInfoJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ExportTaskExecutionInfo unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ExportTaskExecutionInfo exportTaskExecutionInfo = new ExportTaskExecutionInfo();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("creationTime")) {
                exportTaskExecutionInfo.setCreationTime(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("completionTime")) {
                exportTaskExecutionInfo.setCompletionTime(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return exportTaskExecutionInfo;
    }
}
