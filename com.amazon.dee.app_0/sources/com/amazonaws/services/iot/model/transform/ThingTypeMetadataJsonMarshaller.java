package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.ThingTypeMetadata;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;
/* loaded from: classes13.dex */
class ThingTypeMetadataJsonMarshaller {
    private static ThingTypeMetadataJsonMarshaller instance;

    ThingTypeMetadataJsonMarshaller() {
    }

    public static ThingTypeMetadataJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ThingTypeMetadataJsonMarshaller();
        }
        return instance;
    }

    public void marshall(ThingTypeMetadata thingTypeMetadata, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (thingTypeMetadata.getDeprecated() != null) {
            Boolean deprecated = thingTypeMetadata.getDeprecated();
            awsJsonWriter.name("deprecated");
            awsJsonWriter.value(deprecated.booleanValue());
        }
        if (thingTypeMetadata.getDeprecationDate() != null) {
            Date deprecationDate = thingTypeMetadata.getDeprecationDate();
            awsJsonWriter.name("deprecationDate");
            awsJsonWriter.value(deprecationDate);
        }
        if (thingTypeMetadata.getCreationDate() != null) {
            Date creationDate = thingTypeMetadata.getCreationDate();
            awsJsonWriter.name("creationDate");
            awsJsonWriter.value(creationDate);
        }
        awsJsonWriter.endObject();
    }
}
