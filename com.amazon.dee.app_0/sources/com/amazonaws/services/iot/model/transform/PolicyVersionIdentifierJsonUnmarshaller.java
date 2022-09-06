package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.PolicyVersionIdentifier;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class PolicyVersionIdentifierJsonUnmarshaller implements Unmarshaller<PolicyVersionIdentifier, JsonUnmarshallerContext> {
    private static PolicyVersionIdentifierJsonUnmarshaller instance;

    PolicyVersionIdentifierJsonUnmarshaller() {
    }

    public static PolicyVersionIdentifierJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new PolicyVersionIdentifierJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public PolicyVersionIdentifier unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        PolicyVersionIdentifier policyVersionIdentifier = new PolicyVersionIdentifier();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("policyName")) {
                policyVersionIdentifier.setPolicyName(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("policyVersionId")) {
                policyVersionIdentifier.setPolicyVersionId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return policyVersionIdentifier;
    }
}
