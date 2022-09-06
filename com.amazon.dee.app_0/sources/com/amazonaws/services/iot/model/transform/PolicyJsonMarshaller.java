package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.Policy;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class PolicyJsonMarshaller {
    private static PolicyJsonMarshaller instance;

    PolicyJsonMarshaller() {
    }

    public static PolicyJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new PolicyJsonMarshaller();
        }
        return instance;
    }

    public void marshall(Policy policy, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (policy.getPolicyName() != null) {
            String policyName = policy.getPolicyName();
            awsJsonWriter.name("policyName");
            awsJsonWriter.value(policyName);
        }
        if (policy.getPolicyArn() != null) {
            String policyArn = policy.getPolicyArn();
            awsJsonWriter.name("policyArn");
            awsJsonWriter.value(policyArn);
        }
        awsJsonWriter.endObject();
    }
}
