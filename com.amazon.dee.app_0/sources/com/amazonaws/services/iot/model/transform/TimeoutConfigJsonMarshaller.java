package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.TimeoutConfig;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class TimeoutConfigJsonMarshaller {
    private static TimeoutConfigJsonMarshaller instance;

    TimeoutConfigJsonMarshaller() {
    }

    public static TimeoutConfigJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new TimeoutConfigJsonMarshaller();
        }
        return instance;
    }

    public void marshall(TimeoutConfig timeoutConfig, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (timeoutConfig.getInProgressTimeoutInMinutes() != null) {
            Long inProgressTimeoutInMinutes = timeoutConfig.getInProgressTimeoutInMinutes();
            awsJsonWriter.name("inProgressTimeoutInMinutes");
            awsJsonWriter.value(inProgressTimeoutInMinutes);
        }
        awsJsonWriter.endObject();
    }
}
