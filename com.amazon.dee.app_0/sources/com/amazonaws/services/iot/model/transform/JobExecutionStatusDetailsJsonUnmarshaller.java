package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.JobExecutionStatusDetails;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class JobExecutionStatusDetailsJsonUnmarshaller implements Unmarshaller<JobExecutionStatusDetails, JsonUnmarshallerContext> {
    private static JobExecutionStatusDetailsJsonUnmarshaller instance;

    JobExecutionStatusDetailsJsonUnmarshaller() {
    }

    public static JobExecutionStatusDetailsJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new JobExecutionStatusDetailsJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public JobExecutionStatusDetails unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        JobExecutionStatusDetails jobExecutionStatusDetails = new JobExecutionStatusDetails();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("detailsMap")) {
                jobExecutionStatusDetails.setDetailsMap(new MapUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return jobExecutionStatusDetails;
    }
}
