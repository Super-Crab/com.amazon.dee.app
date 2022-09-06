package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.kinesis.model.DescribeLimitsRequest;
import com.amazonaws.transform.Marshaller;
import java.io.ByteArrayInputStream;
/* loaded from: classes13.dex */
public class DescribeLimitsRequestMarshaller implements Marshaller<Request<DescribeLimitsRequest>, DescribeLimitsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeLimitsRequest> marshall(DescribeLimitsRequest describeLimitsRequest) {
        if (describeLimitsRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(describeLimitsRequest, "AmazonKinesis");
            defaultRequest.addHeader("X-Amz-Target", "Kinesis_20131202.DescribeLimits");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            defaultRequest.setResourcePath("/");
            defaultRequest.addHeader("Content-Length", "0");
            defaultRequest.setContent(new ByteArrayInputStream(new byte[0]));
            if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.1");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DescribeLimitsRequest)");
    }
}
