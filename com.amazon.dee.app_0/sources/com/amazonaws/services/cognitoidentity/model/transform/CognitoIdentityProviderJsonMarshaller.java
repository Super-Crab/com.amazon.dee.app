package com.amazonaws.services.cognitoidentity.model.transform;

import com.amazon.identity.auth.device.datastore.DatabaseHelper;
import com.amazonaws.services.cognitoidentity.model.CognitoIdentityProvider;
import com.amazonaws.util.json.AwsJsonWriter;
/* loaded from: classes13.dex */
class CognitoIdentityProviderJsonMarshaller {
    private static CognitoIdentityProviderJsonMarshaller instance;

    CognitoIdentityProviderJsonMarshaller() {
    }

    public static CognitoIdentityProviderJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new CognitoIdentityProviderJsonMarshaller();
        }
        return instance;
    }

    public void marshall(CognitoIdentityProvider cognitoIdentityProvider, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (cognitoIdentityProvider.getProviderName() != null) {
            String providerName = cognitoIdentityProvider.getProviderName();
            awsJsonWriter.name("ProviderName");
            awsJsonWriter.value(providerName);
        }
        if (cognitoIdentityProvider.getClientId() != null) {
            String clientId = cognitoIdentityProvider.getClientId();
            awsJsonWriter.name(DatabaseHelper.appInfo_ClientId);
            awsJsonWriter.value(clientId);
        }
        if (cognitoIdentityProvider.getServerSideTokenCheck() != null) {
            Boolean serverSideTokenCheck = cognitoIdentityProvider.getServerSideTokenCheck();
            awsJsonWriter.name("ServerSideTokenCheck");
            awsJsonWriter.value(serverSideTokenCheck.booleanValue());
        }
        awsJsonWriter.endObject();
    }
}
