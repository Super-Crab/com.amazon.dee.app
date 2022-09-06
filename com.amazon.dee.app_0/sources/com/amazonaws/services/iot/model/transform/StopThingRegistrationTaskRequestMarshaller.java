package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.StopThingRegistrationTaskRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import java.io.ByteArrayInputStream;
/* loaded from: classes13.dex */
public class StopThingRegistrationTaskRequestMarshaller implements Marshaller<Request<StopThingRegistrationTaskRequest>, StopThingRegistrationTaskRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<StopThingRegistrationTaskRequest> marshall(StopThingRegistrationTaskRequest stopThingRegistrationTaskRequest) {
        if (stopThingRegistrationTaskRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(stopThingRegistrationTaskRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.PUT);
            defaultRequest.setResourcePath("/thing-registration-tasks/{taskId}/cancel".replace("{taskId}", stopThingRegistrationTaskRequest.getTaskId() == null ? "" : StringUtils.fromString(stopThingRegistrationTaskRequest.getTaskId())));
            defaultRequest.addHeader("Content-Length", "0");
            defaultRequest.setContent(new ByteArrayInputStream(new byte[0]));
            if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(StopThingRegistrationTaskRequest)");
    }
}
