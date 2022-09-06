package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ThingTypeMetadata;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class ThingTypeMetadataJsonUnmarshaller implements Unmarshaller<ThingTypeMetadata, JsonUnmarshallerContext> {
    private static ThingTypeMetadataJsonUnmarshaller instance;

    ThingTypeMetadataJsonUnmarshaller() {
    }

    public static ThingTypeMetadataJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ThingTypeMetadataJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ThingTypeMetadata unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ThingTypeMetadata thingTypeMetadata = new ThingTypeMetadata();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("deprecated")) {
                thingTypeMetadata.setDeprecated(SimpleTypeJsonUnmarshallers.BooleanJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("deprecationDate")) {
                thingTypeMetadata.setDeprecationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("creationDate")) {
                thingTypeMetadata.setCreationDate(SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return thingTypeMetadata;
    }
}
