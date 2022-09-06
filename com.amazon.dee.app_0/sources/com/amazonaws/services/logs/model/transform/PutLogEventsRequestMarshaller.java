package com.amazonaws.services.logs.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.logs.model.InputLogEvent;
import com.amazonaws.services.logs.model.PutLogEventsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
import java.util.List;
/* loaded from: classes13.dex */
public class PutLogEventsRequestMarshaller implements Marshaller<Request<PutLogEventsRequest>, PutLogEventsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<PutLogEventsRequest> marshall(PutLogEventsRequest putLogEventsRequest) {
        if (putLogEventsRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(putLogEventsRequest, "AmazonCloudWatchLogs");
            defaultRequest.addHeader("X-Amz-Target", "Logs_20140328.PutLogEvents");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (putLogEventsRequest.getLogGroupName() != null) {
                    String logGroupName = putLogEventsRequest.getLogGroupName();
                    jsonWriter.name("logGroupName");
                    jsonWriter.value(logGroupName);
                }
                if (putLogEventsRequest.getLogStreamName() != null) {
                    String logStreamName = putLogEventsRequest.getLogStreamName();
                    jsonWriter.name("logStreamName");
                    jsonWriter.value(logStreamName);
                }
                if (putLogEventsRequest.getLogEvents() != null) {
                    List<InputLogEvent> logEvents = putLogEventsRequest.getLogEvents();
                    jsonWriter.name("logEvents");
                    jsonWriter.beginArray();
                    for (InputLogEvent inputLogEvent : logEvents) {
                        if (inputLogEvent != null) {
                            InputLogEventJsonMarshaller.getInstance().marshall(inputLogEvent, jsonWriter);
                        }
                    }
                    jsonWriter.endArray();
                }
                if (putLogEventsRequest.getSequenceToken() != null) {
                    String sequenceToken = putLogEventsRequest.getSequenceToken();
                    jsonWriter.name("sequenceToken");
                    jsonWriter.value(sequenceToken);
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
        throw new AmazonClientException("Invalid argument passed to marshall(PutLogEventsRequest)");
    }
}
