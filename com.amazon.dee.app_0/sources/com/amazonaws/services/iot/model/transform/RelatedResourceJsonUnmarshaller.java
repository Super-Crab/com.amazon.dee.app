package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.RelatedResource;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.MapUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class RelatedResourceJsonUnmarshaller implements Unmarshaller<RelatedResource, JsonUnmarshallerContext> {
    private static RelatedResourceJsonUnmarshaller instance;

    RelatedResourceJsonUnmarshaller() {
    }

    public static RelatedResourceJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new RelatedResourceJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public RelatedResource unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        RelatedResource relatedResource = new RelatedResource();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("resourceType")) {
                relatedResource.setResourceType(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("resourceIdentifier")) {
                relatedResource.setResourceIdentifier(ResourceIdentifierJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("additionalInfo")) {
                relatedResource.setAdditionalInfo(new MapUnmarshaller(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance()).unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return relatedResource;
    }
}
