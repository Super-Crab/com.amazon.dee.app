package com.amazonaws.services.iotdata.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.iotdata.model.UnsupportedDocumentEncodingException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class UnsupportedDocumentEncodingExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public UnsupportedDocumentEncodingExceptionUnmarshaller() {
        super(UnsupportedDocumentEncodingException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("UnsupportedDocumentEncodingException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        UnsupportedDocumentEncodingException unsupportedDocumentEncodingException = (UnsupportedDocumentEncodingException) super.unmarshall(jsonErrorResponse);
        unsupportedDocumentEncodingException.setErrorCode("UnsupportedDocumentEncodingException");
        return unsupportedDocumentEncodingException;
    }
}
