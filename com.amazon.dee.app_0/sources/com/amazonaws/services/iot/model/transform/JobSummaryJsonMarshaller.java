package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.JobSummary;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;
/* loaded from: classes13.dex */
class JobSummaryJsonMarshaller {
    private static JobSummaryJsonMarshaller instance;

    JobSummaryJsonMarshaller() {
    }

    public static JobSummaryJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new JobSummaryJsonMarshaller();
        }
        return instance;
    }

    public void marshall(JobSummary jobSummary, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (jobSummary.getJobArn() != null) {
            String jobArn = jobSummary.getJobArn();
            awsJsonWriter.name("jobArn");
            awsJsonWriter.value(jobArn);
        }
        if (jobSummary.getJobId() != null) {
            String jobId = jobSummary.getJobId();
            awsJsonWriter.name("jobId");
            awsJsonWriter.value(jobId);
        }
        if (jobSummary.getThingGroupId() != null) {
            String thingGroupId = jobSummary.getThingGroupId();
            awsJsonWriter.name("thingGroupId");
            awsJsonWriter.value(thingGroupId);
        }
        if (jobSummary.getTargetSelection() != null) {
            String targetSelection = jobSummary.getTargetSelection();
            awsJsonWriter.name("targetSelection");
            awsJsonWriter.value(targetSelection);
        }
        if (jobSummary.getStatus() != null) {
            String status = jobSummary.getStatus();
            awsJsonWriter.name("status");
            awsJsonWriter.value(status);
        }
        if (jobSummary.getCreatedAt() != null) {
            Date createdAt = jobSummary.getCreatedAt();
            awsJsonWriter.name("createdAt");
            awsJsonWriter.value(createdAt);
        }
        if (jobSummary.getLastUpdatedAt() != null) {
            Date lastUpdatedAt = jobSummary.getLastUpdatedAt();
            awsJsonWriter.name("lastUpdatedAt");
            awsJsonWriter.value(lastUpdatedAt);
        }
        if (jobSummary.getCompletedAt() != null) {
            Date completedAt = jobSummary.getCompletedAt();
            awsJsonWriter.name("completedAt");
            awsJsonWriter.value(completedAt);
        }
        awsJsonWriter.endObject();
    }
}
