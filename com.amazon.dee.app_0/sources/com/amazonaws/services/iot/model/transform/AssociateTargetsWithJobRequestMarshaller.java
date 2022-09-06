package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AssociateTargetsWithJobRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
import java.util.List;
/* loaded from: classes13.dex */
public class AssociateTargetsWithJobRequestMarshaller implements Marshaller<Request<AssociateTargetsWithJobRequest>, AssociateTargetsWithJobRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<AssociateTargetsWithJobRequest> marshall(AssociateTargetsWithJobRequest associateTargetsWithJobRequest) {
        if (associateTargetsWithJobRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(associateTargetsWithJobRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/jobs/{jobId}/targets".replace("{jobId}", associateTargetsWithJobRequest.getJobId() == null ? "" : StringUtils.fromString(associateTargetsWithJobRequest.getJobId())));
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (associateTargetsWithJobRequest.getTargets() != null) {
                    List<String> targets = associateTargetsWithJobRequest.getTargets();
                    jsonWriter.name("targets");
                    jsonWriter.beginArray();
                    for (String str : targets) {
                        if (str != null) {
                            jsonWriter.value(str);
                        }
                    }
                    jsonWriter.endArray();
                }
                if (associateTargetsWithJobRequest.getComment() != null) {
                    String comment = associateTargetsWithJobRequest.getComment();
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
        throw new AmazonClientException("Invalid argument passed to marshall(AssociateTargetsWithJobRequest)");
    }
}
