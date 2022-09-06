package com.amazonaws.services.iotdata.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.iotdata.model.InvalidRequestException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class InvalidRequestExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public InvalidRequestExceptionUnmarshaller() {
        super(InvalidRequestException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("InvalidRequestException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        InvalidRequestException invalidRequestException = (InvalidRequestException) super.unmarshall(jsonErrorResponse);
        invalidRequestException.setErrorCode("InvalidRequestException");
        return invalidRequestException;
    }
}
