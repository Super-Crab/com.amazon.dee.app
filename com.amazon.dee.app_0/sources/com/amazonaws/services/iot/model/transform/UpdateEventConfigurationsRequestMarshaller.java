package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.Configuration;
import com.amazonaws.services.iot.model.UpdateEventConfigurationsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
import java.util.Map;
/* loaded from: classes13.dex */
public class UpdateEventConfigurationsRequestMarshaller implements Marshaller<Request<UpdateEventConfigurationsRequest>, UpdateEventConfigurationsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateEventConfigurationsRequest> marshall(UpdateEventConfigurationsRequest updateEventConfigurationsRequest) {
        if (updateEventConfigurationsRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(updateEventConfigurationsRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.PATCH);
            defaultRequest.setResourcePath("/event-configurations");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (updateEventConfigurationsRequest.getEventConfigurations() != null) {
                    Map<String, Configuration> eventConfigurations = updateEventConfigurationsRequest.getEventConfigurations();
                    jsonWriter.name("eventConfigurations");
                    jsonWriter.beginObject();
                    for (Map.Entry<String, Configuration> entry : eventConfigurations.entrySet()) {
                        Configuration value = entry.getValue();
                        if (value != null) {
                            jsonWriter.name(entry.getKey());
                            ConfigurationJsonMarshaller.getInstance().marshall(value, jsonWriter);
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
        throw new AmazonClientException("Invalid argument passed to marshall(UpdateEventConfigurationsRequest)");
    }
}
