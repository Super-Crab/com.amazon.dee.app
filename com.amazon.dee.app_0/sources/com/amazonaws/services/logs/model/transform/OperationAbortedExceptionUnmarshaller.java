package com.amazonaws.services.logs.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.logs.model.OperationAbortedException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class OperationAbortedExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public OperationAbortedExceptionUnmarshaller() {
        super(OperationAbortedException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("OperationAbortedException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        OperationAbortedException operationAbortedException = (OperationAbortedException) super.unmarshall(jsonErrorResponse);
        operationAbortedException.setErrorCode("OperationAbortedException");
        return operationAbortedException;
    }
}
