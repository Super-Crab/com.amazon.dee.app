package com.amazonaws.services.logs.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.logs.model.InvalidOperationException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class InvalidOperationExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public InvalidOperationExceptionUnmarshaller() {
        super(InvalidOperationException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("InvalidOperationException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        InvalidOperationException invalidOperationException = (InvalidOperationException) super.unmarshall(jsonErrorResponse);
        invalidOperationException.setErrorCode("InvalidOperationException");
        return invalidOperationException;
    }
}
