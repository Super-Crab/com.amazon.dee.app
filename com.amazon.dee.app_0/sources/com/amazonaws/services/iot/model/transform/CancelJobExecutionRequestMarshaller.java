package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CancelJobExecutionRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
import java.util.Map;
/* loaded from: classes13.dex */
public class CancelJobExecutionRequestMarshaller implements Marshaller<Request<CancelJobExecutionRequest>, CancelJobExecutionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CancelJobExecutionRequest> marshall(CancelJobExecutionRequest cancelJobExecutionRequest) {
        if (cancelJobExecutionRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(cancelJobExecutionRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.PUT);
            String str = "";
            String replace = "/things/{thingName}/jobs/{jobId}/cancel".replace("{jobId}", cancelJobExecutionRequest.getJobId() == null ? str : StringUtils.fromString(cancelJobExecutionRequest.getJobId()));
            if (cancelJobExecutionRequest.getThingName() != null) {
                str = StringUtils.fromString(cancelJobExecutionRequest.getThingName());
            }
            String replace2 = replace.replace("{thingName}", str);
            if (cancelJobExecutionRequest.getForce() != null) {
                defaultRequest.addParameter("force", StringUtils.fromBoolean(cancelJobExecutionRequest.getForce()));
            }
            defaultRequest.setResourcePath(replace2);
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (cancelJobExecutionRequest.getExpectedVersion() != null) {
                    Long expectedVersion = cancelJobExecutionRequest.getExpectedVersion();
                    jsonWriter.name("expectedVersion");
                    jsonWriter.value(expectedVersion);
                }
                if (cancelJobExecutionRequest.getStatusDetails() != null) {
                    Map<String, String> statusDetails = cancelJobExecutionRequest.getStatusDetails();
                    jsonWriter.name("statusDetails");
                    jsonWriter.beginObject();
                    for (Map.Entry<String, String> entry : statusDetails.entrySet()) {
                        String value = entry.getValue();
                        if (value != null) {
                            jsonWriter.name(entry.getKey());
                            jsonWriter.value(value);
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
        throw new AmazonClientException("Invalid argument passed to marshall(CancelJobExecutionRequest)");
    }
}
