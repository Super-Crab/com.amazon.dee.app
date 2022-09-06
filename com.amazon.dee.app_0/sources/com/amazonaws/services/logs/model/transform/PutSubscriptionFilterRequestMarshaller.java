package com.amazonaws.services.logs.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.logs.model.PutSubscriptionFilterRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
/* loaded from: classes13.dex */
public class PutSubscriptionFilterRequestMarshaller implements Marshaller<Request<PutSubscriptionFilterRequest>, PutSubscriptionFilterRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<PutSubscriptionFilterRequest> marshall(PutSubscriptionFilterRequest putSubscriptionFilterRequest) {
        if (putSubscriptionFilterRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(putSubscriptionFilterRequest, "AmazonCloudWatchLogs");
            defaultRequest.addHeader("X-Amz-Target", "Logs_20140328.PutSubscriptionFilter");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (putSubscriptionFilterRequest.getLogGroupName() != null) {
                    String logGroupName = putSubscriptionFilterRequest.getLogGroupName();
                    jsonWriter.name("logGroupName");
                    jsonWriter.value(logGroupName);
                }
                if (putSubscriptionFilterRequest.getFilterName() != null) {
                    String filterName = putSubscriptionFilterRequest.getFilterName();
                    jsonWriter.name("filterName");
                    jsonWriter.value(filterName);
                }
                if (putSubscriptionFilterRequest.getFilterPattern() != null) {
                    String filterPattern = putSubscriptionFilterRequest.getFilterPattern();
                    jsonWriter.name("filterPattern");
                    jsonWriter.value(filterPattern);
                }
                if (putSubscriptionFilterRequest.getDestinationArn() != null) {
                    String destinationArn = putSubscriptionFilterRequest.getDestinationArn();
                    jsonWriter.name("destinationArn");
                    jsonWriter.value(destinationArn);
                }
                if (putSubscriptionFilterRequest.getRoleArn() != null) {
                    String roleArn = putSubscriptionFilterRequest.getRoleArn();
                    jsonWriter.name("roleArn");
                    jsonWriter.value(roleArn);
                }
                if (putSubscriptionFilterRequest.getDistribution() != null) {
                    String distribution = putSubscriptionFilterRequest.getDistribution();
                    jsonWriter.name("distribution");
                    jsonWriter.value(distribution);
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
        throw new AmazonClientException("Invalid argument passed to marshall(PutSubscriptionFilterRequest)");
    }
}
