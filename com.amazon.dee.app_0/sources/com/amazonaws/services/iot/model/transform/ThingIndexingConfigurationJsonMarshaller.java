package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ThingIndexingConfiguration;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class ThingIndexingConfigurationJsonMarshaller {
    private static ThingIndexingConfigurationJsonMarshaller instance;

    ThingIndexingConfigurationJsonMarshaller() {
    }

    public static ThingIndexingConfigurationJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ThingIndexingConfigurationJsonMarshaller();
        }
        return instance;
    }

    public void marshall(ThingIndexingConfiguration thingIndexingConfiguration, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (thingIndexingConfiguration.getThingIndexingMode() != null) {
            String thingIndexingMode = thingIndexingConfiguration.getThingIndexingMode();
            awsJsonWriter.name("thingIndexingMode");
            awsJsonWriter.value(thingIndexingMode);
        }
        if (thingIndexingConfiguration.getThingConnectivityIndexingMode() != null) {
            String thingConnectivityIndexingMode = thingIndexingConfiguration.getThingConnectivityIndexingMode();
            awsJsonWriter.name("thingConnectivityIndexingMode");
            awsJsonWriter.value(thingConnectivityIndexingMode);
        }
        awsJsonWriter.endObject();
    }
}
