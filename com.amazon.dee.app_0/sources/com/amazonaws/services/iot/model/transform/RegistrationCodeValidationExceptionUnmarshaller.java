package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.iot.model.RegistrationCodeValidationException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class RegistrationCodeValidationExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public RegistrationCodeValidationExceptionUnmarshaller() {
        super(RegistrationCodeValidationException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("RegistrationCodeValidationException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        RegistrationCodeValidationException registrationCodeValidationException = (RegistrationCodeValidationException) super.unmarshall(jsonErrorResponse);
        registrationCodeValidationException.setErrorCode("RegistrationCodeValidationException");
        return registrationCodeValidationException;
    }
}
