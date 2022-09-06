package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AwsJobExecutionsRolloutConfig;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class AwsJobExecutionsRolloutConfigJsonMarshaller {
    private static AwsJobExecutionsRolloutConfigJsonMarshaller instance;

    AwsJobExecutionsRolloutConfigJsonMarshaller() {
    }

    public static AwsJobExecutionsRolloutConfigJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AwsJobExecutionsRolloutConfigJsonMarshaller();
        }
        return instance;
    }

    public void marshall(AwsJobExecutionsRolloutConfig awsJobExecutionsRolloutConfig, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (awsJobExecutionsRolloutConfig.getMaximumPerMinute() != null) {
            Integer maximumPerMinute = awsJobExecutionsRolloutConfig.getMaximumPerMinute();
            awsJsonWriter.name("maximumPerMinute");
            awsJsonWriter.value(maximumPerMinute);
        }
        awsJsonWriter.endObject();
    }
}
