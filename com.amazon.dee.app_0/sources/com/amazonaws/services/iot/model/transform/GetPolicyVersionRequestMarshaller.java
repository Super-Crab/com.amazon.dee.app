package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.GetPolicyVersionRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class GetPolicyVersionRequestMarshaller implements Marshaller<Request<GetPolicyVersionRequest>, GetPolicyVersionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetPolicyVersionRequest> marshall(GetPolicyVersionRequest getPolicyVersionRequest) {
        if (getPolicyVersionRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(getPolicyVersionRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            String str = "";
            String replace = "/policies/{policyName}/version/{policyVersionId}".replace("{policyName}", getPolicyVersionRequest.getPolicyName() == null ? str : StringUtils.fromString(getPolicyVersionRequest.getPolicyName()));
            if (getPolicyVersionRequest.getPolicyVersionId() != null) {
                str = StringUtils.fromString(getPolicyVersionRequest.getPolicyVersionId());
            }
            if (!GeneratedOutlineSupport1.outline190(replace, "{policyVersionId}", str, defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(GetPolicyVersionRequest)");
    }
}
