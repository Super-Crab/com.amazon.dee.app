package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListThingsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListThingsRequestMarshaller implements Marshaller<Request<ListThingsRequest>, ListThingsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListThingsRequest> marshall(ListThingsRequest listThingsRequest) {
        if (listThingsRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listThingsRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (listThingsRequest.getNextToken() != null) {
                defaultRequest.addParameter("nextToken", StringUtils.fromString(listThingsRequest.getNextToken()));
            }
            if (listThingsRequest.getMaxResults() != null) {
                defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listThingsRequest.getMaxResults()));
            }
            if (listThingsRequest.getAttributeName() != null) {
                defaultRequest.addParameter("attributeName", StringUtils.fromString(listThingsRequest.getAttributeName()));
            }
            if (listThingsRequest.getAttributeValue() != null) {
                defaultRequest.addParameter("attributeValue", StringUtils.fromString(listThingsRequest.getAttributeValue()));
            }
            if (listThingsRequest.getThingTypeName() != null) {
                defaultRequest.addParameter("thingTypeName", StringUtils.fromString(listThingsRequest.getThingTypeName()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, "/things", "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListThingsRequest)");
    }
}
