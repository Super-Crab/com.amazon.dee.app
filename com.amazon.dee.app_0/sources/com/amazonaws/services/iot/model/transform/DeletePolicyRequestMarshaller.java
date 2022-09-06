package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeletePolicyRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DeletePolicyRequestMarshaller implements Marshaller<Request<DeletePolicyRequest>, DeletePolicyRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeletePolicyRequest> marshall(DeletePolicyRequest deletePolicyRequest) {
        if (deletePolicyRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(deletePolicyRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.DELETE);
            if (!GeneratedOutlineSupport1.outline190("/policies/{policyName}", "{policyName}", deletePolicyRequest.getPolicyName() == null ? "" : StringUtils.fromString(deletePolicyRequest.getPolicyName()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DeletePolicyRequest)");
    }
}
