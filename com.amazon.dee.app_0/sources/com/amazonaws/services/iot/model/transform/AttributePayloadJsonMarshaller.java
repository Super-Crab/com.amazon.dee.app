package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AttributePayload;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Map;
/* loaded from: classes13.dex */
class AttributePayloadJsonMarshaller {
    private static AttributePayloadJsonMarshaller instance;

    AttributePayloadJsonMarshaller() {
    }

    public static AttributePayloadJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AttributePayloadJsonMarshaller();
        }
        return instance;
    }

    public void marshall(AttributePayload attributePayload, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (attributePayload.getAttributes() != null) {
            Map<String, String> attributes = attributePayload.getAttributes();
            awsJsonWriter.name("attributes");
            awsJsonWriter.beginObject();
            for (Map.Entry<String, String> entry : attributes.entrySet()) {
                String value = entry.getValue();
                if (value != null) {
                    awsJsonWriter.name(entry.getKey());
                    awsJsonWriter.value(value);
                }
            }
            awsJsonWriter.endObject();
        }
        if (attributePayload.getMerge() != null) {
            Boolean merge = attributePayload.getMerge();
            awsJsonWriter.name("merge");
            awsJsonWriter.value(merge.booleanValue());
        }
        awsJsonWriter.endObject();
    }
}
