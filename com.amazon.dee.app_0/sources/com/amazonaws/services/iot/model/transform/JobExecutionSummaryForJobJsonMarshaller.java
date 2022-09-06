package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.JobExecutionSummary;
import com.amazonaws.services.iot.model.JobExecutionSummaryForJob;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class JobExecutionSummaryForJobJsonMarshaller {
    private static JobExecutionSummaryForJobJsonMarshaller instance;

    JobExecutionSummaryForJobJsonMarshaller() {
    }

    public static JobExecutionSummaryForJobJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new JobExecutionSummaryForJobJsonMarshaller();
        }
        return instance;
    }

    public void marshall(JobExecutionSummaryForJob jobExecutionSummaryForJob, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (jobExecutionSummaryForJob.getThingArn() != null) {
            String thingArn = jobExecutionSummaryForJob.getThingArn();
            awsJsonWriter.name("thingArn");
            awsJsonWriter.value(thingArn);
        }
        if (jobExecutionSummaryForJob.getJobExecutionSummary() != null) {
            JobExecutionSummary jobExecutionSummary = jobExecutionSummaryForJob.getJobExecutionSummary();
            awsJsonWriter.name("jobExecutionSummary");
            JobExecutionSummaryJsonMarshaller.getInstance().marshall(jobExecutionSummary, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }
}
