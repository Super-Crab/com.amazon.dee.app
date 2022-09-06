package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteThingRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DeleteThingRequestMarshaller implements Marshaller<Request<DeleteThingRequest>, DeleteThingRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteThingRequest> marshall(DeleteThingRequest deleteThingRequest) {
        if (deleteThingRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(deleteThingRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.DELETE);
            String replace = "/things/{thingName}".replace("{thingName}", deleteThingRequest.getThingName() == null ? "" : StringUtils.fromString(deleteThingRequest.getThingName()));
            if (deleteThingRequest.getExpectedVersion() != null) {
                defaultRequest.addParameter("expectedVersion", StringUtils.fromLong(deleteThingRequest.getExpectedVersion()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, replace, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DeleteThingRequest)");
    }
}
