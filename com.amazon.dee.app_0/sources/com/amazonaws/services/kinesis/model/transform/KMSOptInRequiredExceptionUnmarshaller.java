package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.kinesis.model.KMSOptInRequiredException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class KMSOptInRequiredExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public KMSOptInRequiredExceptionUnmarshaller() {
        super(KMSOptInRequiredException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("KMSOptInRequired");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        KMSOptInRequiredException kMSOptInRequiredException = (KMSOptInRequiredException) super.unmarshall(jsonErrorResponse);
        kMSOptInRequiredException.setErrorCode("KMSOptInRequired");
        return kMSOptInRequiredException;
    }
}
