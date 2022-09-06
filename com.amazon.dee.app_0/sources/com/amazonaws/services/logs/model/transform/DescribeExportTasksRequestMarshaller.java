package com.amazonaws.services.logs.model.transform;

import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.logs.model.DescribeExportTasksRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
/* loaded from: classes13.dex */
public class DescribeExportTasksRequestMarshaller implements Marshaller<Request<DescribeExportTasksRequest>, DescribeExportTasksRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeExportTasksRequest> marshall(DescribeExportTasksRequest describeExportTasksRequest) {
        if (describeExportTasksRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(describeExportTasksRequest, "AmazonCloudWatchLogs");
            defaultRequest.addHeader("X-Amz-Target", "Logs_20140328.DescribeExportTasks");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (describeExportTasksRequest.getTaskId() != null) {
                    String taskId = describeExportTasksRequest.getTaskId();
                    jsonWriter.name("taskId");
                    jsonWriter.value(taskId);
                }
                if (describeExportTasksRequest.getStatusCode() != null) {
                    String statusCode = describeExportTasksRequest.getStatusCode();
                    jsonWriter.name("statusCode");
                    jsonWriter.value(statusCode);
                }
                if (describeExportTasksRequest.getNextToken() != null) {
                    String nextToken = describeExportTasksRequest.getNextToken();
                    jsonWriter.name("nextToken");
                    jsonWriter.value(nextToken);
                }
                if (describeExportTasksRequest.getLimit() != null) {
                    Integer limit = describeExportTasksRequest.getLimit();
                    jsonWriter.name(MetricsUtil.LegacyMetricTypes.LIMIT);
                    jsonWriter.value(limit);
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
        throw new AmazonClientException("Invalid argument passed to marshall(DescribeExportTasksRequest)");
    }
}
