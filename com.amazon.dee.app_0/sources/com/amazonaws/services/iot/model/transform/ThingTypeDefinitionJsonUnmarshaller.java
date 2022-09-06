package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ThingTypeDefinition;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class ThingTypeDefinitionJsonUnmarshaller implements Unmarshaller<ThingTypeDefinition, JsonUnmarshallerContext> {
    private static ThingTypeDefinitionJsonUnmarshaller instance;

    ThingTypeDefinitionJsonUnmarshaller() {
    }

    public static ThingTypeDefinitionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ThingTypeDefinitionJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ThingTypeDefinition unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ThingTypeDefinition thingTypeDefinition = new ThingTypeDefinition();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("thingTypeName")) {
                thingTypeDefinition.setThingTypeName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("thingTypeArn")) {
                thingTypeDefinition.setThingTypeArn(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("thingTypeProperties")) {
                thingTypeDefinition.setThingTypeProperties(ThingTypePropertiesJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("thingTypeMetadata")) {
                thingTypeDefinition.setThingTypeMetadata(ThingTypeMetadataJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return thingTypeDefinition;
    }
}
