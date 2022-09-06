package com.amazonaws.services.iot.model.transform;

import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.amazonaws.services.iot.model.PolicyVersionIdentifier;
import com.amazonaws.services.iot.model.ResourceIdentifier;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class ResourceIdentifierJsonMarshaller {
    private static ResourceIdentifierJsonMarshaller instance;

    ResourceIdentifierJsonMarshaller() {
    }

    public static ResourceIdentifierJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ResourceIdentifierJsonMarshaller();
        }
        return instance;
    }

    public void marshall(ResourceIdentifier resourceIdentifier, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (resourceIdentifier.getDeviceCertificateId() != null) {
            String deviceCertificateId = resourceIdentifier.getDeviceCertificateId();
            awsJsonWriter.name("deviceCertificateId");
            awsJsonWriter.value(deviceCertificateId);
        }
        if (resourceIdentifier.getCaCertificateId() != null) {
            String caCertificateId = resourceIdentifier.getCaCertificateId();
            awsJsonWriter.name("caCertificateId");
            awsJsonWriter.value(caCertificateId);
        }
        if (resourceIdentifier.getCognitoIdentityPoolId() != null) {
            String cognitoIdentityPoolId = resourceIdentifier.getCognitoIdentityPoolId();
            awsJsonWriter.name("cognitoIdentityPoolId");
            awsJsonWriter.value(cognitoIdentityPoolId);
        }
        if (resourceIdentifier.getClientId() != null) {
            String clientId = resourceIdentifier.getClientId();
            awsJsonWriter.name(AuthorizationResponseParser.CLIENT_ID_STATE);
            awsJsonWriter.value(clientId);
        }
        if (resourceIdentifier.getPolicyVersionIdentifier() != null) {
            PolicyVersionIdentifier policyVersionIdentifier = resourceIdentifier.getPolicyVersionIdentifier();
            awsJsonWriter.name("policyVersionIdentifier");
            PolicyVersionIdentifierJsonMarshaller.getInstance().marshall(policyVersionIdentifier, awsJsonWriter);
        }
        if (resourceIdentifier.getAccount() != null) {
            String account = resourceIdentifier.getAccount();
            awsJsonWriter.name("account");
            awsJsonWriter.value(account);
        }
        awsJsonWriter.endObject();
    }
}
