package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AlertTarget;
import com.amazonaws.services.iot.model.Behavior;
import com.amazonaws.services.iot.model.CreateSecurityProfileRequest;
import com.amazonaws.services.iot.model.Tag;
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
public class CreateSecurityProfileRequestMarshaller implements Marshaller<Request<CreateSecurityProfileRequest>, CreateSecurityProfileRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateSecurityProfileRequest> marshall(CreateSecurityProfileRequest createSecurityProfileRequest) {
        if (createSecurityProfileRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(createSecurityProfileRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/security-profiles/{securityProfileName}".replace("{securityProfileName}", createSecurityProfileRequest.getSecurityProfileName() == null ? "" : StringUtils.fromString(createSecurityProfileRequest.getSecurityProfileName())));
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (createSecurityProfileRequest.getSecurityProfileDescription() != null) {
                    String securityProfileDescription = createSecurityProfileRequest.getSecurityProfileDescription();
                    jsonWriter.name("securityProfileDescription");
                    jsonWriter.value(securityProfileDescription);
                }
                if (createSecurityProfileRequest.getBehaviors() != null) {
                    List<Behavior> behaviors = createSecurityProfileRequest.getBehaviors();
                    jsonWriter.name("behaviors");
                    jsonWriter.beginArray();
                    for (Behavior behavior : behaviors) {
                        if (behavior != null) {
                            BehaviorJsonMarshaller.getInstance().marshall(behavior, jsonWriter);
                        }
                    }
                    jsonWriter.endArray();
                }
                if (createSecurityProfileRequest.getAlertTargets() != null) {
                    Map<String, AlertTarget> alertTargets = createSecurityProfileRequest.getAlertTargets();
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
                if (createSecurityProfileRequest.getTags() != null) {
                    List<Tag> tags = createSecurityProfileRequest.getTags();
                    jsonWriter.name("tags");
                    jsonWriter.beginArray();
                    for (Tag tag : tags) {
                        if (tag != null) {
                            TagJsonMarshaller.getInstance().marshall(tag, jsonWriter);
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
        throw new AmazonClientException("Invalid argument passed to marshall(CreateSecurityProfileRequest)");
    }
}
