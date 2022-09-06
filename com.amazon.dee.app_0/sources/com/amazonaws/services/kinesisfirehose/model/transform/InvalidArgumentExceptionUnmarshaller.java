package com.amazonaws.services.kinesisfirehose.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.kinesisfirehose.model.InvalidArgumentException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class InvalidArgumentExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public InvalidArgumentExceptionUnmarshaller() {
        super(InvalidArgumentException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("InvalidArgumentException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        InvalidArgumentException invalidArgumentException = (InvalidArgumentException) super.unmarshall(jsonErrorResponse);
        invalidArgumentException.setErrorCode("InvalidArgumentException");
        return invalidArgumentException;
    }
}
