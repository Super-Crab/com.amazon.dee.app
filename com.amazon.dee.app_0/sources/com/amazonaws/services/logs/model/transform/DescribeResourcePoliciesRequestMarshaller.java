package com.amazonaws.services.logs.model.transform;

import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.logs.model.DescribeResourcePoliciesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
/* loaded from: classes13.dex */
public class DescribeResourcePoliciesRequestMarshaller implements Marshaller<Request<DescribeResourcePoliciesRequest>, DescribeResourcePoliciesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeResourcePoliciesRequest> marshall(DescribeResourcePoliciesRequest describeResourcePoliciesRequest) {
        if (describeResourcePoliciesRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(describeResourcePoliciesRequest, "AmazonCloudWatchLogs");
            defaultRequest.addHeader("X-Amz-Target", "Logs_20140328.DescribeResourcePolicies");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (describeResourcePoliciesRequest.getNextToken() != null) {
                    String nextToken = describeResourcePoliciesRequest.getNextToken();
                    jsonWriter.name("nextToken");
                    jsonWriter.value(nextToken);
                }
                if (describeResourcePoliciesRequest.getLimit() != null) {
                    Integer limit = describeResourcePoliciesRequest.getLimit();
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
        throw new AmazonClientException("Invalid argument passed to marshall(DescribeResourcePoliciesRequest)");
    }
}
