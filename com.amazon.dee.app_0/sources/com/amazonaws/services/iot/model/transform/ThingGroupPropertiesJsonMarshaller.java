package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AttributePayload;
import com.amazonaws.services.iot.model.ThingGroupProperties;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class ThingGroupPropertiesJsonMarshaller {
    private static ThingGroupPropertiesJsonMarshaller instance;

    ThingGroupPropertiesJsonMarshaller() {
    }

    public static ThingGroupPropertiesJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ThingGroupPropertiesJsonMarshaller();
        }
        return instance;
    }

    public void marshall(ThingGroupProperties thingGroupProperties, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (thingGroupProperties.getThingGroupDescription() != null) {
            String thingGroupDescription = thingGroupProperties.getThingGroupDescription();
            awsJsonWriter.name("thingGroupDescription");
            awsJsonWriter.value(thingGroupDescription);
        }
        if (thingGroupProperties.getAttributePayload() != null) {
            AttributePayload attributePayload = thingGroupProperties.getAttributePayload();
            awsJsonWriter.name("attributePayload");
            AttributePayloadJsonMarshaller.getInstance().marshall(attributePayload, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }
}
