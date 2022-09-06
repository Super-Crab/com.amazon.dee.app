package com.amazonaws.services.logs.model.transform;

import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.logs.model.DescribeLogStreamsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
/* loaded from: classes13.dex */
public class DescribeLogStreamsRequestMarshaller implements Marshaller<Request<DescribeLogStreamsRequest>, DescribeLogStreamsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeLogStreamsRequest> marshall(DescribeLogStreamsRequest describeLogStreamsRequest) {
        if (describeLogStreamsRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(describeLogStreamsRequest, "AmazonCloudWatchLogs");
            defaultRequest.addHeader("X-Amz-Target", "Logs_20140328.DescribeLogStreams");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (describeLogStreamsRequest.getLogGroupName() != null) {
                    String logGroupName = describeLogStreamsRequest.getLogGroupName();
                    jsonWriter.name("logGroupName");
                    jsonWriter.value(logGroupName);
                }
                if (describeLogStreamsRequest.getLogStreamNamePrefix() != null) {
                    String logStreamNamePrefix = describeLogStreamsRequest.getLogStreamNamePrefix();
                    jsonWriter.name("logStreamNamePrefix");
                    jsonWriter.value(logStreamNamePrefix);
                }
                if (describeLogStreamsRequest.getOrderBy() != null) {
                    String orderBy = describeLogStreamsRequest.getOrderBy();
                    jsonWriter.name("orderBy");
                    jsonWriter.value(orderBy);
                }
                if (describeLogStreamsRequest.getDescending() != null) {
                    Boolean descending = describeLogStreamsRequest.getDescending();
                    jsonWriter.name("descending");
                    jsonWriter.value(descending.booleanValue());
                }
                if (describeLogStreamsRequest.getNextToken() != null) {
                    String nextToken = describeLogStreamsRequest.getNextToken();
                    jsonWriter.name("nextToken");
                    jsonWriter.value(nextToken);
                }
                if (describeLogStreamsRequest.getLimit() != null) {
                    Integer limit = describeLogStreamsRequest.getLimit();
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
        throw new AmazonClientException("Invalid argument passed to marshall(DescribeLogStreamsRequest)");
    }
}
