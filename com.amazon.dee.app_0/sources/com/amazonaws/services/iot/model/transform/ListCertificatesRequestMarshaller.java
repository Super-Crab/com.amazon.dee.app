package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.mobilytics.configuration.Constants;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListCertificatesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListCertificatesRequestMarshaller implements Marshaller<Request<ListCertificatesRequest>, ListCertificatesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListCertificatesRequest> marshall(ListCertificatesRequest listCertificatesRequest) {
        if (listCertificatesRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listCertificatesRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (listCertificatesRequest.getPageSize() != null) {
                defaultRequest.addParameter("pageSize", StringUtils.fromInteger(listCertificatesRequest.getPageSize()));
            }
            if (listCertificatesRequest.getMarker() != null) {
                defaultRequest.addParameter(Constants.MARKER, StringUtils.fromString(listCertificatesRequest.getMarker()));
            }
            if (listCertificatesRequest.getAscendingOrder() != null) {
                defaultRequest.addParameter("isAscendingOrder", StringUtils.fromBoolean(listCertificatesRequest.getAscendingOrder()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, "/certificates", "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListCertificatesRequest)");
    }
}
