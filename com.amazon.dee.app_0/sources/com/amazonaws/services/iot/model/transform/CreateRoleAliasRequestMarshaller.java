package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CreateRoleAliasRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
/* loaded from: classes13.dex */
public class CreateRoleAliasRequestMarshaller implements Marshaller<Request<CreateRoleAliasRequest>, CreateRoleAliasRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateRoleAliasRequest> marshall(CreateRoleAliasRequest createRoleAliasRequest) {
        if (createRoleAliasRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(createRoleAliasRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/role-aliases/{roleAlias}".replace("{roleAlias}", createRoleAliasRequest.getRoleAlias() == null ? "" : StringUtils.fromString(createRoleAliasRequest.getRoleAlias())));
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (createRoleAliasRequest.getRoleArn() != null) {
                    String roleArn = createRoleAliasRequest.getRoleArn();
                    jsonWriter.name("roleArn");
                    jsonWriter.value(roleArn);
                }
                if (createRoleAliasRequest.getCredentialDurationSeconds() != null) {
                    Integer credentialDurationSeconds = createRoleAliasRequest.getCredentialDurationSeconds();
                    jsonWriter.name("credentialDurationSeconds");
                    jsonWriter.value(credentialDurationSeconds);
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
        throw new AmazonClientException("Invalid argument passed to marshall(CreateRoleAliasRequest)");
    }
}
