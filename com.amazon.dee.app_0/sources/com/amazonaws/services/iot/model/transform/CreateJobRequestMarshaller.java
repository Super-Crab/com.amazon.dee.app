package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AbortConfig;
import com.amazonaws.services.iot.model.CreateJobRequest;
import com.amazonaws.services.iot.model.JobExecutionsRolloutConfig;
import com.amazonaws.services.iot.model.PresignedUrlConfig;
import com.amazonaws.services.iot.model.Tag;
import com.amazonaws.services.iot.model.TimeoutConfig;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
import java.util.List;
/* loaded from: classes13.dex */
public class CreateJobRequestMarshaller implements Marshaller<Request<CreateJobRequest>, CreateJobRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateJobRequest> marshall(CreateJobRequest createJobRequest) {
        if (createJobRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(createJobRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.PUT);
            defaultRequest.setResourcePath("/jobs/{jobId}".replace("{jobId}", createJobRequest.getJobId() == null ? "" : StringUtils.fromString(createJobRequest.getJobId())));
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (createJobRequest.getTargets() != null) {
                    List<String> targets = createJobRequest.getTargets();
                    jsonWriter.name("targets");
                    jsonWriter.beginArray();
                    for (String str : targets) {
                        if (str != null) {
                            jsonWriter.value(str);
                        }
                    }
                    jsonWriter.endArray();
                }
                if (createJobRequest.getDocumentSource() != null) {
                    String documentSource = createJobRequest.getDocumentSource();
                    jsonWriter.name("documentSource");
                    jsonWriter.value(documentSource);
                }
                if (createJobRequest.getDocument() != null) {
                    String document = createJobRequest.getDocument();
                    jsonWriter.name("document");
                    jsonWriter.value(document);
                }
                if (createJobRequest.getDescription() != null) {
                    String description = createJobRequest.getDescription();
                    jsonWriter.name("description");
                    jsonWriter.value(description);
                }
                if (createJobRequest.getPresignedUrlConfig() != null) {
                    PresignedUrlConfig presignedUrlConfig = createJobRequest.getPresignedUrlConfig();
                    jsonWriter.name("presignedUrlConfig");
                    PresignedUrlConfigJsonMarshaller.getInstance().marshall(presignedUrlConfig, jsonWriter);
                }
                if (createJobRequest.getTargetSelection() != null) {
                    String targetSelection = createJobRequest.getTargetSelection();
                    jsonWriter.name("targetSelection");
                    jsonWriter.value(targetSelection);
                }
                if (createJobRequest.getJobExecutionsRolloutConfig() != null) {
                    JobExecutionsRolloutConfig jobExecutionsRolloutConfig = createJobRequest.getJobExecutionsRolloutConfig();
                    jsonWriter.name("jobExecutionsRolloutConfig");
                    JobExecutionsRolloutConfigJsonMarshaller.getInstance().marshall(jobExecutionsRolloutConfig, jsonWriter);
                }
                if (createJobRequest.getAbortConfig() != null) {
                    AbortConfig abortConfig = createJobRequest.getAbortConfig();
                    jsonWriter.name("abortConfig");
                    AbortConfigJsonMarshaller.getInstance().marshall(abortConfig, jsonWriter);
                }
                if (createJobRequest.getTimeoutConfig() != null) {
                    TimeoutConfig timeoutConfig = createJobRequest.getTimeoutConfig();
                    jsonWriter.name("timeoutConfig");
                    TimeoutConfigJsonMarshaller.getInstance().marshall(timeoutConfig, jsonWriter);
                }
                if (createJobRequest.getTags() != null) {
                    List<Tag> tags = createJobRequest.getTags();
                    jsonWriter.name("tags");
                    jsonWriter.beginArray();
                    for (Tag tag : tags) {
                        if (tag != null) {
                            TagJsonMarshaller.getInstance().marshall(tag, jsonWriter);
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
                    defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
                }
                return defaultRequest;
            } catch (Throwable th) {
                throw new AmazonClientException(GeneratedOutlineSupport1.outline98(th, GeneratedOutlineSupport1.outline107("Unable to marshall request to JSON: ")), th);
            }
        }
        throw new AmazonClientException("Invalid argument passed to marshall(CreateJobRequest)");
    }
}
