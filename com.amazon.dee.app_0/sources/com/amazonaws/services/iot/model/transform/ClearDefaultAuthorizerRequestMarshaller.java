package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ClearDefaultAuthorizerRequest;
import com.amazonaws.transform.Marshaller;
/* loaded from: classes13.dex */
public class ClearDefaultAuthorizerRequestMarshaller implements Marshaller<Request<ClearDefaultAuthorizerRequest>, ClearDefaultAuthorizerRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ClearDefaultAuthorizerRequest> marshall(ClearDefaultAuthorizerRequest clearDefaultAuthorizerRequest) {
        if (clearDefaultAuthorizerRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(clearDefaultAuthorizerRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.DELETE);
            defaultRequest.setResourcePath("/default-authorizer");
            if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ClearDefaultAuthorizerRequest)");
    }
}
