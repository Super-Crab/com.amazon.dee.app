package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteScheduledAuditRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DeleteScheduledAuditRequestMarshaller implements Marshaller<Request<DeleteScheduledAuditRequest>, DeleteScheduledAuditRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteScheduledAuditRequest> marshall(DeleteScheduledAuditRequest deleteScheduledAuditRequest) {
        if (deleteScheduledAuditRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(deleteScheduledAuditRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.DELETE);
            if (!GeneratedOutlineSupport1.outline190("/audit/scheduledaudits/{scheduledAuditName}", "{scheduledAuditName}", deleteScheduledAuditRequest.getScheduledAuditName() == null ? "" : StringUtils.fromString(deleteScheduledAuditRequest.getScheduledAuditName()), defaultRequest, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DeleteScheduledAuditRequest)");
    }
}
