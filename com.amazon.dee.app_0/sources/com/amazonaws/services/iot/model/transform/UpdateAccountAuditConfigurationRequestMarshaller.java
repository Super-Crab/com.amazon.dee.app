package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AuditCheckConfiguration;
import com.amazonaws.services.iot.model.AuditNotificationTarget;
import com.amazonaws.services.iot.model.UpdateAccountAuditConfigurationRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
import java.util.Map;
/* loaded from: classes13.dex */
public class UpdateAccountAuditConfigurationRequestMarshaller implements Marshaller<Request<UpdateAccountAuditConfigurationRequest>, UpdateAccountAuditConfigurationRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateAccountAuditConfigurationRequest> marshall(UpdateAccountAuditConfigurationRequest updateAccountAuditConfigurationRequest) {
        if (updateAccountAuditConfigurationRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(updateAccountAuditConfigurationRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.PATCH);
            defaultRequest.setResourcePath("/audit/configuration");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (updateAccountAuditConfigurationRequest.getRoleArn() != null) {
                    String roleArn = updateAccountAuditConfigurationRequest.getRoleArn();
                    jsonWriter.name("roleArn");
                    jsonWriter.value(roleArn);
                }
                if (updateAccountAuditConfigurationRequest.getAuditNotificationTargetConfigurations() != null) {
                    Map<String, AuditNotificationTarget> auditNotificationTargetConfigurations = updateAccountAuditConfigurationRequest.getAuditNotificationTargetConfigurations();
                    jsonWriter.name("auditNotificationTargetConfigurations");
                    jsonWriter.beginObject();
                    for (Map.Entry<String, AuditNotificationTarget> entry : auditNotificationTargetConfigurations.entrySet()) {
                        AuditNotificationTarget value = entry.getValue();
                        if (value != null) {
                            jsonWriter.name(entry.getKey());
                            AuditNotificationTargetJsonMarshaller.getInstance().marshall(value, jsonWriter);
                        }
                    }
                    jsonWriter.endObject();
                }
                if (updateAccountAuditConfigurationRequest.getAuditCheckConfigurations() != null) {
                    Map<String, AuditCheckConfiguration> auditCheckConfigurations = updateAccountAuditConfigurationRequest.getAuditCheckConfigurations();
                    jsonWriter.name("auditCheckConfigurations");
                    jsonWriter.beginObject();
                    for (Map.Entry<String, AuditCheckConfiguration> entry2 : auditCheckConfigurations.entrySet()) {
                        AuditCheckConfiguration value2 = entry2.getValue();
                        if (value2 != null) {
                            jsonWriter.name(entry2.getKey());
                            AuditCheckConfigurationJsonMarshaller.getInstance().marshall(value2, jsonWriter);
                        }
                    }
                    jsonWriter.endObject();
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
        throw new AmazonClientException("Invalid argument passed to marshall(UpdateAccountAuditConfigurationRequest)");
    }
}
