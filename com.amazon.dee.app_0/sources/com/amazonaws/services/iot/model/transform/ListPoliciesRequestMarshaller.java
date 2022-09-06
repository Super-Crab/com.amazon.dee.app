package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.mobilytics.configuration.Constants;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListPoliciesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListPoliciesRequestMarshaller implements Marshaller<Request<ListPoliciesRequest>, ListPoliciesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListPoliciesRequest> marshall(ListPoliciesRequest listPoliciesRequest) {
        if (listPoliciesRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listPoliciesRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (listPoliciesRequest.getMarker() != null) {
                defaultRequest.addParameter(Constants.MARKER, StringUtils.fromString(listPoliciesRequest.getMarker()));
            }
            if (listPoliciesRequest.getPageSize() != null) {
                defaultRequest.addParameter("pageSize", StringUtils.fromInteger(listPoliciesRequest.getPageSize()));
            }
            if (listPoliciesRequest.getAscendingOrder() != null) {
                defaultRequest.addParameter("isAscendingOrder", StringUtils.fromBoolean(listPoliciesRequest.getAscendingOrder()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, "/policies", "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListPoliciesRequest)");
    }
}
