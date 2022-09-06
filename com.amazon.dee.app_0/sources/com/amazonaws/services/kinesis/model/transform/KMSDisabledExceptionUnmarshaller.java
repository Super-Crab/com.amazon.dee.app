package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.kinesis.model.KMSDisabledException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class KMSDisabledExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public KMSDisabledExceptionUnmarshaller() {
        super(KMSDisabledException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("KMSDisabledException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        KMSDisabledException kMSDisabledException = (KMSDisabledException) super.unmarshall(jsonErrorResponse);
        kMSDisabledException.setErrorCode("KMSDisabledException");
        return kMSDisabledException;
    }
}
