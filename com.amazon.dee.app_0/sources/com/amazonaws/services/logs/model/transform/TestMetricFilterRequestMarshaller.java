package com.amazonaws.services.logs.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.logs.model.TestMetricFilterRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
import java.util.List;
/* loaded from: classes13.dex */
public class TestMetricFilterRequestMarshaller implements Marshaller<Request<TestMetricFilterRequest>, TestMetricFilterRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<TestMetricFilterRequest> marshall(TestMetricFilterRequest testMetricFilterRequest) {
        if (testMetricFilterRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(testMetricFilterRequest, "AmazonCloudWatchLogs");
            defaultRequest.addHeader("X-Amz-Target", "Logs_20140328.TestMetricFilter");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (testMetricFilterRequest.getFilterPattern() != null) {
                    String filterPattern = testMetricFilterRequest.getFilterPattern();
                    jsonWriter.name("filterPattern");
                    jsonWriter.value(filterPattern);
                }
                if (testMetricFilterRequest.getLogEventMessages() != null) {
                    List<String> logEventMessages = testMetricFilterRequest.getLogEventMessages();
                    jsonWriter.name("logEventMessages");
                    jsonWriter.beginArray();
                    for (String str : logEventMessages) {
                        if (str != null) {
                            jsonWriter.value(str);
                        }
                    }
                    jsonWriter.endArray();
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
        throw new AmazonClientException("Invalid argument passed to marshall(TestMetricFilterRequest)");
    }
}
