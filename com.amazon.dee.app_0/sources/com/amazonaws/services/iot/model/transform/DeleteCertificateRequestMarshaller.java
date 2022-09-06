package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteCertificateRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DeleteCertificateRequestMarshaller implements Marshaller<Request<DeleteCertificateRequest>, DeleteCertificateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteCertificateRequest> marshall(DeleteCertificateRequest deleteCertificateRequest) {
        if (deleteCertificateRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(deleteCertificateRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.DELETE);
            String replace = "/certificates/{certificateId}".replace("{certificateId}", deleteCertificateRequest.getCertificateId() == null ? "" : StringUtils.fromString(deleteCertificateRequest.getCertificateId()));
            if (deleteCertificateRequest.getForceDelete() != null) {
                defaultRequest.addParameter("forceDelete", StringUtils.fromBoolean(deleteCertificateRequest.getForceDelete()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, replace, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DeleteCertificateRequest)");
    }
}
