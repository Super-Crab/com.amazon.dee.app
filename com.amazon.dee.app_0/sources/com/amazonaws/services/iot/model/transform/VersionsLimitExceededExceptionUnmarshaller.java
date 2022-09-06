package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.iot.model.VersionsLimitExceededException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class VersionsLimitExceededExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public VersionsLimitExceededExceptionUnmarshaller() {
        super(VersionsLimitExceededException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("VersionsLimitExceededException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        VersionsLimitExceededException versionsLimitExceededException = (VersionsLimitExceededException) super.unmarshall(jsonErrorResponse);
        versionsLimitExceededException.setErrorCode("VersionsLimitExceededException");
        return versionsLimitExceededException;
    }
}
