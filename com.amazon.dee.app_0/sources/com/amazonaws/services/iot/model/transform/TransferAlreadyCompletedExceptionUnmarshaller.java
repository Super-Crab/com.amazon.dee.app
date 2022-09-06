package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.iot.model.TransferAlreadyCompletedException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class TransferAlreadyCompletedExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public TransferAlreadyCompletedExceptionUnmarshaller() {
        super(TransferAlreadyCompletedException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("TransferAlreadyCompletedException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        TransferAlreadyCompletedException transferAlreadyCompletedException = (TransferAlreadyCompletedException) super.unmarshall(jsonErrorResponse);
        transferAlreadyCompletedException.setErrorCode("TransferAlreadyCompletedException");
        return transferAlreadyCompletedException;
    }
}
