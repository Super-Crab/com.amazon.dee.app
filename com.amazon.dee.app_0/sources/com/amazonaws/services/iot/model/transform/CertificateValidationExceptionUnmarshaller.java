package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.iot.model.CertificateValidationException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class CertificateValidationExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public CertificateValidationExceptionUnmarshaller() {
        super(CertificateValidationException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("CertificateValidationException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        CertificateValidationException certificateValidationException = (CertificateValidationException) super.unmarshall(jsonErrorResponse);
        certificateValidationException.setErrorCode("CertificateValidationException");
        return certificateValidationException;
    }
}
