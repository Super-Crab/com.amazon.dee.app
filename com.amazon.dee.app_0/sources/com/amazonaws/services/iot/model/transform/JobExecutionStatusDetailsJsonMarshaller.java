package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.JobExecutionStatusDetails;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Map;
/* loaded from: classes13.dex */
class JobExecutionStatusDetailsJsonMarshaller {
    private static JobExecutionStatusDetailsJsonMarshaller instance;

    JobExecutionStatusDetailsJsonMarshaller() {
    }

    public static JobExecutionStatusDetailsJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new JobExecutionStatusDetailsJsonMarshaller();
        }
        return instance;
    }

    public void marshall(JobExecutionStatusDetails jobExecutionStatusDetails, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (jobExecutionStatusDetails.getDetailsMap() != null) {
            Map<String, String> detailsMap = jobExecutionStatusDetails.getDetailsMap();
            awsJsonWriter.name("detailsMap");
            awsJsonWriter.beginObject();
            for (Map.Entry<String, String> entry : detailsMap.entrySet()) {
                String value = entry.getValue();
                if (value != null) {
                    awsJsonWriter.name(entry.getKey());
                    awsJsonWriter.value(value);
                }
            }
            awsJsonWriter.endObject();
        }
        awsJsonWriter.endObject();
    }
}
