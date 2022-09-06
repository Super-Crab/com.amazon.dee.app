package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DescribeCACertificateRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DescribeCACertificateRequestMarshaller implements Marshaller<Request<DescribeCACertificateRequest>, DescribeCACertificateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DescribeCACertificateRequest> marshall(DescribeCACertificateRequest describeCACertificateRequest) {
        if (describeCACertificateRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(describeCACertificateRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.GET);
            if (!GeneratedOutlineSupport1.outline190("/cacertificate/{caCertificateId}", "{caCertificateId}", describeCACertificateRequest.getCertificateId() == null ? "" : StringUtils.fromString(describeCACertificateRequest.getCertificateId()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DescribeCACertificateRequest)");
    }
}
