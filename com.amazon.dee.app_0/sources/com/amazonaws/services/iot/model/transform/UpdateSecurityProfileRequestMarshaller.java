package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AlertTarget;
import com.amazonaws.services.iot.model.Behavior;
import com.amazonaws.services.iot.model.UpdateSecurityProfileRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
/* loaded from: classes13.dex */
public class UpdateSecurityProfileRequestMarshaller implements Marshaller<Request<UpdateSecurityProfileRequest>, UpdateSecurityProfileRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateSecurityProfileRequest> marshall(UpdateSecurityProfileRequest updateSecurityProfileRequest) {
        if (updateSecurityProfileRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(updateSecurityProfileRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.PATCH);
            String replace = "/security-profiles/{securityProfileName}".replace("{securityProfileName}", updateSecurityProfileRequest.getSecurityProfileName() == null ? "" : StringUtils.fromString(updateSecurityProfileRequest.getSecurityProfileName()));
            if (updateSecurityProfileRequest.getExpectedVersion() != null) {
                defaultRequest.addParameter("expectedVersion", StringUtils.fromLong(updateSecurityProfileRequest.getExpectedVersion()));
            }
            defaultRequest.setResourcePath(replace);
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (updateSecurityProfileRequest.getSecurityProfileDescription() != null) {
                    String securityProfileDescription = updateSecurityProfileRequest.getSecurityProfileDescription();
                    jsonWriter.name("securityProfileDescription");
                    jsonWriter.value(securityProfileDescription);
                }
                if (updateSecurityProfileRequest.getBehaviors() != null) {
                    List<Behavior> behaviors = updateSecurityProfileRequest.getBehaviors();
                    jsonWriter.name("behaviors");
                    jsonWriter.beginArray();
                    for (Behavior behavior : behaviors) {
                        if (behavior != null) {
                            BehaviorJsonMarshaller.getInstance().marshall(behavior, jsonWriter);
                        }
                    }
                    jsonWriter.endArray();
                }
                if (updateSecurityProfileRequest.getAlertTargets() != null) {
                    Map<String, AlertTarget> alertTargets = updateSecurityProfileRequest.getAlertTargets();
                    jsonWriter.name("alertTargets");
                    jsonWriter.beginObject();
                    for (Map.Entry<String, AlertTarget> entry : alertTargets.entrySet()) {
                        AlertTarget value = entry.getValue();
                        if (value != null) {
                            jsonWriter.name(entry.getKey());
                            AlertTargetJsonMarshaller.getInstance().marshall(value, jsonWriter);
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
        throw new AmazonClientException("Invalid argument passed to marshall(UpdateSecurityProfileRequest)");
    }
}
