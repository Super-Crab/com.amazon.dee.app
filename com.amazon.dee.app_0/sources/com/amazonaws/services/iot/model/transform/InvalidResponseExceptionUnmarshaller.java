package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.iot.model.InvalidResponseException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class InvalidResponseExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public InvalidResponseExceptionUnmarshaller() {
        super(InvalidResponseException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("InvalidResponseException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        InvalidResponseException invalidResponseException = (InvalidResponseException) super.unmarshall(jsonErrorResponse);
        invalidResponseException.setErrorCode("InvalidResponseException");
        return invalidResponseException;
    }
}
