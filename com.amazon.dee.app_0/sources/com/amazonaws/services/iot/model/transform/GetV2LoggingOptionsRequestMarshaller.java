package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.GetV2LoggingOptionsRequest;
import com.amazonaws.transform.Marshaller;
/* loaded from: classes13.dex */
public class GetV2LoggingOptionsRequestMarshaller implements Marshaller<Request<GetV2LoggingOptionsRequest>, GetV2LoggingOptionsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetV2LoggingOptionsRequest> marshall(GetV2LoggingOptionsRequest getV2LoggingOptionsRequest) {
        if (getV2LoggingOptionsRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(getV2LoggingOptionsRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            defaultRequest.setResourcePath("/v2LoggingOptions");
            if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(GetV2LoggingOptionsRequest)");
    }
}
