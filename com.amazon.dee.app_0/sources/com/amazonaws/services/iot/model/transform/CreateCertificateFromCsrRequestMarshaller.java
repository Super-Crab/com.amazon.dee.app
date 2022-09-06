package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.CreateCertificateFromCsrRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
/* loaded from: classes13.dex */
public class CreateCertificateFromCsrRequestMarshaller implements Marshaller<Request<CreateCertificateFromCsrRequest>, CreateCertificateFromCsrRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<CreateCertificateFromCsrRequest> marshall(CreateCertificateFromCsrRequest createCertificateFromCsrRequest) {
        if (createCertificateFromCsrRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(createCertificateFromCsrRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            if (createCertificateFromCsrRequest.getSetAsActive() != null) {
                defaultRequest.addParameter("setAsActive", StringUtils.fromBoolean(createCertificateFromCsrRequest.getSetAsActive()));
            }
            defaultRequest.setResourcePath("/certificates");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (createCertificateFromCsrRequest.getCertificateSigningRequest() != null) {
                    String certificateSigningRequest = createCertificateFromCsrRequest.getCertificateSigningRequest();
                    jsonWriter.name("certificateSigningRequest");
                    jsonWriter.value(certificateSigningRequest);
                }
                jsonWriter.endObject();
                jsonWriter.close();
                String stringWriter2 = stringWriter.toString();
                byte[] bytes = stringWriter2.getBytes(StringUtils.UTF8);
                defaultRequest.setContent(new StringInputStream(stringWriter2));
                defaultRequest.addHeader("Content-Length", Integer.toString(bytes.length));
                if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                    defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
                }
                return defaultRequest;
            } catch (Throwable th) {
                throw new AmazonClientException(GeneratedOutlineSupport1.outline98(th, GeneratedOutlineSupport1.outline107("Unable to marshall request to JSON: ")), th);
            }
        }
        throw new AmazonClientException("Invalid argument passed to marshall(CreateCertificateFromCsrRequest)");
    }
}
