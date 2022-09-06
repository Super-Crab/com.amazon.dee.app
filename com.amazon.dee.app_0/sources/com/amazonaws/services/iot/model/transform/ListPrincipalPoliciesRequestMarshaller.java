package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.mobilytics.configuration.Constants;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListPrincipalPoliciesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListPrincipalPoliciesRequestMarshaller implements Marshaller<Request<ListPrincipalPoliciesRequest>, ListPrincipalPoliciesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListPrincipalPoliciesRequest> marshall(ListPrincipalPoliciesRequest listPrincipalPoliciesRequest) {
        if (listPrincipalPoliciesRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listPrincipalPoliciesRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (listPrincipalPoliciesRequest.getPrincipal() != null) {
                defaultRequest.addHeader("x-amzn-iot-principal", StringUtils.fromString(listPrincipalPoliciesRequest.getPrincipal()));
            }
            if (listPrincipalPoliciesRequest.getMarker() != null) {
                defaultRequest.addParameter(Constants.MARKER, StringUtils.fromString(listPrincipalPoliciesRequest.getMarker()));
            }
            if (listPrincipalPoliciesRequest.getPageSize() != null) {
                defaultRequest.addParameter("pageSize", StringUtils.fromInteger(listPrincipalPoliciesRequest.getPageSize()));
            }
            if (listPrincipalPoliciesRequest.getAscendingOrder() != null) {
                defaultRequest.addParameter("isAscendingOrder", StringUtils.fromBoolean(listPrincipalPoliciesRequest.getAscendingOrder()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, "/principal-policies", "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListPrincipalPoliciesRequest)");
    }
}
