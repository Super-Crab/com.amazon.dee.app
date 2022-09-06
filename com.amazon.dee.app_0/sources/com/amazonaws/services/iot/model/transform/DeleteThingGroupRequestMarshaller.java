package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteThingGroupRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DeleteThingGroupRequestMarshaller implements Marshaller<Request<DeleteThingGroupRequest>, DeleteThingGroupRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteThingGroupRequest> marshall(DeleteThingGroupRequest deleteThingGroupRequest) {
        if (deleteThingGroupRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(deleteThingGroupRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.DELETE);
            String replace = "/thing-groups/{thingGroupName}".replace("{thingGroupName}", deleteThingGroupRequest.getThingGroupName() == null ? "" : StringUtils.fromString(deleteThingGroupRequest.getThingGroupName()));
            if (deleteThingGroupRequest.getExpectedVersion() != null) {
                defaultRequest.addParameter("expectedVersion", StringUtils.fromLong(deleteThingGroupRequest.getExpectedVersion()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, replace, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DeleteThingGroupRequest)");
    }
}
