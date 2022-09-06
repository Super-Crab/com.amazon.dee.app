package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.AcceptCertificateTransferRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class AcceptCertificateTransferRequestMarshaller implements Marshaller<Request<AcceptCertificateTransferRequest>, AcceptCertificateTransferRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<AcceptCertificateTransferRequest> marshall(AcceptCertificateTransferRequest acceptCertificateTransferRequest) {
        if (acceptCertificateTransferRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(acceptCertificateTransferRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.PATCH);
            String replace = "/accept-certificate-transfer/{certificateId}".replace("{certificateId}", acceptCertificateTransferRequest.getCertificateId() == null ? "" : StringUtils.fromString(acceptCertificateTransferRequest.getCertificateId()));
            if (acceptCertificateTransferRequest.getSetAsActive() != null) {
                defaultRequest.addParameter("setAsActive", StringUtils.fromBoolean(acceptCertificateTransferRequest.getSetAsActive()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, replace, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(AcceptCertificateTransferRequest)");
    }
}
