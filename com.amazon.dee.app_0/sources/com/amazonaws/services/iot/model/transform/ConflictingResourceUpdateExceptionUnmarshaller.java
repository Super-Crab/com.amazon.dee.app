package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.iot.model.ConflictingResourceUpdateException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class ConflictingResourceUpdateExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public ConflictingResourceUpdateExceptionUnmarshaller() {
        super(ConflictingResourceUpdateException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("ConflictingResourceUpdateException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        ConflictingResourceUpdateException conflictingResourceUpdateException = (ConflictingResourceUpdateException) super.unmarshall(jsonErrorResponse);
        conflictingResourceUpdateException.setErrorCode("ConflictingResourceUpdateException");
        return conflictingResourceUpdateException;
    }
}
