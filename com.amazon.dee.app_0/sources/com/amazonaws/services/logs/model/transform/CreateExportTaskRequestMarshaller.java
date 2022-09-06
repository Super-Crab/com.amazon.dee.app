package com.amazonaws.services.logs.model.transform;

import com.amazon.device.messaging.ADMConstants;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.logs.model.CreateExportTaskRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
/* loaded from: classes13.dex */
public class CreateExportTaskRequestMarshaller implements Marshaller<Request<CreateExportTaskRequest>, CreateExportTaskRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateExportTaskRequest> marshall(CreateExportTaskRequest createExportTaskRequest) {
        if (createExportTaskRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(createExportTaskRequest, "AmazonCloudWatchLogs");
            defaultRequest.addHeader("X-Amz-Target", "Logs_20140328.CreateExportTask");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (createExportTaskRequest.getTaskName() != null) {
                    String taskName = createExportTaskRequest.getTaskName();
                    jsonWriter.name("taskName");
                    jsonWriter.value(taskName);
                }
                if (createExportTaskRequest.getLogGroupName() != null) {
                    String logGroupName = createExportTaskRequest.getLogGroupName();
                    jsonWriter.name("logGroupName");
                    jsonWriter.value(logGroupName);
                }
                if (createExportTaskRequest.getLogStreamNamePrefix() != null) {
                    String logStreamNamePrefix = createExportTaskRequest.getLogStreamNamePrefix();
                    jsonWriter.name("logStreamNamePrefix");
                    jsonWriter.value(logStreamNamePrefix);
                }
                if (createExportTaskRequest.getFrom() != null) {
                    Long from = createExportTaskRequest.getFrom();
                    jsonWriter.name(ADMConstants.EXTRA_FROM);
                    jsonWriter.value(from);
                }
                if (createExportTaskRequest.getTo() != null) {
                    Long to = createExportTaskRequest.getTo();
                    jsonWriter.name("to");
                    jsonWriter.value(to);
                }
                if (createExportTaskRequest.getDestination() != null) {
                    String destination = createExportTaskRequest.getDestination();
                    jsonWriter.name("destination");
                    jsonWriter.value(destination);
                }
                if (createExportTaskRequest.getDestinationPrefix() != null) {
                    String destinationPrefix = createExportTaskRequest.getDestinationPrefix();
                    jsonWriter.name("destinationPrefix");
                    jsonWriter.value(destinationPrefix);
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
        throw new AmazonClientException("Invalid argument passed to marshall(CreateExportTaskRequest)");
    }
}
