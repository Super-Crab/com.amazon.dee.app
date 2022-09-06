package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.NonCompliantResource;
import com.amazonaws.services.iot.model.ResourceIdentifier;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Map;
/* loaded from: classes13.dex */
class NonCompliantResourceJsonMarshaller {
    private static NonCompliantResourceJsonMarshaller instance;

    NonCompliantResourceJsonMarshaller() {
    }

    public static NonCompliantResourceJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new NonCompliantResourceJsonMarshaller();
        }
        return instance;
    }

    public void marshall(NonCompliantResource nonCompliantResource, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (nonCompliantResource.getResourceType() != null) {
            String resourceType = nonCompliantResource.getResourceType();
            awsJsonWriter.name("resourceType");
            awsJsonWriter.value(resourceType);
        }
        if (nonCompliantResource.getResourceIdentifier() != null) {
            ResourceIdentifier resourceIdentifier = nonCompliantResource.getResourceIdentifier();
            awsJsonWriter.name("resourceIdentifier");
            ResourceIdentifierJsonMarshaller.getInstance().marshall(resourceIdentifier, awsJsonWriter);
        }
        if (nonCompliantResource.getAdditionalInfo() != null) {
            Map<String, String> additionalInfo = nonCompliantResource.getAdditionalInfo();
            awsJsonWriter.name("additionalInfo");
            awsJsonWriter.beginObject();
            for (Map.Entry<String, String> entry : additionalInfo.entrySet()) {
                String value = entry.getValue();
                if (value != null) {
                    awsJsonWriter.name(entry.getKey());
                    awsJsonWriter.value(value);
                }
            }
            awsJsonWriter.endObject();
        }
        awsJsonWriter.endObject();
    }
}
