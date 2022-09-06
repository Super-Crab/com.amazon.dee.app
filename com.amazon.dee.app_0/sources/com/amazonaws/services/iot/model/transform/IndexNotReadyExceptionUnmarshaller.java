package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.iot.model.IndexNotReadyException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class IndexNotReadyExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public IndexNotReadyExceptionUnmarshaller() {
        super(IndexNotReadyException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("IndexNotReadyException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        IndexNotReadyException indexNotReadyException = (IndexNotReadyException) super.unmarshall(jsonErrorResponse);
        indexNotReadyException.setErrorCode("IndexNotReadyException");
        return indexNotReadyException;
    }
}
