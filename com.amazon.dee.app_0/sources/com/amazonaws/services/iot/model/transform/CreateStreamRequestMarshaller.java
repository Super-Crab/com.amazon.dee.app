package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CreateStreamRequest;
import com.amazonaws.services.iot.model.StreamFile;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
import java.util.List;
/* loaded from: classes13.dex */
public class CreateStreamRequestMarshaller implements Marshaller<Request<CreateStreamRequest>, CreateStreamRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateStreamRequest> marshall(CreateStreamRequest createStreamRequest) {
        if (createStreamRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(createStreamRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/streams/{streamId}".replace("{streamId}", createStreamRequest.getStreamId() == null ? "" : StringUtils.fromString(createStreamRequest.getStreamId())));
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (createStreamRequest.getDescription() != null) {
                    String description = createStreamRequest.getDescription();
                    jsonWriter.name("description");
                    jsonWriter.value(description);
                }
                if (createStreamRequest.getFiles() != null) {
                    List<StreamFile> files = createStreamRequest.getFiles();
                    jsonWriter.name("files");
                    jsonWriter.beginArray();
                    for (StreamFile streamFile : files) {
                        if (streamFile != null) {
                            StreamFileJsonMarshaller.getInstance().marshall(streamFile, jsonWriter);
                        }
                    }
                    jsonWriter.endArray();
                }
                if (createStreamRequest.getRoleArn() != null) {
                    String roleArn = createStreamRequest.getRoleArn();
                    jsonWriter.name("roleArn");
                    jsonWriter.value(roleArn);
                }
                jsonWriter.endObject();
                jsonWriter.close();
                String stringWriter2 = stringWriter.toString();
                byte[] bytes = stringWriter2.getBytes(StringUtils.UTF8);
                defaultRequest.setContent(new StringInputStream(stringWriter2));
                defaultRequest.addHeader("Content-Length", Integer.toString(bytes.length));
                if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                    defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
                }
                return defaultRequest;
            } catch (Throwable th) {
                throw new AmazonClientException(GeneratedOutlineSupport1.outline98(th, GeneratedOutlineSupport1.outline107("Unable to marshall request to JSON: ")), th);
            }
        }
        throw new AmazonClientException("Invalid argument passed to marshall(CreateStreamRequest)");
    }
}
