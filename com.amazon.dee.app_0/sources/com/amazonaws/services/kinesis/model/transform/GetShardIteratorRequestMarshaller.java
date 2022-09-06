package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.kinesis.model.GetShardIteratorRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
import java.util.Date;
/* loaded from: classes13.dex */
public class GetShardIteratorRequestMarshaller implements Marshaller<Request<GetShardIteratorRequest>, GetShardIteratorRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetShardIteratorRequest> marshall(GetShardIteratorRequest getShardIteratorRequest) {
        if (getShardIteratorRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(getShardIteratorRequest, "AmazonKinesis");
            defaultRequest.addHeader("X-Amz-Target", "Kinesis_20131202.GetShardIterator");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (getShardIteratorRequest.getStreamName() != null) {
                    String streamName = getShardIteratorRequest.getStreamName();
                    jsonWriter.name("StreamName");
                    jsonWriter.value(streamName);
                }
                if (getShardIteratorRequest.getShardId() != null) {
                    String shardId = getShardIteratorRequest.getShardId();
                    jsonWriter.name("ShardId");
                    jsonWriter.value(shardId);
                }
                if (getShardIteratorRequest.getShardIteratorType() != null) {
                    String shardIteratorType = getShardIteratorRequest.getShardIteratorType();
                    jsonWriter.name("ShardIteratorType");
                    jsonWriter.value(shardIteratorType);
                }
                if (getShardIteratorRequest.getStartingSequenceNumber() != null) {
                    String startingSequenceNumber = getShardIteratorRequest.getStartingSequenceNumber();
                    jsonWriter.name("StartingSequenceNumber");
                    jsonWriter.value(startingSequenceNumber);
                }
                if (getShardIteratorRequest.getTimestamp() != null) {
                    Date timestamp = getShardIteratorRequest.getTimestamp();
                    jsonWriter.name("Timestamp");
                    jsonWriter.value(timestamp);
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
        throw new AmazonClientException("Invalid argument passed to marshall(GetShardIteratorRequest)");
    }
}
