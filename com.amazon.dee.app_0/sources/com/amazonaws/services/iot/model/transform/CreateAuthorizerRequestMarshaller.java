package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CreateAuthorizerRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
import java.util.Map;
/* loaded from: classes13.dex */
public class CreateAuthorizerRequestMarshaller implements Marshaller<Request<CreateAuthorizerRequest>, CreateAuthorizerRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateAuthorizerRequest> marshall(CreateAuthorizerRequest createAuthorizerRequest) {
        if (createAuthorizerRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(createAuthorizerRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/authorizer/{authorizerName}".replace("{authorizerName}", createAuthorizerRequest.getAuthorizerName() == null ? "" : StringUtils.fromString(createAuthorizerRequest.getAuthorizerName())));
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (createAuthorizerRequest.getAuthorizerFunctionArn() != null) {
                    String authorizerFunctionArn = createAuthorizerRequest.getAuthorizerFunctionArn();
                    jsonWriter.name("authorizerFunctionArn");
                    jsonWriter.value(authorizerFunctionArn);
                }
                if (createAuthorizerRequest.getTokenKeyName() != null) {
                    String tokenKeyName = createAuthorizerRequest.getTokenKeyName();
                    jsonWriter.name("tokenKeyName");
                    jsonWriter.value(tokenKeyName);
                }
                if (createAuthorizerRequest.getTokenSigningPublicKeys() != null) {
                    Map<String, String> tokenSigningPublicKeys = createAuthorizerRequest.getTokenSigningPublicKeys();
                    jsonWriter.name("tokenSigningPublicKeys");
                    jsonWriter.beginObject();
                    for (Map.Entry<String, String> entry : tokenSigningPublicKeys.entrySet()) {
                        String value = entry.getValue();
                        if (value != null) {
                            jsonWriter.name(entry.getKey());
                            jsonWriter.value(value);
                        }
                    }
                    jsonWriter.endObject();
                }
                if (createAuthorizerRequest.getStatus() != null) {
                    String status = createAuthorizerRequest.getStatus();
                    jsonWriter.name("status");
                    jsonWriter.value(status);
                }
                jsonWriter.endObject();
                jsonWriter.close();
                String stringWriter2 = stringWriter.toString();
                byte[] bytes = stringWriter2.getBytes(StringUtils.UTF8);
                defaultRequest.setContent(new StringInputStream(stringWriter2));
                defaultRequest.addHeader("Content-Length", Integer.toString(bytes.length));
                if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                    defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
                }
                return defaultRequest;
            } catch (Throwable th) {
                throw new AmazonClientException(GeneratedOutlineSupport1.outline98(th, GeneratedOutlineSupport1.outline107("Unable to marshall request to JSON: ")), th);
            }
        }
        throw new AmazonClientException("Invalid argument passed to marshall(CreateAuthorizerRequest)");
    }
}
