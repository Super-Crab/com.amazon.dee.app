package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.TaskStatistics;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class TaskStatisticsJsonMarshaller {
    private static TaskStatisticsJsonMarshaller instance;

    TaskStatisticsJsonMarshaller() {
    }

    public static TaskStatisticsJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new TaskStatisticsJsonMarshaller();
        }
        return instance;
    }

    public void marshall(TaskStatistics taskStatistics, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (taskStatistics.getTotalChecks() != null) {
            Integer totalChecks = taskStatistics.getTotalChecks();
            awsJsonWriter.name("totalChecks");
            awsJsonWriter.value(totalChecks);
        }
        if (taskStatistics.getInProgressChecks() != null) {
            Integer inProgressChecks = taskStatistics.getInProgressChecks();
            awsJsonWriter.name("inProgressChecks");
            awsJsonWriter.value(inProgressChecks);
        }
        if (taskStatistics.getWaitingForDataCollectionChecks() != null) {
            Integer waitingForDataCollectionChecks = taskStatistics.getWaitingForDataCollectionChecks();
            awsJsonWriter.name("waitingForDataCollectionChecks");
            awsJsonWriter.value(waitingForDataCollectionChecks);
        }
        if (taskStatistics.getCompliantChecks() != null) {
            Integer compliantChecks = taskStatistics.getCompliantChecks();
            awsJsonWriter.name("compliantChecks");
            awsJsonWriter.value(compliantChecks);
        }
        if (taskStatistics.getNonCompliantChecks() != null) {
            Integer nonCompliantChecks = taskStatistics.getNonCompliantChecks();
            awsJsonWriter.name("nonCompliantChecks");
            awsJsonWriter.value(nonCompliantChecks);
        }
        if (taskStatistics.getFailedChecks() != null) {
            Integer failedChecks = taskStatistics.getFailedChecks();
            awsJsonWriter.name("failedChecks");
            awsJsonWriter.value(failedChecks);
        }
        if (taskStatistics.getCanceledChecks() != null) {
            Integer canceledChecks = taskStatistics.getCanceledChecks();
            awsJsonWriter.name("canceledChecks");
            awsJsonWriter.value(canceledChecks);
        }
        awsJsonWriter.endObject();
    }
}
