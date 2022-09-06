package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ThingIndexingConfiguration;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class ThingIndexingConfigurationJsonUnmarshaller implements Unmarshaller<ThingIndexingConfiguration, JsonUnmarshallerContext> {
    private static ThingIndexingConfigurationJsonUnmarshaller instance;

    ThingIndexingConfigurationJsonUnmarshaller() {
    }

    public static ThingIndexingConfigurationJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ThingIndexingConfigurationJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ThingIndexingConfiguration unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ThingIndexingConfiguration thingIndexingConfiguration = new ThingIndexingConfiguration();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("thingIndexingMode")) {
                thingIndexingConfiguration.setThingIndexingMode(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("thingConnectivityIndexingMode")) {
                thingIndexingConfiguration.setThingConnectivityIndexingMode(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return thingIndexingConfiguration;
    }
}
