package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.GetLoggingOptionsRequest;
import com.amazonaws.transform.Marshaller;
/* loaded from: classes13.dex */
public class GetLoggingOptionsRequestMarshaller implements Marshaller<Request<GetLoggingOptionsRequest>, GetLoggingOptionsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetLoggingOptionsRequest> marshall(GetLoggingOptionsRequest getLoggingOptionsRequest) {
        if (getLoggingOptionsRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(getLoggingOptionsRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            defaultRequest.setResourcePath("/loggingOptions");
            if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(GetLoggingOptionsRequest)");
    }
}
