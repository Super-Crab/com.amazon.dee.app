package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.iot.model.InvalidQueryException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class InvalidQueryExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public InvalidQueryExceptionUnmarshaller() {
        super(InvalidQueryException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("InvalidQueryException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        InvalidQueryException invalidQueryException = (InvalidQueryException) super.unmarshall(jsonErrorResponse);
        invalidQueryException.setErrorCode("InvalidQueryException");
        return invalidQueryException;
    }
}
