package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.mobilytics.configuration.Constants;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListAuthorizersRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListAuthorizersRequestMarshaller implements Marshaller<Request<ListAuthorizersRequest>, ListAuthorizersRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListAuthorizersRequest> marshall(ListAuthorizersRequest listAuthorizersRequest) {
        if (listAuthorizersRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listAuthorizersRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (listAuthorizersRequest.getPageSize() != null) {
                defaultRequest.addParameter("pageSize", StringUtils.fromInteger(listAuthorizersRequest.getPageSize()));
            }
            if (listAuthorizersRequest.getMarker() != null) {
                defaultRequest.addParameter(Constants.MARKER, StringUtils.fromString(listAuthorizersRequest.getMarker()));
            }
            if (listAuthorizersRequest.getAscendingOrder() != null) {
                defaultRequest.addParameter("isAscendingOrder", StringUtils.fromBoolean(listAuthorizersRequest.getAscendingOrder()));
            }
            if (listAuthorizersRequest.getStatus() != null) {
                defaultRequest.addParameter("status", StringUtils.fromString(listAuthorizersRequest.getStatus()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, "/authorizers/", "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListAuthorizersRequest)");
    }
}
