package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeEndpointRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DescribeEndpointRequestMarshaller implements Marshaller<Request<DescribeEndpointRequest>, DescribeEndpointRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeEndpointRequest> marshall(DescribeEndpointRequest describeEndpointRequest) {
        if (describeEndpointRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(describeEndpointRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (describeEndpointRequest.getEndpointType() != null) {
                defaultRequest.addParameter("endpointType", StringUtils.fromString(describeEndpointRequest.getEndpointType()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, "/endpoint", "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DescribeEndpointRequest)");
    }
}
