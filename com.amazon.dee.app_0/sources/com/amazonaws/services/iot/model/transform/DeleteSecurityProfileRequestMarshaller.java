package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteSecurityProfileRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DeleteSecurityProfileRequestMarshaller implements Marshaller<Request<DeleteSecurityProfileRequest>, DeleteSecurityProfileRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteSecurityProfileRequest> marshall(DeleteSecurityProfileRequest deleteSecurityProfileRequest) {
        if (deleteSecurityProfileRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(deleteSecurityProfileRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.DELETE);
            String replace = "/security-profiles/{securityProfileName}".replace("{securityProfileName}", deleteSecurityProfileRequest.getSecurityProfileName() == null ? "" : StringUtils.fromString(deleteSecurityProfileRequest.getSecurityProfileName()));
            if (deleteSecurityProfileRequest.getExpectedVersion() != null) {
                defaultRequest.addParameter("expectedVersion", StringUtils.fromLong(deleteSecurityProfileRequest.getExpectedVersion()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, replace, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DeleteSecurityProfileRequest)");
    }
}
