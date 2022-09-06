package com.amazonaws.services.logs.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.logs.model.UnrecognizedClientException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class UnrecognizedClientExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public UnrecognizedClientExceptionUnmarshaller() {
        super(UnrecognizedClientException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("UnrecognizedClientException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        UnrecognizedClientException unrecognizedClientException = (UnrecognizedClientException) super.unmarshall(jsonErrorResponse);
        unrecognizedClientException.setErrorCode("UnrecognizedClientException");
        return unrecognizedClientException;
    }
}
