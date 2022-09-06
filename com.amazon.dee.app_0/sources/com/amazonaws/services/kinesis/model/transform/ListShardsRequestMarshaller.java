package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.kinesis.model.ListShardsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
import java.util.Date;
/* loaded from: classes13.dex */
public class ListShardsRequestMarshaller implements Marshaller<Request<ListShardsRequest>, ListShardsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListShardsRequest> marshall(ListShardsRequest listShardsRequest) {
        if (listShardsRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listShardsRequest, "AmazonKinesis");
            defaultRequest.addHeader("X-Amz-Target", "Kinesis_20131202.ListShards");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (listShardsRequest.getStreamName() != null) {
                    String streamName = listShardsRequest.getStreamName();
                    jsonWriter.name("StreamName");
                    jsonWriter.value(streamName);
                }
                if (listShardsRequest.getNextToken() != null) {
                    String nextToken = listShardsRequest.getNextToken();
                    jsonWriter.name("NextToken");
                    jsonWriter.value(nextToken);
                }
                if (listShardsRequest.getExclusiveStartShardId() != null) {
                    String exclusiveStartShardId = listShardsRequest.getExclusiveStartShardId();
                    jsonWriter.name("ExclusiveStartShardId");
                    jsonWriter.value(exclusiveStartShardId);
                }
                if (listShardsRequest.getMaxResults() != null) {
                    Integer maxResults = listShardsRequest.getMaxResults();
                    jsonWriter.name("MaxResults");
                    jsonWriter.value(maxResults);
                }
                if (listShardsRequest.getStreamCreationTimestamp() != null) {
                    Date streamCreationTimestamp = listShardsRequest.getStreamCreationTimestamp();
                    jsonWriter.name("StreamCreationTimestamp");
                    jsonWriter.value(streamCreationTimestamp);
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
        throw new AmazonClientException("Invalid argument passed to marshall(ListShardsRequest)");
    }
}
