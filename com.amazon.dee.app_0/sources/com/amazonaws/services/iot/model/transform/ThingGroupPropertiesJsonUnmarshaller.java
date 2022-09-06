package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ThingGroupProperties;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class ThingGroupPropertiesJsonUnmarshaller implements Unmarshaller<ThingGroupProperties, JsonUnmarshallerContext> {
    private static ThingGroupPropertiesJsonUnmarshaller instance;

    ThingGroupPropertiesJsonUnmarshaller() {
    }

    public static ThingGroupPropertiesJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ThingGroupPropertiesJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ThingGroupProperties unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ThingGroupProperties thingGroupProperties = new ThingGroupProperties();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("thingGroupDescription")) {
                thingGroupProperties.setThingGroupDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("attributePayload")) {
                thingGroupProperties.setAttributePayload(AttributePayloadJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return thingGroupProperties;
    }
}
