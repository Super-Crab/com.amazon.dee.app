package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.iot.model.ResourceAlreadyExistsException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class ResourceAlreadyExistsExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public ResourceAlreadyExistsExceptionUnmarshaller() {
        super(ResourceAlreadyExistsException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("ResourceAlreadyExistsException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        ResourceAlreadyExistsException resourceAlreadyExistsException = (ResourceAlreadyExistsException) super.unmarshall(jsonErrorResponse);
        resourceAlreadyExistsException.setErrorCode("ResourceAlreadyExistsException");
        resourceAlreadyExistsException.setResourceId(String.valueOf(jsonErrorResponse.get("resourceId")));
        resourceAlreadyExistsException.setResourceArn(String.valueOf(jsonErrorResponse.get("resourceArn")));
        return resourceAlreadyExistsException;
    }
}
