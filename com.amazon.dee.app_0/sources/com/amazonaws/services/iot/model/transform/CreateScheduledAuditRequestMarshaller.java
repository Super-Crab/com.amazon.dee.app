package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.audiopersonalization.constants.EventBusConstants;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CreateScheduledAuditRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
import java.util.List;
/* loaded from: classes13.dex */
public class CreateScheduledAuditRequestMarshaller implements Marshaller<Request<CreateScheduledAuditRequest>, CreateScheduledAuditRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateScheduledAuditRequest> marshall(CreateScheduledAuditRequest createScheduledAuditRequest) {
        if (createScheduledAuditRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(createScheduledAuditRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/audit/scheduledaudits/{scheduledAuditName}".replace("{scheduledAuditName}", createScheduledAuditRequest.getScheduledAuditName() == null ? "" : StringUtils.fromString(createScheduledAuditRequest.getScheduledAuditName())));
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (createScheduledAuditRequest.getFrequency() != null) {
                    String frequency = createScheduledAuditRequest.getFrequency();
                    jsonWriter.name(EventBusConstants.FREQUENCY_KEY);
                    jsonWriter.value(frequency);
                }
                if (createScheduledAuditRequest.getDayOfMonth() != null) {
                    String dayOfMonth = createScheduledAuditRequest.getDayOfMonth();
                    jsonWriter.name("dayOfMonth");
                    jsonWriter.value(dayOfMonth);
                }
                if (createScheduledAuditRequest.getDayOfWeek() != null) {
                    String dayOfWeek = createScheduledAuditRequest.getDayOfWeek();
                    jsonWriter.name("dayOfWeek");
                    jsonWriter.value(dayOfWeek);
                }
                if (createScheduledAuditRequest.getTargetCheckNames() != null) {
                    List<String> targetCheckNames = createScheduledAuditRequest.getTargetCheckNames();
                    jsonWriter.name("targetCheckNames");
                    jsonWriter.beginArray();
                    for (String str : targetCheckNames) {
                        if (str != null) {
                            jsonWriter.value(str);
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
        throw new AmazonClientException("Invalid argument passed to marshall(CreateScheduledAuditRequest)");
    }
}
