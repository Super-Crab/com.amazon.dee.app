package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AbortCriteria;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class AbortCriteriaJsonMarshaller {
    private static AbortCriteriaJsonMarshaller instance;

    AbortCriteriaJsonMarshaller() {
    }

    public static AbortCriteriaJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AbortCriteriaJsonMarshaller();
        }
        return instance;
    }

    public void marshall(AbortCriteria abortCriteria, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (abortCriteria.getFailureType() != null) {
            String failureType = abortCriteria.getFailureType();
            awsJsonWriter.name("failureType");
            awsJsonWriter.value(failureType);
        }
        if (abortCriteria.getAction() != null) {
            String action = abortCriteria.getAction();
            awsJsonWriter.name("action");
            awsJsonWriter.value(action);
        }
        if (abortCriteria.getThresholdPercentage() != null) {
            Double thresholdPercentage = abortCriteria.getThresholdPercentage();
            awsJsonWriter.name("thresholdPercentage");
            awsJsonWriter.value(thresholdPercentage);
        }
        if (abortCriteria.getMinNumberOfExecutedThings() != null) {
            Integer minNumberOfExecutedThings = abortCriteria.getMinNumberOfExecutedThings();
            awsJsonWriter.name("minNumberOfExecutedThings");
            awsJsonWriter.value(minNumberOfExecutedThings);
        }
        awsJsonWriter.endObject();
    }
}
