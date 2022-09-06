package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListThingTypesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListThingTypesRequestMarshaller implements Marshaller<Request<ListThingTypesRequest>, ListThingTypesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListThingTypesRequest> marshall(ListThingTypesRequest listThingTypesRequest) {
        if (listThingTypesRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listThingTypesRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (listThingTypesRequest.getNextToken() != null) {
                defaultRequest.addParameter("nextToken", StringUtils.fromString(listThingTypesRequest.getNextToken()));
            }
            if (listThingTypesRequest.getMaxResults() != null) {
                defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listThingTypesRequest.getMaxResults()));
            }
            if (listThingTypesRequest.getThingTypeName() != null) {
                defaultRequest.addParameter("thingTypeName", StringUtils.fromString(listThingTypesRequest.getThingTypeName()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, "/thing-types", "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListThingTypesRequest)");
    }
}
