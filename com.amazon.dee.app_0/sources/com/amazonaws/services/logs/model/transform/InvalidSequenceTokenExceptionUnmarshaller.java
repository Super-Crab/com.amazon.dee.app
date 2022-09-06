package com.amazonaws.services.logs.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.logs.model.InvalidSequenceTokenException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class InvalidSequenceTokenExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public InvalidSequenceTokenExceptionUnmarshaller() {
        super(InvalidSequenceTokenException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("InvalidSequenceTokenException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        InvalidSequenceTokenException invalidSequenceTokenException = (InvalidSequenceTokenException) super.unmarshall(jsonErrorResponse);
        invalidSequenceTokenException.setErrorCode("InvalidSequenceTokenException");
        invalidSequenceTokenException.setExpectedSequenceToken(String.valueOf(jsonErrorResponse.get("expectedSequenceToken")));
        return invalidSequenceTokenException;
    }
}
