package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.iot.model.InvalidStateTransitionException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class InvalidStateTransitionExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public InvalidStateTransitionExceptionUnmarshaller() {
        super(InvalidStateTransitionException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("InvalidStateTransitionException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        InvalidStateTransitionException invalidStateTransitionException = (InvalidStateTransitionException) super.unmarshall(jsonErrorResponse);
        invalidStateTransitionException.setErrorCode("InvalidStateTransitionException");
        return invalidStateTransitionException;
    }
}
