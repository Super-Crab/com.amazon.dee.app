package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteDynamicThingGroupRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DeleteDynamicThingGroupRequestMarshaller implements Marshaller<Request<DeleteDynamicThingGroupRequest>, DeleteDynamicThingGroupRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteDynamicThingGroupRequest> marshall(DeleteDynamicThingGroupRequest deleteDynamicThingGroupRequest) {
        if (deleteDynamicThingGroupRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(deleteDynamicThingGroupRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.DELETE);
            String replace = "/dynamic-thing-groups/{thingGroupName}".replace("{thingGroupName}", deleteDynamicThingGroupRequest.getThingGroupName() == null ? "" : StringUtils.fromString(deleteDynamicThingGroupRequest.getThingGroupName()));
            if (deleteDynamicThingGroupRequest.getExpectedVersion() != null) {
                defaultRequest.addParameter("expectedVersion", StringUtils.fromLong(deleteDynamicThingGroupRequest.getExpectedVersion()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, replace, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DeleteDynamicThingGroupRequest)");
    }
}
