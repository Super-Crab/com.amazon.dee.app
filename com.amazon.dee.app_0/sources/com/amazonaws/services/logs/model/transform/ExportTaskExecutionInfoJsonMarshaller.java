package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.ExportTaskExecutionInfo;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class ExportTaskExecutionInfoJsonMarshaller {
    private static ExportTaskExecutionInfoJsonMarshaller instance;

    ExportTaskExecutionInfoJsonMarshaller() {
    }

    public static ExportTaskExecutionInfoJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ExportTaskExecutionInfoJsonMarshaller();
        }
        return instance;
    }

    public void marshall(ExportTaskExecutionInfo exportTaskExecutionInfo, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (exportTaskExecutionInfo.getCreationTime() != null) {
            Long creationTime = exportTaskExecutionInfo.getCreationTime();
            awsJsonWriter.name("creationTime");
            awsJsonWriter.value(creationTime);
        }
        if (exportTaskExecutionInfo.getCompletionTime() != null) {
            Long completionTime = exportTaskExecutionInfo.getCompletionTime();
            awsJsonWriter.name("completionTime");
            awsJsonWriter.value(completionTime);
        }
        awsJsonWriter.endObject();
    }
}
