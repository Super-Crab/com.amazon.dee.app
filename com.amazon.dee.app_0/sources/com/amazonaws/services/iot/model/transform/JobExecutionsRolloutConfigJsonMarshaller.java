package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ExponentialRolloutRate;
import com.amazonaws.services.iot.model.JobExecutionsRolloutConfig;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class JobExecutionsRolloutConfigJsonMarshaller {
    private static JobExecutionsRolloutConfigJsonMarshaller instance;

    JobExecutionsRolloutConfigJsonMarshaller() {
    }

    public static JobExecutionsRolloutConfigJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new JobExecutionsRolloutConfigJsonMarshaller();
        }
        return instance;
    }

    public void marshall(JobExecutionsRolloutConfig jobExecutionsRolloutConfig, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (jobExecutionsRolloutConfig.getMaximumPerMinute() != null) {
            Integer maximumPerMinute = jobExecutionsRolloutConfig.getMaximumPerMinute();
            awsJsonWriter.name("maximumPerMinute");
            awsJsonWriter.value(maximumPerMinute);
        }
        if (jobExecutionsRolloutConfig.getExponentialRate() != null) {
            ExponentialRolloutRate exponentialRate = jobExecutionsRolloutConfig.getExponentialRate();
            awsJsonWriter.name("exponentialRate");
            ExponentialRolloutRateJsonMarshaller.getInstance().marshall(exponentialRate, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }
}
