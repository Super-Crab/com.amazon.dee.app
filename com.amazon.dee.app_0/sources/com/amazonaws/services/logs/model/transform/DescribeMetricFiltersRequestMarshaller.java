package com.amazonaws.services.logs.model.transform;

import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.logs.model.DescribeMetricFiltersRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
/* loaded from: classes13.dex */
public class DescribeMetricFiltersRequestMarshaller implements Marshaller<Request<DescribeMetricFiltersRequest>, DescribeMetricFiltersRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeMetricFiltersRequest> marshall(DescribeMetricFiltersRequest describeMetricFiltersRequest) {
        if (describeMetricFiltersRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(describeMetricFiltersRequest, "AmazonCloudWatchLogs");
            defaultRequest.addHeader("X-Amz-Target", "Logs_20140328.DescribeMetricFilters");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (describeMetricFiltersRequest.getLogGroupName() != null) {
                    String logGroupName = describeMetricFiltersRequest.getLogGroupName();
                    jsonWriter.name("logGroupName");
                    jsonWriter.value(logGroupName);
                }
                if (describeMetricFiltersRequest.getFilterNamePrefix() != null) {
                    String filterNamePrefix = describeMetricFiltersRequest.getFilterNamePrefix();
                    jsonWriter.name("filterNamePrefix");
                    jsonWriter.value(filterNamePrefix);
                }
                if (describeMetricFiltersRequest.getNextToken() != null) {
                    String nextToken = describeMetricFiltersRequest.getNextToken();
                    jsonWriter.name("nextToken");
                    jsonWriter.value(nextToken);
                }
                if (describeMetricFiltersRequest.getLimit() != null) {
                    Integer limit = describeMetricFiltersRequest.getLimit();
                    jsonWriter.name(MetricsUtil.LegacyMetricTypes.LIMIT);
                    jsonWriter.value(limit);
                }
                if (describeMetricFiltersRequest.getMetricName() != null) {
                    String metricName = describeMetricFiltersRequest.getMetricName();
                    jsonWriter.name("metricName");
                    jsonWriter.value(metricName);
                }
                if (describeMetricFiltersRequest.getMetricNamespace() != null) {
                    String metricNamespace = describeMetricFiltersRequest.getMetricNamespace();
                    jsonWriter.name("metricNamespace");
                    jsonWriter.value(metricNamespace);
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
        throw new AmazonClientException("Invalid argument passed to marshall(DescribeMetricFiltersRequest)");
    }
}
