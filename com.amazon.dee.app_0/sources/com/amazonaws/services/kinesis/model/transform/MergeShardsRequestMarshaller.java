package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.kinesis.model.MergeShardsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
/* loaded from: classes13.dex */
public class MergeShardsRequestMarshaller implements Marshaller<Request<MergeShardsRequest>, MergeShardsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<MergeShardsRequest> marshall(MergeShardsRequest mergeShardsRequest) {
        if (mergeShardsRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(mergeShardsRequest, "AmazonKinesis");
            defaultRequest.addHeader("X-Amz-Target", "Kinesis_20131202.MergeShards");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (mergeShardsRequest.getStreamName() != null) {
                    String streamName = mergeShardsRequest.getStreamName();
                    jsonWriter.name("StreamName");
                    jsonWriter.value(streamName);
                }
                if (mergeShardsRequest.getShardToMerge() != null) {
                    String shardToMerge = mergeShardsRequest.getShardToMerge();
                    jsonWriter.name("ShardToMerge");
                    jsonWriter.value(shardToMerge);
                }
                if (mergeShardsRequest.getAdjacentShardToMerge() != null) {
                    String adjacentShardToMerge = mergeShardsRequest.getAdjacentShardToMerge();
                    jsonWriter.name("AdjacentShardToMerge");
                    jsonWriter.value(adjacentShardToMerge);
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
        throw new AmazonClientException("Invalid argument passed to marshall(MergeShardsRequest)");
    }
}
