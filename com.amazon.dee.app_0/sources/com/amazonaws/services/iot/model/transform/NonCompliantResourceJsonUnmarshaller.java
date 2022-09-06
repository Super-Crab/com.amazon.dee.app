package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.NonCompliantResource;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class NonCompliantResourceJsonUnmarshaller implements Unmarshaller<NonCompliantResource, JsonUnmarshallerContext> {
    private static NonCompliantResourceJsonUnmarshaller instance;

    NonCompliantResourceJsonUnmarshaller() {
    }

    public static NonCompliantResourceJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new NonCompliantResourceJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public NonCompliantResource unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        NonCompliantResource nonCompliantResource = new NonCompliantResource();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("resourceType")) {
                nonCompliantResource.setResourceType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("resourceIdentifier")) {
                nonCompliantResource.setResourceIdentifier(ResourceIdentifierJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("additionalInfo")) {
                nonCompliantResource.setAdditionalInfo(new MapUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return nonCompliantResource;
    }
}
