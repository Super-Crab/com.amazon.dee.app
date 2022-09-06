package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.RegistrationConfig;
import com.amazonaws.services.iot.model.UpdateCACertificateRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.StringWriter;
/* loaded from: classes13.dex */
public class UpdateCACertificateRequestMarshaller implements Marshaller<Request<UpdateCACertificateRequest>, UpdateCACertificateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<UpdateCACertificateRequest> marshall(UpdateCACertificateRequest updateCACertificateRequest) {
        if (updateCACertificateRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(updateCACertificateRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.PUT);
            String replace = "/cacertificate/{caCertificateId}".replace("{caCertificateId}", updateCACertificateRequest.getCertificateId() == null ? "" : StringUtils.fromString(updateCACertificateRequest.getCertificateId()));
            if (updateCACertificateRequest.getNewStatus() != null) {
                defaultRequest.addParameter("newStatus", StringUtils.fromString(updateCACertificateRequest.getNewStatus()));
            }
            if (updateCACertificateRequest.getNewAutoRegistrationStatus() != null) {
                defaultRequest.addParameter("newAutoRegistrationStatus", StringUtils.fromString(updateCACertificateRequest.getNewAutoRegistrationStatus()));
            }
            defaultRequest.setResourcePath(replace);
            try {
                StringWriter stringWriter = new StringWriter();
                AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
                jsonWriter.beginObject();
                if (updateCACertificateRequest.getRegistrationConfig() != null) {
                    RegistrationConfig registrationConfig = updateCACertificateRequest.getRegistrationConfig();
                    jsonWriter.name("registrationConfig");
                    RegistrationConfigJsonMarshaller.getInstance().marshall(registrationConfig, jsonWriter);
                }
                if (updateCACertificateRequest.getRemoveAutoRegistration() != null) {
                    Boolean removeAutoRegistration = updateCACertificateRequest.getRemoveAutoRegistration();
                    jsonWriter.name("removeAutoRegistration");
                    jsonWriter.value(removeAutoRegistration.booleanValue());
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
        throw new AmazonClientException("Invalid argument passed to marshall(UpdateCACertificateRequest)");
    }
}
