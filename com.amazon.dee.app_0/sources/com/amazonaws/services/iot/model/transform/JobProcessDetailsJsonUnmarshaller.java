package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.JobProcessDetails;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class JobProcessDetailsJsonUnmarshaller implements Unmarshaller<JobProcessDetails, JsonUnmarshallerContext> {
    private static JobProcessDetailsJsonUnmarshaller instance;

    JobProcessDetailsJsonUnmarshaller() {
    }

    public static JobProcessDetailsJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new JobProcessDetailsJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public JobProcessDetails unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        JobProcessDetails jobProcessDetails = new JobProcessDetails();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("processingTargets")) {
                jobProcessDetails.setProcessingTargets(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("numberOfCanceledThings")) {
                jobProcessDetails.setNumberOfCanceledThings(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("numberOfSucceededThings")) {
                jobProcessDetails.setNumberOfSucceededThings(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("numberOfFailedThings")) {
                jobProcessDetails.setNumberOfFailedThings(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("numberOfRejectedThings")) {
                jobProcessDetails.setNumberOfRejectedThings(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("numberOfQueuedThings")) {
                jobProcessDetails.setNumberOfQueuedThings(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("numberOfInProgressThings")) {
                jobProcessDetails.setNumberOfInProgressThings(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("numberOfRemovedThings")) {
                jobProcessDetails.setNumberOfRemovedThings(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("numberOfTimedOutThings")) {
                jobProcessDetails.setNumberOfTimedOutThings(SimpleTypeJsonUnmarshallers.IntegerJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return jobProcessDetails;
    }
}
