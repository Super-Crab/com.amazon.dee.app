package com.amazonaws.services.kinesis.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.kinesis.model.ResourceInUseException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class ResourceInUseExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public ResourceInUseExceptionUnmarshaller() {
        super(ResourceInUseException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("ResourceInUseException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        ResourceInUseException resourceInUseException = (ResourceInUseException) super.unmarshall(jsonErrorResponse);
        resourceInUseException.setErrorCode("ResourceInUseException");
        return resourceInUseException;
    }
}
