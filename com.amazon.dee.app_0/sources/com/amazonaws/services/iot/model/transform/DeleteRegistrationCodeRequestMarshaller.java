package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteRegistrationCodeRequest;
import com.amazonaws.transform.Marshaller;
/* loaded from: classes13.dex */
public class DeleteRegistrationCodeRequestMarshaller implements Marshaller<Request<DeleteRegistrationCodeRequest>, DeleteRegistrationCodeRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteRegistrationCodeRequest> marshall(DeleteRegistrationCodeRequest deleteRegistrationCodeRequest) {
        if (deleteRegistrationCodeRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(deleteRegistrationCodeRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.DELETE);
            defaultRequest.setResourcePath("/registrationcode");
            if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DeleteRegistrationCodeRequest)");
    }
}
