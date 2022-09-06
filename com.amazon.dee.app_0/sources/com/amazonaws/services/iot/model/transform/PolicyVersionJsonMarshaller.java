package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.PolicyVersion;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;
/* loaded from: classes13.dex */
class PolicyVersionJsonMarshaller {
    private static PolicyVersionJsonMarshaller instance;

    PolicyVersionJsonMarshaller() {
    }

    public static PolicyVersionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new PolicyVersionJsonMarshaller();
        }
        return instance;
    }

    public void marshall(PolicyVersion policyVersion, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (policyVersion.getVersionId() != null) {
            String versionId = policyVersion.getVersionId();
            awsJsonWriter.name("versionId");
            awsJsonWriter.value(versionId);
        }
        if (policyVersion.getIsDefaultVersion() != null) {
            Boolean isDefaultVersion = policyVersion.getIsDefaultVersion();
            awsJsonWriter.name("isDefaultVersion");
            awsJsonWriter.value(isDefaultVersion.booleanValue());
        }
        if (policyVersion.getCreateDate() != null) {
            Date createDate = policyVersion.getCreateDate();
            awsJsonWriter.name("createDate");
            awsJsonWriter.value(createDate);
        }
        awsJsonWriter.endObject();
    }
}
