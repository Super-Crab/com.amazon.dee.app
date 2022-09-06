package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler;
import com.amazonaws.services.iot.model.NotConfiguredException;
import com.amazonaws.transform.JsonErrorUnmarshaller;
/* loaded from: classes13.dex */
public class NotConfiguredExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public NotConfiguredExceptionUnmarshaller() {
        super(NotConfiguredException.class);
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller
    public boolean match(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        return jsonErrorResponse.getErrorCode().equals("NotConfiguredException");
    }

    @Override // com.amazonaws.transform.JsonErrorUnmarshaller, com.amazonaws.transform.Unmarshaller
    public AmazonServiceException unmarshall(JsonErrorResponseHandler.JsonErrorResponse jsonErrorResponse) throws Exception {
        NotConfiguredException notConfiguredException = (NotConfiguredException) super.unmarshall(jsonErrorResponse);
        notConfiguredException.setErrorCode("NotConfiguredException");
        return notConfiguredException;
    }
}
