package com.amazonaws.services.iotdata.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.iotdata.model.RequestEntityTooLargeException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class RequestEntityTooLargeExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public RequestEntityTooLargeExceptionUnmarshaller() {
        super(RequestEntityTooLargeException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("RequestEntityTooLargeException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        RequestEntityTooLargeException requestEntityTooLargeException = (RequestEntityTooLargeException) super.unmarshall(jsonErrorResponse);
        requestEntityTooLargeException.setErrorCode("RequestEntityTooLargeException");
        return requestEntityTooLargeException;
    }
}
