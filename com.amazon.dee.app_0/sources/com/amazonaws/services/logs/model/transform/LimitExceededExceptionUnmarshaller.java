package com.amazonaws.services.logs.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.logs.model.LimitExceededException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class LimitExceededExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public LimitExceededExceptionUnmarshaller() {
        super(LimitExceededException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("LimitExceededException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        LimitExceededException limitExceededException = (LimitExceededException) super.unmarshall(jsonErrorResponse);
        limitExceededException.setErrorCode("LimitExceededException");
        return limitExceededException;
    }
}
