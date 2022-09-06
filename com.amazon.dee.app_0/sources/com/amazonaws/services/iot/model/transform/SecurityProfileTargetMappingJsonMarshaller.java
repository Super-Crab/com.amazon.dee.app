package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.SecurityProfileIdentifier;
import com.amazonaws.services.iot.model.SecurityProfileTarget;
import com.amazonaws.services.iot.model.SecurityProfileTargetMapping;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class SecurityProfileTargetMappingJsonMarshaller {
    private static SecurityProfileTargetMappingJsonMarshaller instance;

    SecurityProfileTargetMappingJsonMarshaller() {
    }

    public static SecurityProfileTargetMappingJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new SecurityProfileTargetMappingJsonMarshaller();
        }
        return instance;
    }

    public void marshall(SecurityProfileTargetMapping securityProfileTargetMapping, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (securityProfileTargetMapping.getSecurityProfileIdentifier() != null) {
            SecurityProfileIdentifier securityProfileIdentifier = securityProfileTargetMapping.getSecurityProfileIdentifier();
            awsJsonWriter.name("securityProfileIdentifier");
            SecurityProfileIdentifierJsonMarshaller.getInstance().marshall(securityProfileIdentifier, awsJsonWriter);
        }
        if (securityProfileTargetMapping.getTarget() != null) {
            SecurityProfileTarget target = securityProfileTargetMapping.getTarget();
            awsJsonWriter.name("target");
            SecurityProfileTargetJsonMarshaller.getInstance().marshall(target, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }
}
