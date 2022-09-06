package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeDefaultAuthorizerRequest;
import com.amazonaws.transform.Marshaller;
/* loaded from: classes13.dex */
public class DescribeDefaultAuthorizerRequestMarshaller implements Marshaller<Request<DescribeDefaultAuthorizerRequest>, DescribeDefaultAuthorizerRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeDefaultAuthorizerRequest> marshall(DescribeDefaultAuthorizerRequest describeDefaultAuthorizerRequest) {
        if (describeDefaultAuthorizerRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(describeDefaultAuthorizerRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            defaultRequest.setResourcePath("/default-authorizer");
            if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DescribeDefaultAuthorizerRequest)");
    }
}
