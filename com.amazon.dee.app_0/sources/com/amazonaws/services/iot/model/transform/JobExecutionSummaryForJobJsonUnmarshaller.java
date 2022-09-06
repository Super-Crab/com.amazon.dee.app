package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.JobExecutionSummaryForJob;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class JobExecutionSummaryForJobJsonUnmarshaller implements Unmarshaller<JobExecutionSummaryForJob, JsonUnmarshallerContext> {
    private static JobExecutionSummaryForJobJsonUnmarshaller instance;

    JobExecutionSummaryForJobJsonUnmarshaller() {
    }

    public static JobExecutionSummaryForJobJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new JobExecutionSummaryForJobJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public JobExecutionSummaryForJob unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        JobExecutionSummaryForJob jobExecutionSummaryForJob = new JobExecutionSummaryForJob();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("thingArn")) {
                jobExecutionSummaryForJob.setThingArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("jobExecutionSummary")) {
                jobExecutionSummaryForJob.setJobExecutionSummary(JobExecutionSummaryJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return jobExecutionSummaryForJob;
    }
}
