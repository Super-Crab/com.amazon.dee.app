package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AttachSecurityProfileRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import java.io.ByteArrayInputStream;
/* loaded from: classes13.dex */
public class AttachSecurityProfileRequestMarshaller implements Marshaller<Request<AttachSecurityProfileRequest>, AttachSecurityProfileRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<AttachSecurityProfileRequest> marshall(AttachSecurityProfileRequest attachSecurityProfileRequest) {
        if (attachSecurityProfileRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(attachSecurityProfileRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.PUT);
            String replace = "/security-profiles/{securityProfileName}/targets".replace("{securityProfileName}", attachSecurityProfileRequest.getSecurityProfileName() == null ? "" : StringUtils.fromString(attachSecurityProfileRequest.getSecurityProfileName()));
            if (attachSecurityProfileRequest.getSecurityProfileTargetArn() != null) {
                defaultRequest.addParameter("securityProfileTargetArn", StringUtils.fromString(attachSecurityProfileRequest.getSecurityProfileTargetArn()));
            }
            defaultRequest.setResourcePath(replace);
            defaultRequest.addHeader("Content-Length", "0");
            defaultRequest.setContent(new ByteArrayInputStream(new byte[0]));
            if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(AttachSecurityProfileRequest)");
    }
}
