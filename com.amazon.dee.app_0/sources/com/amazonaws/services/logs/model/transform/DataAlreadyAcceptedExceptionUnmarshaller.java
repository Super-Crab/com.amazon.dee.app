package com.amazonaws.services.logs.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.logs.model.DataAlreadyAcceptedException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class DataAlreadyAcceptedExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public DataAlreadyAcceptedExceptionUnmarshaller() {
        super(DataAlreadyAcceptedException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("DataAlreadyAcceptedException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        DataAlreadyAcceptedException dataAlreadyAcceptedException = (DataAlreadyAcceptedException) super.unmarshall(jsonErrorResponse);
        dataAlreadyAcceptedException.setErrorCode("DataAlreadyAcceptedException");
        dataAlreadyAcceptedException.setExpectedSequenceToken(String.valueOf(jsonErrorResponse.get("expectedSequenceToken")));
        return dataAlreadyAcceptedException;
    }
}
