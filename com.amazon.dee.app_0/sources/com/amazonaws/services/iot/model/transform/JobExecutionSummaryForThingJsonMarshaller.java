package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.JobExecutionSummary;
import com.amazonaws.services.iot.model.JobExecutionSummaryForThing;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class JobExecutionSummaryForThingJsonMarshaller {
    private static JobExecutionSummaryForThingJsonMarshaller instance;

    JobExecutionSummaryForThingJsonMarshaller() {
    }

    public static JobExecutionSummaryForThingJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new JobExecutionSummaryForThingJsonMarshaller();
        }
        return instance;
    }

    public void marshall(JobExecutionSummaryForThing jobExecutionSummaryForThing, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (jobExecutionSummaryForThing.getJobId() != null) {
            String jobId = jobExecutionSummaryForThing.getJobId();
            awsJsonWriter.name("jobId");
            awsJsonWriter.value(jobId);
        }
        if (jobExecutionSummaryForThing.getJobExecutionSummary() != null) {
            JobExecutionSummary jobExecutionSummary = jobExecutionSummaryForThing.getJobExecutionSummary();
            awsJsonWriter.name("jobExecutionSummary");
            JobExecutionSummaryJsonMarshaller.getInstance().marshall(jobExecutionSummary, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }
}
