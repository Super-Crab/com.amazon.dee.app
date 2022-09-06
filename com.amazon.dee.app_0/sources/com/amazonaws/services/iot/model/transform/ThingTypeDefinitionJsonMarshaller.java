package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ThingTypeDefinition;
import com.amazonaws.services.iot.model.ThingTypeMetadata;
import com.amazonaws.services.iot.model.ThingTypeProperties;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class ThingTypeDefinitionJsonMarshaller {
    private static ThingTypeDefinitionJsonMarshaller instance;

    ThingTypeDefinitionJsonMarshaller() {
    }

    public static ThingTypeDefinitionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ThingTypeDefinitionJsonMarshaller();
        }
        return instance;
    }

    public void marshall(ThingTypeDefinition thingTypeDefinition, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (thingTypeDefinition.getThingTypeName() != null) {
            String thingTypeName = thingTypeDefinition.getThingTypeName();
            awsJsonWriter.name("thingTypeName");
            awsJsonWriter.value(thingTypeName);
        }
        if (thingTypeDefinition.getThingTypeArn() != null) {
            String thingTypeArn = thingTypeDefinition.getThingTypeArn();
            awsJsonWriter.name("thingTypeArn");
            awsJsonWriter.value(thingTypeArn);
        }
        if (thingTypeDefinition.getThingTypeProperties() != null) {
            ThingTypeProperties thingTypeProperties = thingTypeDefinition.getThingTypeProperties();
            awsJsonWriter.name("thingTypeProperties");
            ThingTypePropertiesJsonMarshaller.getInstance().marshall(thingTypeProperties, awsJsonWriter);
        }
        if (thingTypeDefinition.getThingTypeMetadata() != null) {
            ThingTypeMetadata thingTypeMetadata = thingTypeDefinition.getThingTypeMetadata();
            awsJsonWriter.name("thingTypeMetadata");
            ThingTypeMetadataJsonMarshaller.getInstance().marshall(thingTypeMetadata, awsJsonWriter);
        }
        awsJsonWriter.endObject();
    }
}
