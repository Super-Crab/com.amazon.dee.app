package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListThingsInThingGroupRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListThingsInThingGroupRequestMarshaller implements Marshaller<Request<ListThingsInThingGroupRequest>, ListThingsInThingGroupRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListThingsInThingGroupRequest> marshall(ListThingsInThingGroupRequest listThingsInThingGroupRequest) {
        if (listThingsInThingGroupRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listThingsInThingGroupRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            String replace = "/thing-groups/{thingGroupName}/things".replace("{thingGroupName}", listThingsInThingGroupRequest.getThingGroupName() == null ? "" : StringUtils.fromString(listThingsInThingGroupRequest.getThingGroupName()));
            if (listThingsInThingGroupRequest.getRecursive() != null) {
                defaultRequest.addParameter("recursive", StringUtils.fromBoolean(listThingsInThingGroupRequest.getRecursive()));
            }
            if (listThingsInThingGroupRequest.getNextToken() != null) {
                defaultRequest.addParameter("nextToken", StringUtils.fromString(listThingsInThingGroupRequest.getNextToken()));
            }
            if (listThingsInThingGroupRequest.getMaxResults() != null) {
                defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listThingsInThingGroupRequest.getMaxResults()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, replace, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListThingsInThingGroupRequest)");
    }
}
