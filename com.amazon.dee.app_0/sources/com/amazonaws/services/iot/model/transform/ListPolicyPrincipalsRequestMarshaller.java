package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.mobilytics.configuration.Constants;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListPolicyPrincipalsRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListPolicyPrincipalsRequestMarshaller implements Marshaller<Request<ListPolicyPrincipalsRequest>, ListPolicyPrincipalsRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListPolicyPrincipalsRequest> marshall(ListPolicyPrincipalsRequest listPolicyPrincipalsRequest) {
        if (listPolicyPrincipalsRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listPolicyPrincipalsRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (listPolicyPrincipalsRequest.getPolicyName() != null) {
                defaultRequest.addHeader("x-amzn-iot-policy", StringUtils.fromString(listPolicyPrincipalsRequest.getPolicyName()));
            }
            if (listPolicyPrincipalsRequest.getMarker() != null) {
                defaultRequest.addParameter(Constants.MARKER, StringUtils.fromString(listPolicyPrincipalsRequest.getMarker()));
            }
            if (listPolicyPrincipalsRequest.getPageSize() != null) {
                defaultRequest.addParameter("pageSize", StringUtils.fromInteger(listPolicyPrincipalsRequest.getPageSize()));
            }
            if (listPolicyPrincipalsRequest.getAscendingOrder() != null) {
                defaultRequest.addParameter("isAscendingOrder", StringUtils.fromBoolean(listPolicyPrincipalsRequest.getAscendingOrder()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, "/policy-principals", "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListPolicyPrincipalsRequest)");
    }
}
