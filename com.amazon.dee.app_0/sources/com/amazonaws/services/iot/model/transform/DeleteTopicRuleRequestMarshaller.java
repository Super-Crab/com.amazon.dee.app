package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteTopicRuleRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DeleteTopicRuleRequestMarshaller implements Marshaller<Request<DeleteTopicRuleRequest>, DeleteTopicRuleRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteTopicRuleRequest> marshall(DeleteTopicRuleRequest deleteTopicRuleRequest) {
        if (deleteTopicRuleRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(deleteTopicRuleRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.DELETE);
            if (!GeneratedOutlineSupport1.outline190("/rules/{ruleName}", "{ruleName}", deleteTopicRuleRequest.getRuleName() == null ? "" : StringUtils.fromString(deleteTopicRuleRequest.getRuleName()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DeleteTopicRuleRequest)");
    }
}
