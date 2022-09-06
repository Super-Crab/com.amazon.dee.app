package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteAccountAuditConfigurationRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DeleteAccountAuditConfigurationRequestMarshaller implements Marshaller<Request<DeleteAccountAuditConfigurationRequest>, DeleteAccountAuditConfigurationRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteAccountAuditConfigurationRequest> marshall(DeleteAccountAuditConfigurationRequest deleteAccountAuditConfigurationRequest) {
        if (deleteAccountAuditConfigurationRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(deleteAccountAuditConfigurationRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.DELETE);
            if (deleteAccountAuditConfigurationRequest.getDeleteScheduledAudits() != null) {
                defaultRequest.addParameter("deleteScheduledAudits", StringUtils.fromBoolean(deleteAccountAuditConfigurationRequest.getDeleteScheduledAudits()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, "/audit/configuration", "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DeleteAccountAuditConfigurationRequest)");
    }
}
