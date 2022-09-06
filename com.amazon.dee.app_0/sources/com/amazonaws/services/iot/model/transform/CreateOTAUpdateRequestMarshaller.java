package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AwsJobExecutionsRolloutConfig;
import com.amazonaws.services.iot.model.CreateOTAUpdateRequest;
import com.amazonaws.services.iot.model.OTAUpdateFile;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
/* loaded from: classes13.dex */
public class CreateOTAUpdateRequestMarshaller implements Marshaller<Request<CreateOTAUpdateRequest>, CreateOTAUpdateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateOTAUpdateRequest> marshall(CreateOTAUpdateRequest createOTAUpdateRequest) {
        if (createOTAUpdateRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(createOTAUpdateRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/otaUpdates/{otaUpdateId}".replace("{otaUpdateId}", createOTAUpdateRequest.getOtaUpdateId() == null ? "" : StringUtils.fromString(createOTAUpdateRequest.getOtaUpdateId())));
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (createOTAUpdateRequest.getDescription() != null) {
                    String description = createOTAUpdateRequest.getDescription();
                    jsonWriter.name("description");
                    jsonWriter.value(description);
                }
                if (createOTAUpdateRequest.getTargets() != null) {
                    List<String> targets = createOTAUpdateRequest.getTargets();
                    jsonWriter.name("targets");
                    jsonWriter.beginArray();
                    for (String str : targets) {
                        if (str != null) {
                            jsonWriter.value(str);
                        }
                    }
                    jsonWriter.endArray();
                }
                if (createOTAUpdateRequest.getTargetSelection() != null) {
                    String targetSelection = createOTAUpdateRequest.getTargetSelection();
                    jsonWriter.name("targetSelection");
                    jsonWriter.value(targetSelection);
                }
                if (createOTAUpdateRequest.getAwsJobExecutionsRolloutConfig() != null) {
                    AwsJobExecutionsRolloutConfig awsJobExecutionsRolloutConfig = createOTAUpdateRequest.getAwsJobExecutionsRolloutConfig();
                    jsonWriter.name("awsJobExecutionsRolloutConfig");
                    AwsJobExecutionsRolloutConfigJsonMarshaller.getInstance().marshall(awsJobExecutionsRolloutConfig, jsonWriter);
                }
                if (createOTAUpdateRequest.getFiles() != null) {
                    List<OTAUpdateFile> files = createOTAUpdateRequest.getFiles();
                    jsonWriter.name("files");
                    jsonWriter.beginArray();
                    for (OTAUpdateFile oTAUpdateFile : files) {
                        if (oTAUpdateFile != null) {
                            OTAUpdateFileJsonMarshaller.getInstance().marshall(oTAUpdateFile, jsonWriter);
                        }
                    }
                    jsonWriter.endArray();
                }
                if (createOTAUpdateRequest.getRoleArn() != null) {
                    String roleArn = createOTAUpdateRequest.getRoleArn();
                    jsonWriter.name("roleArn");
                    jsonWriter.value(roleArn);
                }
                if (createOTAUpdateRequest.getAdditionalParameters() != null) {
                    Map<String, String> additionalParameters = createOTAUpdateRequest.getAdditionalParameters();
                    jsonWriter.name("additionalParameters");
                    jsonWriter.beginObject();
                    for (Map.Entry<String, String> entry : additionalParameters.entrySet()) {
                        String value = entry.getValue();
                        if (value != null) {
                            jsonWriter.name(entry.getKey());
                            jsonWriter.value(value);
                        }
                    }
                    jsonWriter.endObject();
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
        throw new AmazonClientException("Invalid argument passed to marshall(CreateOTAUpdateRequest)");
    }
}
