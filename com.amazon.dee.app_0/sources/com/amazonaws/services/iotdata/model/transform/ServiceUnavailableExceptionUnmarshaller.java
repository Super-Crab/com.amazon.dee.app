package com.amazonaws.services.iotdata.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.iotdata.model.ServiceUnavailableException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class ServiceUnavailableExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public ServiceUnavailableExceptionUnmarshaller() {
        super(ServiceUnavailableException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("ServiceUnavailableException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        ServiceUnavailableException serviceUnavailableException = (ServiceUnavailableException) super.unmarshall(jsonErrorResponse);
        serviceUnavailableException.setErrorCode("ServiceUnavailableException");
        return serviceUnavailableException;
    }
}
