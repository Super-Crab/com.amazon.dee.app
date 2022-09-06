package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.ExportTaskStatus;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class ExportTaskStatusJsonMarshaller {
    private static ExportTaskStatusJsonMarshaller instance;

    ExportTaskStatusJsonMarshaller() {
    }

    public static ExportTaskStatusJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ExportTaskStatusJsonMarshaller();
        }
        return instance;
    }

    public void marshall(ExportTaskStatus exportTaskStatus, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (exportTaskStatus.getCode() != null) {
            String code = exportTaskStatus.getCode();
            awsJsonWriter.name("code");
            awsJsonWriter.value(code);
        }
        if (exportTaskStatus.getMessage() != null) {
            String message = exportTaskStatus.getMessage();
            awsJsonWriter.name("message");
            awsJsonWriter.value(message);
        }
        awsJsonWriter.endObject();
    }
}
