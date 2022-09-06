package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.RegisterCACertificateRequest;
import com.amazonaws.services.iot.model.RegistrationConfig;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
/* loaded from: classes13.dex */
public class RegisterCACertificateRequestMarshaller implements Marshaller<Request<RegisterCACertificateRequest>, RegisterCACertificateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<RegisterCACertificateRequest> marshall(RegisterCACertificateRequest registerCACertificateRequest) {
        if (registerCACertificateRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(registerCACertificateRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.POST);
            if (registerCACertificateRequest.getSetAsActive() != null) {
                defaultRequest.addParameter("setAsActive", StringUtils.fromBoolean(registerCACertificateRequest.getSetAsActive()));
            }
            if (registerCACertificateRequest.getAllowAutoRegistration() != null) {
                defaultRequest.addParameter("allowAutoRegistration", StringUtils.fromBoolean(registerCACertificateRequest.getAllowAutoRegistration()));
            }
            defaultRequest.setResourcePath("/cacertificate");
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (registerCACertificateRequest.getCaCertificate() != null) {
                    String caCertificate = registerCACertificateRequest.getCaCertificate();
                    jsonWriter.name("caCertificate");
                    jsonWriter.value(caCertificate);
                }
                if (registerCACertificateRequest.getVerificationCertificate() != null) {
                    String verificationCertificate = registerCACertificateRequest.getVerificationCertificate();
                    jsonWriter.name("verificationCertificate");
                    jsonWriter.value(verificationCertificate);
                }
                if (registerCACertificateRequest.getRegistrationConfig() != null) {
                    RegistrationConfig registrationConfig = registerCACertificateRequest.getRegistrationConfig();
                    jsonWriter.name("registrationConfig");
                    RegistrationConfigJsonMarshaller.getInstance().marshall(registrationConfig, jsonWriter);
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
        throw new AmazonClientException("Invalid argument passed to marshall(RegisterCACertificateRequest)");
    }
}
