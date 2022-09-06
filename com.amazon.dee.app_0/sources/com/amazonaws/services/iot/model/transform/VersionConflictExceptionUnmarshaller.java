package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.iot.model.VersionConflictException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class VersionConflictExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public VersionConflictExceptionUnmarshaller() {
        super(VersionConflictException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("VersionConflictException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        VersionConflictException versionConflictException = (VersionConflictException) super.unmarshall(jsonErrorResponse);
        versionConflictException.setErrorCode("VersionConflictException");
        return versionConflictException;
    }
}
