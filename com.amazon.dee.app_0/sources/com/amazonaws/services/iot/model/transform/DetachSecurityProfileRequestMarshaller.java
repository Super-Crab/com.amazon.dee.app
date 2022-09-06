package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DetachSecurityProfileRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DetachSecurityProfileRequestMarshaller implements Marshaller<Request<DetachSecurityProfileRequest>, DetachSecurityProfileRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DetachSecurityProfileRequest> marshall(DetachSecurityProfileRequest detachSecurityProfileRequest) {
        if (detachSecurityProfileRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(detachSecurityProfileRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.DELETE);
            String replace = "/security-profiles/{securityProfileName}/targets".replace("{securityProfileName}", detachSecurityProfileRequest.getSecurityProfileName() == null ? "" : StringUtils.fromString(detachSecurityProfileRequest.getSecurityProfileName()));
            if (detachSecurityProfileRequest.getSecurityProfileTargetArn() != null) {
                defaultRequest.addParameter("securityProfileTargetArn", StringUtils.fromString(detachSecurityProfileRequest.getSecurityProfileTargetArn()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, replace, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DetachSecurityProfileRequest)");
    }
}
