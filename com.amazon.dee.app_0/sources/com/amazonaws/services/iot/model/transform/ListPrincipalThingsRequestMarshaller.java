package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListPrincipalThingsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListPrincipalThingsRequestMarshaller implements Marshaller<Request<ListPrincipalThingsRequest>, ListPrincipalThingsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListPrincipalThingsRequest> marshall(ListPrincipalThingsRequest listPrincipalThingsRequest) {
        if (listPrincipalThingsRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listPrincipalThingsRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (listPrincipalThingsRequest.getPrincipal() != null) {
                defaultRequest.addHeader("x-amzn-principal", StringUtils.fromString(listPrincipalThingsRequest.getPrincipal()));
            }
            if (listPrincipalThingsRequest.getNextToken() != null) {
                defaultRequest.addParameter("nextToken", StringUtils.fromString(listPrincipalThingsRequest.getNextToken()));
            }
            if (listPrincipalThingsRequest.getMaxResults() != null) {
                defaultRequest.addParameter("maxResults", StringUtils.fromInteger(listPrincipalThingsRequest.getMaxResults()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, "/principals/things", "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListPrincipalThingsRequest)");
    }
}
