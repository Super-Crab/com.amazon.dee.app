package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CreateKeysAndCertificateRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import java.io.ByteArrayInputStream;
/* loaded from: classes13.dex */
public class CreateKeysAndCertificateRequestMarshaller implements Marshaller<Request<CreateKeysAndCertificateRequest>, CreateKeysAndCertificateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateKeysAndCertificateRequest> marshall(CreateKeysAndCertificateRequest createKeysAndCertificateRequest) {
        if (createKeysAndCertificateRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(createKeysAndCertificateRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            if (createKeysAndCertificateRequest.getSetAsActive() != null) {
                defaultRequest.addParameter("setAsActive", StringUtils.fromBoolean(createKeysAndCertificateRequest.getSetAsActive()));
            }
            defaultRequest.setResourcePath("/keys-and-certificate");
            defaultRequest.addHeader("Content-Length", "0");
            defaultRequest.setContent(new ByteArrayInputStream(new byte[0]));
            if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(CreateKeysAndCertificateRequest)");
    }
}
