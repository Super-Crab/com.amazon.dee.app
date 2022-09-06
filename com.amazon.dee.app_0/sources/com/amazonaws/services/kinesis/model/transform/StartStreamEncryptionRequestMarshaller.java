package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.kinesis.model.StartStreamEncryptionRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
/* loaded from: classes13.dex */
public class StartStreamEncryptionRequestMarshaller implements Marshaller<Request<StartStreamEncryptionRequest>, StartStreamEncryptionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<StartStreamEncryptionRequest> marshall(StartStreamEncryptionRequest startStreamEncryptionRequest) {
        if (startStreamEncryptionRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(startStreamEncryptionRequest, "AmazonKinesis");
            defaultRequest.addHeader("X-Amz-Target", "Kinesis_20131202.StartStreamEncryption");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (startStreamEncryptionRequest.getStreamName() != null) {
                    String streamName = startStreamEncryptionRequest.getStreamName();
                    jsonWriter.name("StreamName");
                    jsonWriter.value(streamName);
                }
                if (startStreamEncryptionRequest.getEncryptionType() != null) {
                    String encryptionType = startStreamEncryptionRequest.getEncryptionType();
                    jsonWriter.name("EncryptionType");
                    jsonWriter.value(encryptionType);
                }
                if (startStreamEncryptionRequest.getKeyId() != null) {
                    String keyId = startStreamEncryptionRequest.getKeyId();
                    jsonWriter.name("KeyId");
                    jsonWriter.value(keyId);
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
        throw new AmazonClientException("Invalid argument passed to marshall(StartStreamEncryptionRequest)");
    }
}
