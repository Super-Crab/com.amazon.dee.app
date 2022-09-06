package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.kinesis.model.ExpiredIteratorException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class ExpiredIteratorExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public ExpiredIteratorExceptionUnmarshaller() {
        super(ExpiredIteratorException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("ExpiredIteratorException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        ExpiredIteratorException expiredIteratorException = (ExpiredIteratorException) super.unmarshall(jsonErrorResponse);
        expiredIteratorException.setErrorCode("ExpiredIteratorException");
        return expiredIteratorException;
    }
}
