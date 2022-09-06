package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AttachPrincipalPolicyRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import java.io.ByteArrayInputStream;
/* loaded from: classes13.dex */
public class AttachPrincipalPolicyRequestMarshaller implements Marshaller<Request<AttachPrincipalPolicyRequest>, AttachPrincipalPolicyRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<AttachPrincipalPolicyRequest> marshall(AttachPrincipalPolicyRequest attachPrincipalPolicyRequest) {
        if (attachPrincipalPolicyRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(attachPrincipalPolicyRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.PUT);
            if (attachPrincipalPolicyRequest.getPrincipal() != null) {
                defaultRequest.addHeader("x-amzn-iot-principal", StringUtils.fromString(attachPrincipalPolicyRequest.getPrincipal()));
            }
            defaultRequest.setResourcePath("/principal-policies/{policyName}".replace("{policyName}", attachPrincipalPolicyRequest.getPolicyName() == null ? "" : StringUtils.fromString(attachPrincipalPolicyRequest.getPolicyName())));
            defaultRequest.addHeader("Content-Length", "0");
            defaultRequest.setContent(new ByteArrayInputStream(new byte[0]));
            if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(AttachPrincipalPolicyRequest)");
    }
}
