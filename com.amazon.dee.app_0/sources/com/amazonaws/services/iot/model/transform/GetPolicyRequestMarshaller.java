package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.GetPolicyRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class GetPolicyRequestMarshaller implements Marshaller<Request<GetPolicyRequest>, GetPolicyRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetPolicyRequest> marshall(GetPolicyRequest getPolicyRequest) {
        if (getPolicyRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(getPolicyRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (!GeneratedOutlineSupport1.outline190("/policies/{policyName}", "{policyName}", getPolicyRequest.getPolicyName() == null ? "" : StringUtils.fromString(getPolicyRequest.getPolicyName()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(GetPolicyRequest)");
    }
}
