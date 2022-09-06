package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CancelCertificateTransferRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class CancelCertificateTransferRequestMarshaller implements Marshaller<Request<CancelCertificateTransferRequest>, CancelCertificateTransferRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CancelCertificateTransferRequest> marshall(CancelCertificateTransferRequest cancelCertificateTransferRequest) {
        if (cancelCertificateTransferRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(cancelCertificateTransferRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.PATCH);
            if (!GeneratedOutlineSupport1.outline190("/cancel-certificate-transfer/{certificateId}", "{certificateId}", cancelCertificateTransferRequest.getCertificateId() == null ? "" : StringUtils.fromString(cancelCertificateTransferRequest.getCertificateId()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(CancelCertificateTransferRequest)");
    }
}
