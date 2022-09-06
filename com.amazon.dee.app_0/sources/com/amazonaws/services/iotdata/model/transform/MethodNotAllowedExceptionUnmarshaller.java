package com.amazonaws.services.iotdata.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.iotdata.model.MethodNotAllowedException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class MethodNotAllowedExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public MethodNotAllowedExceptionUnmarshaller() {
        super(MethodNotAllowedException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("MethodNotAllowedException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        MethodNotAllowedException methodNotAllowedException = (MethodNotAllowedException) super.unmarshall(jsonErrorResponse);
        methodNotAllowedException.setErrorCode("MethodNotAllowedException");
        return methodNotAllowedException;
    }
}
