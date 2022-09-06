package com.amazonaws.services.logs.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.logs.model.CreateLogGroupRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
import java.util.Map;
/* loaded from: classes13.dex */
public class CreateLogGroupRequestMarshaller implements Marshaller<Request<CreateLogGroupRequest>, CreateLogGroupRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateLogGroupRequest> marshall(CreateLogGroupRequest createLogGroupRequest) {
        if (createLogGroupRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(createLogGroupRequest, "AmazonCloudWatchLogs");
            defaultRequest.addHeader("X-Amz-Target", "Logs_20140328.CreateLogGroup");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (createLogGroupRequest.getLogGroupName() != null) {
                    String logGroupName = createLogGroupRequest.getLogGroupName();
                    jsonWriter.name("logGroupName");
                    jsonWriter.value(logGroupName);
                }
                if (createLogGroupRequest.getKmsKeyId() != null) {
                    String kmsKeyId = createLogGroupRequest.getKmsKeyId();
                    jsonWriter.name("kmsKeyId");
                    jsonWriter.value(kmsKeyId);
                }
                if (createLogGroupRequest.getTags() != null) {
                    Map<String, String> tags = createLogGroupRequest.getTags();
                    jsonWriter.name("tags");
                    jsonWriter.beginObject();
                    for (Map.Entry<String, String> entry : tags.entrySet()) {
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
                    defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.1");
                }
                return defaultRequest;
            } catch (Throwable th) {
                throw new AmazonClientException(GeneratedOutlineSupport1.outline98(th, GeneratedOutlineSupport1.outline107("Unable to marshall request to JSON: ")), th);
            }
        }
        throw new AmazonClientException("Invalid argument passed to marshall(CreateLogGroupRequest)");
    }
}
