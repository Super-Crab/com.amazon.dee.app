package com.amazonaws.services.iot.model.transform;

import com.amazonaws.services.iot.model.AuthorizerDescription;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Date;
import java.util.Map;
/* loaded from: classes13.dex */
class AuthorizerDescriptionJsonMarshaller {
    private static AuthorizerDescriptionJsonMarshaller instance;

    AuthorizerDescriptionJsonMarshaller() {
    }

    public static AuthorizerDescriptionJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new AuthorizerDescriptionJsonMarshaller();
        }
        return instance;
    }

    public void marshall(AuthorizerDescription authorizerDescription, AwsJsonWriter awsJsonWriter) throws Exception {
        awsJsonWriter.beginObject();
        if (authorizerDescription.getAuthorizerName() != null) {
            String authorizerName = authorizerDescription.getAuthorizerName();
            awsJsonWriter.name("authorizerName");
            awsJsonWriter.value(authorizerName);
        }
        if (authorizerDescription.getAuthorizerArn() != null) {
            String authorizerArn = authorizerDescription.getAuthorizerArn();
            awsJsonWriter.name("authorizerArn");
            awsJsonWriter.value(authorizerArn);
        }
        if (authorizerDescription.getAuthorizerFunctionArn() != null) {
            String authorizerFunctionArn = authorizerDescription.getAuthorizerFunctionArn();
            awsJsonWriter.name("authorizerFunctionArn");
            awsJsonWriter.value(authorizerFunctionArn);
        }
        if (authorizerDescription.getTokenKeyName() != null) {
            String tokenKeyName = authorizerDescription.getTokenKeyName();
            awsJsonWriter.name("tokenKeyName");
            awsJsonWriter.value(tokenKeyName);
        }
        if (authorizerDescription.getTokenSigningPublicKeys() != null) {
            Map<String, String> tokenSigningPublicKeys = authorizerDescription.getTokenSigningPublicKeys();
            awsJsonWriter.name("tokenSigningPublicKeys");
            awsJsonWriter.beginObject();
            for (Map.Entry<String, String> entry : tokenSigningPublicKeys.entrySet()) {
                String value = entry.getValue();
                if (value != null) {
                    awsJsonWriter.name(entry.getKey());
                    awsJsonWriter.value(value);
                }
            }
            awsJsonWriter.endObject();
        }
        if (authorizerDescription.getStatus() != null) {
            String status = authorizerDescription.getStatus();
            awsJsonWriter.name("status");
            awsJsonWriter.value(status);
        }
        if (authorizerDescription.getCreationDate() != null) {
            Date creationDate = authorizerDescription.getCreationDate();
            awsJsonWriter.name("creationDate");
            awsJsonWriter.value(creationDate);
        }
        if (authorizerDescription.getLastModifiedDate() != null) {
            Date lastModifiedDate = authorizerDescription.getLastModifiedDate();
            awsJsonWriter.name("lastModifiedDate");
            awsJsonWriter.value(lastModifiedDate);
        }
        awsJsonWriter.endObject();
    }
}
