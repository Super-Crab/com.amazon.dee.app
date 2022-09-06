package com.amazonaws.services.iotdata.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.iotdata.model.InternalFailureException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class InternalFailureExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public InternalFailureExceptionUnmarshaller() {
        super(InternalFailureException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("InternalFailureException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        InternalFailureException internalFailureException = (InternalFailureException) super.unmarshall(jsonErrorResponse);
        internalFailureException.setErrorCode("InternalFailureException");
        return internalFailureException;
    }
}
