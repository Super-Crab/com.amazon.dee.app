package com.amazonaws.services.logs.model.transform;

import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.logs.model.FilterLogEventsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
import java.util.List;
/* loaded from: classes13.dex */
public class FilterLogEventsRequestMarshaller implements Marshaller<Request<FilterLogEventsRequest>, FilterLogEventsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<FilterLogEventsRequest> marshall(FilterLogEventsRequest filterLogEventsRequest) {
        if (filterLogEventsRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(filterLogEventsRequest, "AmazonCloudWatchLogs");
            defaultRequest.addHeader("X-Amz-Target", "Logs_20140328.FilterLogEvents");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (filterLogEventsRequest.getLogGroupName() != null) {
                    String logGroupName = filterLogEventsRequest.getLogGroupName();
                    jsonWriter.name("logGroupName");
                    jsonWriter.value(logGroupName);
                }
                if (filterLogEventsRequest.getLogStreamNames() != null) {
                    List<String> logStreamNames = filterLogEventsRequest.getLogStreamNames();
                    jsonWriter.name("logStreamNames");
                    jsonWriter.beginArray();
                    for (String str : logStreamNames) {
                        if (str != null) {
                            jsonWriter.value(str);
                        }
                    }
                    jsonWriter.endArray();
                }
                if (filterLogEventsRequest.getLogStreamNamePrefix() != null) {
                    String logStreamNamePrefix = filterLogEventsRequest.getLogStreamNamePrefix();
                    jsonWriter.name("logStreamNamePrefix");
                    jsonWriter.value(logStreamNamePrefix);
                }
                if (filterLogEventsRequest.getStartTime() != null) {
                    Long startTime = filterLogEventsRequest.getStartTime();
                    jsonWriter.name("startTime");
                    jsonWriter.value(startTime);
                }
                if (filterLogEventsRequest.getEndTime() != null) {
                    Long endTime = filterLogEventsRequest.getEndTime();
                    jsonWriter.name("endTime");
                    jsonWriter.value(endTime);
                }
                if (filterLogEventsRequest.getFilterPattern() != null) {
                    String filterPattern = filterLogEventsRequest.getFilterPattern();
                    jsonWriter.name("filterPattern");
                    jsonWriter.value(filterPattern);
                }
                if (filterLogEventsRequest.getNextToken() != null) {
                    String nextToken = filterLogEventsRequest.getNextToken();
                    jsonWriter.name("nextToken");
                    jsonWriter.value(nextToken);
                }
                if (filterLogEventsRequest.getLimit() != null) {
                    Integer limit = filterLogEventsRequest.getLimit();
                    jsonWriter.name(MetricsUtil.LegacyMetricTypes.LIMIT);
                    jsonWriter.value(limit);
                }
                if (filterLogEventsRequest.getInterleaved() != null) {
                    Boolean interleaved = filterLogEventsRequest.getInterleaved();
                    jsonWriter.name("interleaved");
                    jsonWriter.value(interleaved.booleanValue());
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
        throw new AmazonClientException("Invalid argument passed to marshall(FilterLogEventsRequest)");
    }
}
