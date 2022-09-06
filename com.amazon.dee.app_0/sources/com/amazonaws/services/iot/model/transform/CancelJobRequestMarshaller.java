package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CancelJobRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
/* loaded from: classes13.dex */
public class CancelJobRequestMarshaller implements Marshaller<Request<CancelJobRequest>, CancelJobRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CancelJobRequest> marshall(CancelJobRequest cancelJobRequest) {
        if (cancelJobRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(cancelJobRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.PUT);
            String replace = "/jobs/{jobId}/cancel".replace("{jobId}", cancelJobRequest.getJobId() == null ? "" : StringUtils.fromString(cancelJobRequest.getJobId()));
            if (cancelJobRequest.getForce() != null) {
                defaultRequest.addParameter("force", StringUtils.fromBoolean(cancelJobRequest.getForce()));
            }
            defaultRequest.setResourcePath(replace);
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (cancelJobRequest.getReasonCode() != null) {
                    String reasonCode = cancelJobRequest.getReasonCode();
                    jsonWriter.name("reasonCode");
                    jsonWriter.value(reasonCode);
                }
                if (cancelJobRequest.getComment() != null) {
                    String comment = cancelJobRequest.getComment();
                    jsonWriter.name("comment");
                    jsonWriter.value(comment);
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
        throw new AmazonClientException("Invalid argument passed to marshall(CancelJobRequest)");
    }
}
