package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.iot.model.DeleteConflictException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class DeleteConflictExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public DeleteConflictExceptionUnmarshaller() {
        super(DeleteConflictException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("DeleteConflictException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        DeleteConflictException deleteConflictException = (DeleteConflictException) super.unmarshall(jsonErrorResponse);
        deleteConflictException.setErrorCode("DeleteConflictException");
        return deleteConflictException;
    }
}
