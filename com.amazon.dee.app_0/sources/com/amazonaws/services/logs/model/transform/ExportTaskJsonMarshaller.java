package com.amazonaws.services.logs.model.transform;

import com.amazon.device.messaging.ADMConstants;
import com.amazonaws.services.logs.model.ExportTask;
import com.amazonaws.services.logs.model.ExportTaskExecutionInfo;
import com.amazonaws.services.logs.model.ExportTaskStatus;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class ExportTaskJsonMarshaller {
    private static ExportTaskJsonMarshaller instance;

    ExportTaskJsonMarshaller() {
    }

    public static ExportTaskJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ExportTaskJsonMarshaller();
        }
        return instance;
    }

    public void marshall(ExportTask exportTask, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (exportTask.getTaskId() != null) {
            String taskId = exportTask.getTaskId();
            awsJsonWriter.name("taskId");
            awsJsonWriter.value(taskId);
        }
        if (exportTask.getTaskName() != null) {
            String taskName = exportTask.getTaskName();
            awsJsonWriter.name("taskName");
            awsJsonWriter.value(taskName);
        }
        if (exportTask.getLogGroupName() != null) {
            String logGroupName = exportTask.getLogGroupName();
            awsJsonWriter.name("logGroupName");
            awsJsonWriter.value(logGroupName);
        }
        if (exportTask.getFrom() != null) {
            Long from = exportTask.getFrom();
            awsJsonWriter.name(ADMConstants.EXTRA_FROM);
            awsJsonWriter.value(from);
        }
        if (exportTask.getTo() != null) {
            Long to = exportTask.getTo();
            awsJsonWriter.name("to");
            awsJsonWriter.value(to);
        }
        if (exportTask.getDestination() != null) {
            String destination = exportTask.getDestination();
            awsJsonWriter.name("destination");
            awsJsonWriter.value(destination);
        }
        if (exportTask.getDestinationPrefix() != null) {
            String destinationPrefix = exportTask.getDestinationPrefix();
            awsJsonWriter.name("destinationPrefix");
            awsJsonWriter.value(destinationPrefix);
        }
        if (exportTask.getStatus() != null) {
            ExportTaskStatus status = exportTask.getStatus();
            awsJsonWriter.name("status");
            ExportTaskStatusJsonMarshaller.getInstance().marshall(status, awsJsonWriter);
        }
        if (exportTask.getExecutionInfo() != null) {
            ExportTaskExecutionInfo executionInfo = exportTask.getExecutionInfo();
            awsJsonWriter.name("executionInfo");
            ExportTaskExecutionInfoJsonMarshaller.getInstance().marshall(executionInfo, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }
}
