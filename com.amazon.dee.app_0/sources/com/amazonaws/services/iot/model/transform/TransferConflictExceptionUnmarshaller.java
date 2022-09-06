package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.iot.model.TransferConflictException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class TransferConflictExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public TransferConflictExceptionUnmarshaller() {
        super(TransferConflictException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("TransferConflictException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        TransferConflictException transferConflictException = (TransferConflictException) super.unmarshall(jsonErrorResponse);
        transferConflictException.setErrorCode("TransferConflictException");
        return transferConflictException;
    }
}
