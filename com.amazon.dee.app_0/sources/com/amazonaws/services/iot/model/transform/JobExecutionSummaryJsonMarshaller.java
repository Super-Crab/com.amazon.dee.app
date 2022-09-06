package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.JobExecutionSummary;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;
/* loaded from: classes13.dex */
class JobExecutionSummaryJsonMarshaller {
    private static JobExecutionSummaryJsonMarshaller instance;

    JobExecutionSummaryJsonMarshaller() {
    }

    public static JobExecutionSummaryJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new JobExecutionSummaryJsonMarshaller();
        }
        return instance;
    }

    public void marshall(JobExecutionSummary jobExecutionSummary, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (jobExecutionSummary.getStatus() != null) {
            String status = jobExecutionSummary.getStatus();
            awsJsonWriter.name("status");
            awsJsonWriter.value(status);
        }
        if (jobExecutionSummary.getQueuedAt() != null) {
            Date queuedAt = jobExecutionSummary.getQueuedAt();
            awsJsonWriter.name("queuedAt");
            awsJsonWriter.value(queuedAt);
        }
        if (jobExecutionSummary.getStartedAt() != null) {
            Date startedAt = jobExecutionSummary.getStartedAt();
            awsJsonWriter.name("startedAt");
            awsJsonWriter.value(startedAt);
        }
        if (jobExecutionSummary.getLastUpdatedAt() != null) {
            Date lastUpdatedAt = jobExecutionSummary.getLastUpdatedAt();
            awsJsonWriter.name("lastUpdatedAt");
            awsJsonWriter.value(lastUpdatedAt);
        }
        if (jobExecutionSummary.getExecutionNumber() != null) {
            Long executionNumber = jobExecutionSummary.getExecutionNumber();
            awsJsonWriter.name("executionNumber");
            awsJsonWriter.value(executionNumber);
        }
        awsJsonWriter.endObject();
    }
}
