package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.ResourcePolicy;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class ResourcePolicyJsonMarshaller {
    private static ResourcePolicyJsonMarshaller instance;

    ResourcePolicyJsonMarshaller() {
    }

    public static ResourcePolicyJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ResourcePolicyJsonMarshaller();
        }
        return instance;
    }

    public void marshall(ResourcePolicy resourcePolicy, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (resourcePolicy.getPolicyName() != null) {
            String policyName = resourcePolicy.getPolicyName();
            awsJsonWriter.name("policyName");
            awsJsonWriter.value(policyName);
        }
        if (resourcePolicy.getPolicyDocument() != null) {
            String policyDocument = resourcePolicy.getPolicyDocument();
            awsJsonWriter.name("policyDocument");
            awsJsonWriter.value(policyDocument);
        }
        if (resourcePolicy.getLastUpdatedTime() != null) {
            Long lastUpdatedTime = resourcePolicy.getLastUpdatedTime();
            awsJsonWriter.name("lastUpdatedTime");
            awsJsonWriter.value(lastUpdatedTime);
        }
        awsJsonWriter.endObject();
    }
}
