package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DisableTopicRuleRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import java.io.ByteArrayInputStream;
/* loaded from: classes13.dex */
public class DisableTopicRuleRequestMarshaller implements Marshaller<Request<DisableTopicRuleRequest>, DisableTopicRuleRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DisableTopicRuleRequest> marshall(DisableTopicRuleRequest disableTopicRuleRequest) {
        if (disableTopicRuleRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(disableTopicRuleRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/rules/{ruleName}/disable".replace("{ruleName}", disableTopicRuleRequest.getRuleName() == null ? "" : StringUtils.fromString(disableTopicRuleRequest.getRuleName())));
            defaultRequest.addHeader("Content-Length", "0");
            defaultRequest.setContent(new ByteArrayInputStream(new byte[0]));
            if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DisableTopicRuleRequest)");
    }
}
