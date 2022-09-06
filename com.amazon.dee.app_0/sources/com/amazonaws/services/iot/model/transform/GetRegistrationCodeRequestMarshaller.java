package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.GetRegistrationCodeRequest;
import com.amazonaws.transform.Marshaller;
/* loaded from: classes13.dex */
public class GetRegistrationCodeRequestMarshaller implements Marshaller<Request<GetRegistrationCodeRequest>, GetRegistrationCodeRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<GetRegistrationCodeRequest> marshall(GetRegistrationCodeRequest getRegistrationCodeRequest) {
        if (getRegistrationCodeRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(getRegistrationCodeRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            defaultRequest.setResourcePath("/registrationcode");
            if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(GetRegistrationCodeRequest)");
    }
}
