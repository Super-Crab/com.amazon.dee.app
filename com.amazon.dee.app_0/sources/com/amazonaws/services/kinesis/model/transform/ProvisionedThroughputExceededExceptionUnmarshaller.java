package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.kinesis.model.ProvisionedThroughputExceededException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class ProvisionedThroughputExceededExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public ProvisionedThroughputExceededExceptionUnmarshaller() {
        super(ProvisionedThroughputExceededException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("ProvisionedThroughputExceededException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        ProvisionedThroughputExceededException provisionedThroughputExceededException = (ProvisionedThroughputExceededException) super.unmarshall(jsonErrorResponse);
        provisionedThroughputExceededException.setErrorCode("ProvisionedThroughputExceededException");
        return provisionedThroughputExceededException;
    }
}
