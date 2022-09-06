package com.amazonaws.services.logs.model.transform;

import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.logs.model.GetLogEventsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
/* loaded from: classes13.dex */
public class GetLogEventsRequestMarshaller implements Marshaller<Request<GetLogEventsRequest>, GetLogEventsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetLogEventsRequest> marshall(GetLogEventsRequest getLogEventsRequest) {
        if (getLogEventsRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(getLogEventsRequest, "AmazonCloudWatchLogs");
            defaultRequest.addHeader("X-Amz-Target", "Logs_20140328.GetLogEvents");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (getLogEventsRequest.getLogGroupName() != null) {
                    String logGroupName = getLogEventsRequest.getLogGroupName();
                    jsonWriter.name("logGroupName");
                    jsonWriter.value(logGroupName);
                }
                if (getLogEventsRequest.getLogStreamName() != null) {
                    String logStreamName = getLogEventsRequest.getLogStreamName();
                    jsonWriter.name("logStreamName");
                    jsonWriter.value(logStreamName);
                }
                if (getLogEventsRequest.getStartTime() != null) {
                    Long startTime = getLogEventsRequest.getStartTime();
                    jsonWriter.name("startTime");
                    jsonWriter.value(startTime);
                }
                if (getLogEventsRequest.getEndTime() != null) {
                    Long endTime = getLogEventsRequest.getEndTime();
                    jsonWriter.name("endTime");
                    jsonWriter.value(endTime);
                }
                if (getLogEventsRequest.getNextToken() != null) {
                    String nextToken = getLogEventsRequest.getNextToken();
                    jsonWriter.name("nextToken");
                    jsonWriter.value(nextToken);
                }
                if (getLogEventsRequest.getLimit() != null) {
                    Integer limit = getLogEventsRequest.getLimit();
                    jsonWriter.name(MetricsUtil.LegacyMetricTypes.LIMIT);
                    jsonWriter.value(limit);
                }
                if (getLogEventsRequest.getStartFromHead() != null) {
                    Boolean startFromHead = getLogEventsRequest.getStartFromHead();
                    jsonWriter.name("startFromHead");
                    jsonWriter.value(startFromHead.booleanValue());
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
        throw new AmazonClientException("Invalid argument passed to marshall(GetLogEventsRequest)");
    }
}
