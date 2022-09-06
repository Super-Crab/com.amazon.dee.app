package com.amazonaws.services.iot.model.transform;

import com.amazon.alexa.mobilytics.configuration.Constants;
import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.ListOutgoingCertificatesRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class ListOutgoingCertificatesRequestMarshaller implements Marshaller<Request<ListOutgoingCertificatesRequest>, ListOutgoingCertificatesRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<ListOutgoingCertificatesRequest> marshall(ListOutgoingCertificatesRequest listOutgoingCertificatesRequest) {
        if (listOutgoingCertificatesRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(listOutgoingCertificatesRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (listOutgoingCertificatesRequest.getPageSize() != null) {
                defaultRequest.addParameter("pageSize", StringUtils.fromInteger(listOutgoingCertificatesRequest.getPageSize()));
            }
            if (listOutgoingCertificatesRequest.getMarker() != null) {
                defaultRequest.addParameter(Constants.MARKER, StringUtils.fromString(listOutgoingCertificatesRequest.getMarker()));
            }
            if (listOutgoingCertificatesRequest.getAscendingOrder() != null) {
                defaultRequest.addParameter("isAscendingOrder", StringUtils.fromBoolean(listOutgoingCertificatesRequest.getAscendingOrder()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, "/certificates-out-going", "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(ListOutgoingCertificatesRequest)");
    }
}
