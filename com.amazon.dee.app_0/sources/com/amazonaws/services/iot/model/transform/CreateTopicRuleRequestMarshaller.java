package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CreateTopicRuleRequest;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
/* loaded from: classes13.dex */
public class CreateTopicRuleRequestMarshaller implements Marshaller<Request<CreateTopicRuleRequest>, CreateTopicRuleRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateTopicRuleRequest> marshall(CreateTopicRuleRequest createTopicRuleRequest) {
        if (createTopicRuleRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(createTopicRuleRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            if (createTopicRuleRequest.getTags() != null) {
                defaultRequest.addHeader(Headers.S3_TAGGING, StringUtils.fromString(createTopicRuleRequest.getTags()));
            }
            defaultRequest.setResourcePath("/rules/{ruleName}".replace("{ruleName}", createTopicRuleRequest.getRuleName() == null ? "" : StringUtils.fromString(createTopicRuleRequest.getRuleName())));
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                if (createTopicRuleRequest.getTopicRulePayload() != null) {
                    TopicRulePayloadJsonMarshaller.getInstance().marshall(createTopicRuleRequest.getTopicRulePayload(), jsonWriter);
                }
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
        throw new AmazonClientException("Invalid argument passed to marshall(CreateTopicRuleRequest)");
    }
}
