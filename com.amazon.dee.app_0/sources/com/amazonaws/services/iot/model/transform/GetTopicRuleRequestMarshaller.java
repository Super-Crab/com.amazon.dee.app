package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.GetTopicRuleRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class GetTopicRuleRequestMarshaller implements Marshaller<Request<GetTopicRuleRequest>, GetTopicRuleRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetTopicRuleRequest> marshall(GetTopicRuleRequest getTopicRuleRequest) {
        if (getTopicRuleRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(getTopicRuleRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (!GeneratedOutlineSupport1.outline190("/rules/{ruleName}", "{ruleName}", getTopicRuleRequest.getRuleName() == null ? "" : StringUtils.fromString(getTopicRuleRequest.getRuleName()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(GetTopicRuleRequest)");
    }
}
