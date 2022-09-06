package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.UpdateAuthorizerRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
import java.util.Map;
/* loaded from: classes13.dex */
public class UpdateAuthorizerRequestMarshaller implements Marshaller<Request<UpdateAuthorizerRequest>, UpdateAuthorizerRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateAuthorizerRequest> marshall(UpdateAuthorizerRequest updateAuthorizerRequest) {
        if (updateAuthorizerRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(updateAuthorizerRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.PUT);
            defaultRequest.setResourcePath("/authorizer/{authorizerName}".replace("{authorizerName}", updateAuthorizerRequest.getAuthorizerName() == null ? "" : StringUtils.fromString(updateAuthorizerRequest.getAuthorizerName())));
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (updateAuthorizerRequest.getAuthorizerFunctionArn() != null) {
                    String authorizerFunctionArn = updateAuthorizerRequest.getAuthorizerFunctionArn();
                    jsonWriter.name("authorizerFunctionArn");
                    jsonWriter.value(authorizerFunctionArn);
                }
                if (updateAuthorizerRequest.getTokenKeyName() != null) {
                    String tokenKeyName = updateAuthorizerRequest.getTokenKeyName();
                    jsonWriter.name("tokenKeyName");
                    jsonWriter.value(tokenKeyName);
                }
                if (updateAuthorizerRequest.getTokenSigningPublicKeys() != null) {
                    Map<String, String> tokenSigningPublicKeys = updateAuthorizerRequest.getTokenSigningPublicKeys();
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
                if (updateAuthorizerRequest.getStatus() != null) {
                    String status = updateAuthorizerRequest.getStatus();
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
        throw new AmazonClientException("Invalid argument passed to marshall(UpdateAuthorizerRequest)");
    }
}
