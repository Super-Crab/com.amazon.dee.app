package com.amazonaws.services.logs.model.transform;

import com.amazonaws.services.logs.model.ResourcePolicy;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class ResourcePolicyJsonUnmarshaller implements Unmarshaller<ResourcePolicy, JsonUnmarshallerContext> {
    private static ResourcePolicyJsonUnmarshaller instance;

    ResourcePolicyJsonUnmarshaller() {
    }

    public static ResourcePolicyJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ResourcePolicyJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ResourcePolicy unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ResourcePolicy resourcePolicy = new ResourcePolicy();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("policyName")) {
                resourcePolicy.setPolicyName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("policyDocument")) {
                resourcePolicy.setPolicyDocument(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("lastUpdatedTime")) {
                resourcePolicy.setLastUpdatedTime(SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return resourcePolicy;
    }
}
