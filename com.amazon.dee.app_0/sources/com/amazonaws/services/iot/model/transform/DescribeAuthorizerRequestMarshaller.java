package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeAuthorizerRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DescribeAuthorizerRequestMarshaller implements Marshaller<Request<DescribeAuthorizerRequest>, DescribeAuthorizerRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeAuthorizerRequest> marshall(DescribeAuthorizerRequest describeAuthorizerRequest) {
        if (describeAuthorizerRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(describeAuthorizerRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (!GeneratedOutlineSupport1.outline190("/authorizer/{authorizerName}", "{authorizerName}", describeAuthorizerRequest.getAuthorizerName() == null ? "" : StringUtils.fromString(describeAuthorizerRequest.getAuthorizerName()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DescribeAuthorizerRequest)");
    }
}
