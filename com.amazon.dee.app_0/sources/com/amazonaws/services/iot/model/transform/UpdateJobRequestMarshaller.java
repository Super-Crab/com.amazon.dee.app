package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AbortConfig;
import com.amazonaws.services.iot.model.JobExecutionsRolloutConfig;
import com.amazonaws.services.iot.model.PresignedUrlConfig;
import com.amazonaws.services.iot.model.TimeoutConfig;
import com.amazonaws.services.iot.model.UpdateJobRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
/* loaded from: classes13.dex */
public class UpdateJobRequestMarshaller implements Marshaller<Request<UpdateJobRequest>, UpdateJobRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateJobRequest> marshall(UpdateJobRequest updateJobRequest) {
        if (updateJobRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(updateJobRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.PATCH);
            defaultRequest.setResourcePath("/jobs/{jobId}".replace("{jobId}", updateJobRequest.getJobId() == null ? "" : StringUtils.fromString(updateJobRequest.getJobId())));
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (updateJobRequest.getDescription() != null) {
                    String description = updateJobRequest.getDescription();
                    jsonWriter.name("description");
                    jsonWriter.value(description);
                }
                if (updateJobRequest.getPresignedUrlConfig() != null) {
                    PresignedUrlConfig presignedUrlConfig = updateJobRequest.getPresignedUrlConfig();
                    jsonWriter.name("presignedUrlConfig");
                    PresignedUrlConfigJsonMarshaller.getInstance().marshall(presignedUrlConfig, jsonWriter);
                }
                if (updateJobRequest.getJobExecutionsRolloutConfig() != null) {
                    JobExecutionsRolloutConfig jobExecutionsRolloutConfig = updateJobRequest.getJobExecutionsRolloutConfig();
                    jsonWriter.name("jobExecutionsRolloutConfig");
                    JobExecutionsRolloutConfigJsonMarshaller.getInstance().marshall(jobExecutionsRolloutConfig, jsonWriter);
                }
                if (updateJobRequest.getAbortConfig() != null) {
                    AbortConfig abortConfig = updateJobRequest.getAbortConfig();
                    jsonWriter.name("abortConfig");
                    AbortConfigJsonMarshaller.getInstance().marshall(abortConfig, jsonWriter);
                }
                if (updateJobRequest.getTimeoutConfig() != null) {
                    TimeoutConfig timeoutConfig = updateJobRequest.getTimeoutConfig();
                    jsonWriter.name("timeoutConfig");
                    TimeoutConfigJsonMarshaller.getInstance().marshall(timeoutConfig, jsonWriter);
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
        throw new AmazonClientException("Invalid argument passed to marshall(UpdateJobRequest)");
    }
}
