package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeSecurityProfileRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DescribeSecurityProfileRequestMarshaller implements Marshaller<Request<DescribeSecurityProfileRequest>, DescribeSecurityProfileRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeSecurityProfileRequest> marshall(DescribeSecurityProfileRequest describeSecurityProfileRequest) {
        if (describeSecurityProfileRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(describeSecurityProfileRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (!GeneratedOutlineSupport1.outline190("/security-profiles/{securityProfileName}", "{securityProfileName}", describeSecurityProfileRequest.getSecurityProfileName() == null ? "" : StringUtils.fromString(describeSecurityProfileRequest.getSecurityProfileName()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DescribeSecurityProfileRequest)");
    }
}
