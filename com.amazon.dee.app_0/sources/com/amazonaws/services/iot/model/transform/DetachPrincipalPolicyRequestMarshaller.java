package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DetachPrincipalPolicyRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DetachPrincipalPolicyRequestMarshaller implements Marshaller<Request<DetachPrincipalPolicyRequest>, DetachPrincipalPolicyRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DetachPrincipalPolicyRequest> marshall(DetachPrincipalPolicyRequest detachPrincipalPolicyRequest) {
        if (detachPrincipalPolicyRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(detachPrincipalPolicyRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.DELETE);
            if (detachPrincipalPolicyRequest.getPrincipal() != null) {
                defaultRequest.addHeader("x-amzn-iot-principal", StringUtils.fromString(detachPrincipalPolicyRequest.getPrincipal()));
            }
            if (!GeneratedOutlineSupport1.outline190("/principal-policies/{policyName}", "{policyName}", detachPrincipalPolicyRequest.getPolicyName() == null ? "" : StringUtils.fromString(detachPrincipalPolicyRequest.getPolicyName()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DetachPrincipalPolicyRequest)");
    }
}
