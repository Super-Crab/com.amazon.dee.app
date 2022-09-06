package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.RelatedResource;
import com.amazonaws.services.iot.model.ResourceIdentifier;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Map;
/* loaded from: classes13.dex */
class RelatedResourceJsonMarshaller {
    private static RelatedResourceJsonMarshaller instance;

    RelatedResourceJsonMarshaller() {
    }

    public static RelatedResourceJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new RelatedResourceJsonMarshaller();
        }
        return instance;
    }

    public void marshall(RelatedResource relatedResource, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (relatedResource.getResourceType() != null) {
            String resourceType = relatedResource.getResourceType();
            awsJsonWriter.name("resourceType");
            awsJsonWriter.value(resourceType);
        }
        if (relatedResource.getResourceIdentifier() != null) {
            ResourceIdentifier resourceIdentifier = relatedResource.getResourceIdentifier();
            awsJsonWriter.name("resourceIdentifier");
            ResourceIdentifierJsonMarshaller.getInstance().marshall(resourceIdentifier, awsJsonWriter);
        }
        if (relatedResource.getAdditionalInfo() != null) {
            Map<String, String> additionalInfo = relatedResource.getAdditionalInfo();
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
