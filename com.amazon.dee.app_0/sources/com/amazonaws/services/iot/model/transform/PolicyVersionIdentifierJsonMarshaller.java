package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.PolicyVersionIdentifier;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class PolicyVersionIdentifierJsonMarshaller {
    private static PolicyVersionIdentifierJsonMarshaller instance;

    PolicyVersionIdentifierJsonMarshaller() {
    }

    public static PolicyVersionIdentifierJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new PolicyVersionIdentifierJsonMarshaller();
        }
        return instance;
    }

    public void marshall(PolicyVersionIdentifier policyVersionIdentifier, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (policyVersionIdentifier.getPolicyName() != null) {
            String policyName = policyVersionIdentifier.getPolicyName();
            awsJsonWriter.name("policyName");
            awsJsonWriter.value(policyName);
        }
        if (policyVersionIdentifier.getPolicyVersionId() != null) {
            String policyVersionId = policyVersionIdentifier.getPolicyVersionId();
            awsJsonWriter.name("policyVersionId");
            awsJsonWriter.value(policyVersionId);
        }
        awsJsonWriter.endObject();
    }
}
