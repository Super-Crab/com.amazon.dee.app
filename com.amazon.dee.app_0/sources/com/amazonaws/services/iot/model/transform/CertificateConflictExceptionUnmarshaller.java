package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.iot.model.CertificateConflictException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class CertificateConflictExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public CertificateConflictExceptionUnmarshaller() {
        super(CertificateConflictException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("CertificateConflictException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        CertificateConflictException certificateConflictException = (CertificateConflictException) super.unmarshall(jsonErrorResponse);
        certificateConflictException.setErrorCode("CertificateConflictException");
        return certificateConflictException;
    }
}
