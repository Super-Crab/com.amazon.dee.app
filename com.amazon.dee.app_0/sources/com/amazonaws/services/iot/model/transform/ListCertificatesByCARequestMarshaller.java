package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.mobilytics.configuration.Constants;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListCertificatesByCARequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListCertificatesByCARequestMarshaller implements Marshaller<Request<ListCertificatesByCARequest>, ListCertificatesByCARequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListCertificatesByCARequest> marshall(ListCertificatesByCARequest listCertificatesByCARequest) {
        if (listCertificatesByCARequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listCertificatesByCARequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            String replace = "/certificates-by-ca/{caCertificateId}".replace("{caCertificateId}", listCertificatesByCARequest.getCaCertificateId() == null ? "" : StringUtils.fromString(listCertificatesByCARequest.getCaCertificateId()));
            if (listCertificatesByCARequest.getPageSize() != null) {
                defaultRequest.addParameter("pageSize", StringUtils.fromInteger(listCertificatesByCARequest.getPageSize()));
            }
            if (listCertificatesByCARequest.getMarker() != null) {
                defaultRequest.addParameter(Constants.MARKER, StringUtils.fromString(listCertificatesByCARequest.getMarker()));
            }
            if (listCertificatesByCARequest.getAscendingOrder() != null) {
                defaultRequest.addParameter("isAscendingOrder", StringUtils.fromBoolean(listCertificatesByCARequest.getAscendingOrder()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, replace, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListCertificatesByCARequest)");
    }
}
