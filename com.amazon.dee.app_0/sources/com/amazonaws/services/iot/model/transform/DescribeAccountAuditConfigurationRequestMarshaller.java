package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeAccountAuditConfigurationRequest;
import com.amazonaws.transform.Marshaller;
/* loaded from: classes13.dex */
public class DescribeAccountAuditConfigurationRequestMarshaller implements Marshaller<Request<DescribeAccountAuditConfigurationRequest>, DescribeAccountAuditConfigurationRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeAccountAuditConfigurationRequest> marshall(DescribeAccountAuditConfigurationRequest describeAccountAuditConfigurationRequest) {
        if (describeAccountAuditConfigurationRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(describeAccountAuditConfigurationRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            defaultRequest.setResourcePath("/audit/configuration");
            if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DescribeAccountAuditConfigurationRequest)");
    }
}
