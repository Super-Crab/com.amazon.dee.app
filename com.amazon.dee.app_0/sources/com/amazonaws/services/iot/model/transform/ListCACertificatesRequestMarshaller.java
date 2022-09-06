package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.mobilytics.configuration.Constants;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListCACertificatesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListCACertificatesRequestMarshaller implements Marshaller<Request<ListCACertificatesRequest>, ListCACertificatesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListCACertificatesRequest> marshall(ListCACertificatesRequest listCACertificatesRequest) {
        if (listCACertificatesRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listCACertificatesRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (listCACertificatesRequest.getPageSize() != null) {
                defaultRequest.addParameter("pageSize", StringUtils.fromInteger(listCACertificatesRequest.getPageSize()));
            }
            if (listCACertificatesRequest.getMarker() != null) {
                defaultRequest.addParameter(Constants.MARKER, StringUtils.fromString(listCACertificatesRequest.getMarker()));
            }
            if (listCACertificatesRequest.getAscendingOrder() != null) {
                defaultRequest.addParameter("isAscendingOrder", StringUtils.fromBoolean(listCACertificatesRequest.getAscendingOrder()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, "/cacertificates", "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListCACertificatesRequest)");
    }
}
