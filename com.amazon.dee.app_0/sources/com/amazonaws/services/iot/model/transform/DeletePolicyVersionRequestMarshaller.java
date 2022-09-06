package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeletePolicyVersionRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DeletePolicyVersionRequestMarshaller implements Marshaller<Request<DeletePolicyVersionRequest>, DeletePolicyVersionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeletePolicyVersionRequest> marshall(DeletePolicyVersionRequest deletePolicyVersionRequest) {
        if (deletePolicyVersionRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(deletePolicyVersionRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.DELETE);
            String str = "";
            String replace = "/policies/{policyName}/version/{policyVersionId}".replace("{policyName}", deletePolicyVersionRequest.getPolicyName() == null ? str : StringUtils.fromString(deletePolicyVersionRequest.getPolicyName()));
            if (deletePolicyVersionRequest.getPolicyVersionId() != null) {
                str = StringUtils.fromString(deletePolicyVersionRequest.getPolicyVersionId());
            }
            if (!GeneratedOutlineSupport1.outline190(replace, "{policyVersionId}", str, defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DeletePolicyVersionRequest)");
    }
}
