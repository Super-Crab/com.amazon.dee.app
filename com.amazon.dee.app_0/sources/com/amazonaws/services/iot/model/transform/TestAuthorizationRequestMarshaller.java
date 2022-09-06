package com.amazonaws.services.iot.model.transform;

import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AuthInfo;
import com.amazonaws.services.iot.model.TestAuthorizationRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
import java.util.List;
/* loaded from: classes13.dex */
public class TestAuthorizationRequestMarshaller implements Marshaller<Request<TestAuthorizationRequest>, TestAuthorizationRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<TestAuthorizationRequest> marshall(TestAuthorizationRequest testAuthorizationRequest) {
        if (testAuthorizationRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(testAuthorizationRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            if (testAuthorizationRequest.getClientId() != null) {
                defaultRequest.addParameter(AuthorizationResponseParser.CLIENT_ID_STATE, StringUtils.fromString(testAuthorizationRequest.getClientId()));
            }
            defaultRequest.setResourcePath("/test-authorization");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (testAuthorizationRequest.getPrincipal() != null) {
                    String principal = testAuthorizationRequest.getPrincipal();
                    jsonWriter.name("principal");
                    jsonWriter.value(principal);
                }
                if (testAuthorizationRequest.getCognitoIdentityPoolId() != null) {
                    String cognitoIdentityPoolId = testAuthorizationRequest.getCognitoIdentityPoolId();
                    jsonWriter.name("cognitoIdentityPoolId");
                    jsonWriter.value(cognitoIdentityPoolId);
                }
                if (testAuthorizationRequest.getAuthInfos() != null) {
                    List<AuthInfo> authInfos = testAuthorizationRequest.getAuthInfos();
                    jsonWriter.name("authInfos");
                    jsonWriter.beginArray();
                    for (AuthInfo authInfo : authInfos) {
                        if (authInfo != null) {
                            AuthInfoJsonMarshaller.getInstance().marshall(authInfo, jsonWriter);
                        }
                    }
                    jsonWriter.endArray();
                }
                if (testAuthorizationRequest.getPolicyNamesToAdd() != null) {
                    List<String> policyNamesToAdd = testAuthorizationRequest.getPolicyNamesToAdd();
                    jsonWriter.name("policyNamesToAdd");
                    jsonWriter.beginArray();
                    for (String str : policyNamesToAdd) {
                        if (str != null) {
                            jsonWriter.value(str);
                        }
                    }
                    jsonWriter.endArray();
                }
                if (testAuthorizationRequest.getPolicyNamesToSkip() != null) {
                    List<String> policyNamesToSkip = testAuthorizationRequest.getPolicyNamesToSkip();
                    jsonWriter.name("policyNamesToSkip");
                    jsonWriter.beginArray();
                    for (String str2 : policyNamesToSkip) {
                        if (str2 != null) {
                            jsonWriter.value(str2);
                        }
                    }
                    jsonWriter.endArray();
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
        throw new AmazonClientException("Invalid argument passed to marshall(TestAuthorizationRequest)");
    }
}
