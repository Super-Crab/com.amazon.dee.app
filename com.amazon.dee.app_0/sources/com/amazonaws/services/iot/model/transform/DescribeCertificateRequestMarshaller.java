package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeCertificateRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DescribeCertificateRequestMarshaller implements Marshaller<Request<DescribeCertificateRequest>, DescribeCertificateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeCertificateRequest> marshall(DescribeCertificateRequest describeCertificateRequest) {
        if (describeCertificateRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(describeCertificateRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (!GeneratedOutlineSupport1.outline190("/certificates/{certificateId}", "{certificateId}", describeCertificateRequest.getCertificateId() == null ? "" : StringUtils.fromString(describeCertificateRequest.getCertificateId()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DescribeCertificateRequest)");
    }
}
