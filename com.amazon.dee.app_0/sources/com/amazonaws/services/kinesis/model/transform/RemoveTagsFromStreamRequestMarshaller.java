package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.kinesis.model.RemoveTagsFromStreamRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
import java.util.List;
/* loaded from: classes13.dex */
public class RemoveTagsFromStreamRequestMarshaller implements Marshaller<Request<RemoveTagsFromStreamRequest>, RemoveTagsFromStreamRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<RemoveTagsFromStreamRequest> marshall(RemoveTagsFromStreamRequest removeTagsFromStreamRequest) {
        if (removeTagsFromStreamRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(removeTagsFromStreamRequest, "AmazonKinesis");
            defaultRequest.addHeader("X-Amz-Target", "Kinesis_20131202.RemoveTagsFromStream");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (removeTagsFromStreamRequest.getStreamName() != null) {
                    String streamName = removeTagsFromStreamRequest.getStreamName();
                    jsonWriter.name("StreamName");
                    jsonWriter.value(streamName);
                }
                if (removeTagsFromStreamRequest.getTagKeys() != null) {
                    List<String> tagKeys = removeTagsFromStreamRequest.getTagKeys();
                    jsonWriter.name("TagKeys");
                    jsonWriter.beginArray();
                    for (String str : tagKeys) {
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
        throw new AmazonClientException("Invalid argument passed to marshall(RemoveTagsFromStreamRequest)");
    }
}
