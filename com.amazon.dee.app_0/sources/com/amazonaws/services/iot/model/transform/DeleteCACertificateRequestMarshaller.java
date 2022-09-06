package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteCACertificateRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DeleteCACertificateRequestMarshaller implements Marshaller<Request<DeleteCACertificateRequest>, DeleteCACertificateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteCACertificateRequest> marshall(DeleteCACertificateRequest deleteCACertificateRequest) {
        if (deleteCACertificateRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(deleteCACertificateRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.DELETE);
            if (!GeneratedOutlineSupport1.outline190("/cacertificate/{caCertificateId}", "{caCertificateId}", deleteCACertificateRequest.getCertificateId() == null ? "" : StringUtils.fromString(deleteCACertificateRequest.getCertificateId()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DeleteCACertificateRequest)");
    }
}
