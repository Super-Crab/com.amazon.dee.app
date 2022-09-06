package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListThingGroupsForThingRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListThingGroupsForThingRequestMarshaller implements Marshaller<Request<ListThingGroupsForThingRequest>, ListThingGroupsForThingRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListThingGroupsForThingRequest> marshall(ListThingGroupsForThingRequest listThingGroupsForThingRequest) {
        if (listThingGroupsForThingRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listThingGroupsForThingRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            String replace = "/things/{thingName}/thing-groups".replace("{thingName}", listThingGroupsForThingRequest.getThingName() == null ? "" : StringUtils.fromString(listThingGroupsForThingRequest.getThingName()));
            if (listThingGroupsForThingRequest.getNextToken() != null) {
                defaultRequest.addParameter("nextToken", StringUtils.fromString(listThingGroupsForThingRequest.getNextToken()));
            }
            if (listThingGroupsForThingRequest.getMaxResults() != null) {
                defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listThingGroupsForThingRequest.getMaxResults()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, replace, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListThingGroupsForThingRequest)");
    }
}
