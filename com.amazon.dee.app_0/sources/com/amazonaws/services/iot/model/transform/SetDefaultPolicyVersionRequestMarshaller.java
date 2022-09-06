package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.SetDefaultPolicyVersionRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class SetDefaultPolicyVersionRequestMarshaller implements Marshaller<Request<SetDefaultPolicyVersionRequest>, SetDefaultPolicyVersionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<SetDefaultPolicyVersionRequest> marshall(SetDefaultPolicyVersionRequest setDefaultPolicyVersionRequest) {
        if (setDefaultPolicyVersionRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(setDefaultPolicyVersionRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.PATCH);
            String str = "";
            String replace = "/policies/{policyName}/version/{policyVersionId}".replace("{policyName}", setDefaultPolicyVersionRequest.getPolicyName() == null ? str : StringUtils.fromString(setDefaultPolicyVersionRequest.getPolicyName()));
            if (setDefaultPolicyVersionRequest.getPolicyVersionId() != null) {
                str = StringUtils.fromString(setDefaultPolicyVersionRequest.getPolicyVersionId());
            }
            if (!GeneratedOutlineSupport1.outline190(replace, "{policyVersionId}", str, defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(SetDefaultPolicyVersionRequest)");
    }
}
