package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.EffectivePolicy;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class EffectivePolicyJsonMarshaller {
    private static EffectivePolicyJsonMarshaller instance;

    EffectivePolicyJsonMarshaller() {
    }

    public static EffectivePolicyJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new EffectivePolicyJsonMarshaller();
        }
        return instance;
    }

    public void marshall(EffectivePolicy effectivePolicy, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (effectivePolicy.getPolicyName() != null) {
            String policyName = effectivePolicy.getPolicyName();
            awsJsonWriter.name("policyName");
            awsJsonWriter.value(policyName);
        }
        if (effectivePolicy.getPolicyArn() != null) {
            String policyArn = effectivePolicy.getPolicyArn();
            awsJsonWriter.name("policyArn");
            awsJsonWriter.value(policyArn);
        }
        if (effectivePolicy.getPolicyDocument() != null) {
            String policyDocument = effectivePolicy.getPolicyDocument();
            awsJsonWriter.name("policyDocument");
            awsJsonWriter.value(policyDocument);
        }
        awsJsonWriter.endObject();
    }
}
