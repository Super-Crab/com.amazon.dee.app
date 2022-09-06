package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ThingGroupIndexingConfiguration;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class ThingGroupIndexingConfigurationJsonUnmarshaller implements Unmarshaller<ThingGroupIndexingConfiguration, JsonUnmarshallerContext> {
    private static ThingGroupIndexingConfigurationJsonUnmarshaller instance;

    ThingGroupIndexingConfigurationJsonUnmarshaller() {
    }

    public static ThingGroupIndexingConfigurationJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ThingGroupIndexingConfigurationJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ThingGroupIndexingConfiguration unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ThingGroupIndexingConfiguration thingGroupIndexingConfiguration = new ThingGroupIndexingConfiguration();
        reader.beginObject();
        while (reader.hasNext()) {
            if (reader.nextName().equals("thingGroupIndexingMode")) {
                thingGroupIndexingConfiguration.setThingGroupIndexingMode(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return thingGroupIndexingConfiguration;
    }
}
