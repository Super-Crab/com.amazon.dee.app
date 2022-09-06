package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeEventConfigurationsRequest;
import com.amazonaws.transform.Marshaller;
/* loaded from: classes13.dex */
public class DescribeEventConfigurationsRequestMarshaller implements Marshaller<Request<DescribeEventConfigurationsRequest>, DescribeEventConfigurationsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeEventConfigurationsRequest> marshall(DescribeEventConfigurationsRequest describeEventConfigurationsRequest) {
        if (describeEventConfigurationsRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(describeEventConfigurationsRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            defaultRequest.setResourcePath("/event-configurations");
            if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DescribeEventConfigurationsRequest)");
    }
}
