package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.Behavior;
import com.amazonaws.services.iot.model.ValidateSecurityProfileBehaviorsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
import java.util.List;
/* loaded from: classes13.dex */
public class ValidateSecurityProfileBehaviorsRequestMarshaller implements Marshaller<Request<ValidateSecurityProfileBehaviorsRequest>, ValidateSecurityProfileBehaviorsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ValidateSecurityProfileBehaviorsRequest> marshall(ValidateSecurityProfileBehaviorsRequest validateSecurityProfileBehaviorsRequest) {
        if (validateSecurityProfileBehaviorsRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(validateSecurityProfileBehaviorsRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/security-profile-behaviors/validate");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (validateSecurityProfileBehaviorsRequest.getBehaviors() != null) {
                    List<Behavior> behaviors = validateSecurityProfileBehaviorsRequest.getBehaviors();
                    jsonWriter.name("behaviors");
                    jsonWriter.beginArray();
                    for (Behavior behavior : behaviors) {
                        if (behavior != null) {
                            BehaviorJsonMarshaller.getInstance().marshall(behavior, jsonWriter);
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
        throw new AmazonClientException("Invalid argument passed to marshall(ValidateSecurityProfileBehaviorsRequest)");
    }
}
