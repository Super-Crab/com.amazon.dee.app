package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ThingTypeProperties;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.ListUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class ThingTypePropertiesJsonUnmarshaller implements Unmarshaller<ThingTypeProperties, JsonUnmarshallerContext> {
    private static ThingTypePropertiesJsonUnmarshaller instance;

    ThingTypePropertiesJsonUnmarshaller() {
    }

    public static ThingTypePropertiesJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ThingTypePropertiesJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ThingTypeProperties unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ThingTypeProperties thingTypeProperties = new ThingTypeProperties();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("thingTypeDescription")) {
                thingTypeProperties.setThingTypeDescription(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("searchableAttributes")) {
                thingTypeProperties.setSearchableAttributes(new ListUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return thingTypeProperties;
    }
}
