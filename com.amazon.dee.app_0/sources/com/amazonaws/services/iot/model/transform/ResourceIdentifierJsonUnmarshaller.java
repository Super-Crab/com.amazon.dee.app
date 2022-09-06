package com.amazonaws.services.iot.model.transform;

import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.amazonaws.services.iot.model.ResourceIdentifier;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;
/* loaded from: classes13.dex */
class ResourceIdentifierJsonUnmarshaller implements Unmarshaller<ResourceIdentifier, JsonUnmarshallerContext> {
    private static ResourceIdentifierJsonUnmarshaller instance;

    ResourceIdentifierJsonUnmarshaller() {
    }

    public static ResourceIdentifierJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ResourceIdentifierJsonUnmarshaller();
        }
        return instance;
    }

    @Override // com.amazonaws.transform.Unmarshaller
    public ResourceIdentifier unmarshall(JsonUnmarshallerContext jsonUnmarshallerContext) throws Exception {
        AwsJsonReader reader = jsonUnmarshallerContext.getReader();
        if (!reader.isContainer()) {
            reader.skipValue();
            return null;
        }
        ResourceIdentifier resourceIdentifier = new ResourceIdentifier();
        reader.beginObject();
        while (reader.hasNext()) {
            String nextName = reader.nextName();
            if (nextName.equals("deviceCertificateId")) {
                resourceIdentifier.setDeviceCertificateId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("caCertificateId")) {
                resourceIdentifier.setCaCertificateId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("cognitoIdentityPoolId")) {
                resourceIdentifier.setCognitoIdentityPoolId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals(AuthorizationResponseParser.CLIENT_ID_STATE)) {
                resourceIdentifier.setClientId(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("policyVersionIdentifier")) {
                resourceIdentifier.setPolicyVersionIdentifier(PolicyVersionIdentifierJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else if (nextName.equals("account")) {
                resourceIdentifier.setAccount(SimpleTypeJsonUnmarshallers.StringJsonUnmarshaller.getInstance().unmarshall(jsonUnmarshallerContext));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return resourceIdentifier;
    }
}
