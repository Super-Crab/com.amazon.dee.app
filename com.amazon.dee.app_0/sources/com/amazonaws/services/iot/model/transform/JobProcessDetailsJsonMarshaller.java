package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.JobProcessDetails;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;
/* loaded from: classes13.dex */
class JobProcessDetailsJsonMarshaller {
    private static JobProcessDetailsJsonMarshaller instance;

    JobProcessDetailsJsonMarshaller() {
    }

    public static JobProcessDetailsJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new JobProcessDetailsJsonMarshaller();
        }
        return instance;
    }

    public void marshall(JobProcessDetails jobProcessDetails, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (jobProcessDetails.getProcessingTargets() != null) {
            List<String> processingTargets = jobProcessDetails.getProcessingTargets();
            awsJsonWriter.name("processingTargets");
            awsJsonWriter.beginArray();
            for (String str : processingTargets) {
                if (str != null) {
                    awsJsonWriter.value(str);
                }
            }
            awsJsonWriter.endArray();
        }
        if (jobProcessDetails.getNumberOfCanceledThings() != null) {
            Integer numberOfCanceledThings = jobProcessDetails.getNumberOfCanceledThings();
            awsJsonWriter.name("numberOfCanceledThings");
            awsJsonWriter.value(numberOfCanceledThings);
        }
        if (jobProcessDetails.getNumberOfSucceededThings() != null) {
            Integer numberOfSucceededThings = jobProcessDetails.getNumberOfSucceededThings();
            awsJsonWriter.name("numberOfSucceededThings");
            awsJsonWriter.value(numberOfSucceededThings);
        }
        if (jobProcessDetails.getNumberOfFailedThings() != null) {
            Integer numberOfFailedThings = jobProcessDetails.getNumberOfFailedThings();
            awsJsonWriter.name("numberOfFailedThings");
            awsJsonWriter.value(numberOfFailedThings);
        }
        if (jobProcessDetails.getNumberOfRejectedThings() != null) {
            Integer numberOfRejectedThings = jobProcessDetails.getNumberOfRejectedThings();
            awsJsonWriter.name("numberOfRejectedThings");
            awsJsonWriter.value(numberOfRejectedThings);
        }
        if (jobProcessDetails.getNumberOfQueuedThings() != null) {
            Integer numberOfQueuedThings = jobProcessDetails.getNumberOfQueuedThings();
            awsJsonWriter.name("numberOfQueuedThings");
            awsJsonWriter.value(numberOfQueuedThings);
        }
        if (jobProcessDetails.getNumberOfInProgressThings() != null) {
            Integer numberOfInProgressThings = jobProcessDetails.getNumberOfInProgressThings();
            awsJsonWriter.name("numberOfInProgressThings");
            awsJsonWriter.value(numberOfInProgressThings);
        }
        if (jobProcessDetails.getNumberOfRemovedThings() != null) {
            Integer numberOfRemovedThings = jobProcessDetails.getNumberOfRemovedThings();
            awsJsonWriter.name("numberOfRemovedThings");
            awsJsonWriter.value(numberOfRemovedThings);
        }
        if (jobProcessDetails.getNumberOfTimedOutThings() != null) {
            Integer numberOfTimedOutThings = jobProcessDetails.getNumberOfTimedOutThings();
            awsJsonWriter.name("numberOfTimedOutThings");
            awsJsonWriter.value(numberOfTimedOutThings);
        }
        awsJsonWriter.endObject();
    }
}
