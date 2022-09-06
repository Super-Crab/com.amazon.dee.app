package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ThingGroupIndexingConfiguration;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class ThingGroupIndexingConfigurationJsonMarshaller {
    private static ThingGroupIndexingConfigurationJsonMarshaller instance;

    ThingGroupIndexingConfigurationJsonMarshaller() {
    }

    public static ThingGroupIndexingConfigurationJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ThingGroupIndexingConfigurationJsonMarshaller();
        }
        return instance;
    }

    public void marshall(ThingGroupIndexingConfiguration thingGroupIndexingConfiguration, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (thingGroupIndexingConfiguration.getThingGroupIndexingMode() != null) {
            String thingGroupIndexingMode = thingGroupIndexingConfiguration.getThingGroupIndexingMode();
            awsJsonWriter.name("thingGroupIndexingMode");
            awsJsonWriter.value(thingGroupIndexingMode);
        }
        awsJsonWriter.endObject();
    }
}
