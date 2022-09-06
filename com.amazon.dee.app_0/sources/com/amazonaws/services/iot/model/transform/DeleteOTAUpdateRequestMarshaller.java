package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteOTAUpdateRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DeleteOTAUpdateRequestMarshaller implements Marshaller<Request<DeleteOTAUpdateRequest>, DeleteOTAUpdateRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteOTAUpdateRequest> marshall(DeleteOTAUpdateRequest deleteOTAUpdateRequest) {
        if (deleteOTAUpdateRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(deleteOTAUpdateRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.DELETE);
            String replace = "/otaUpdates/{otaUpdateId}".replace("{otaUpdateId}", deleteOTAUpdateRequest.getOtaUpdateId() == null ? "" : StringUtils.fromString(deleteOTAUpdateRequest.getOtaUpdateId()));
            if (deleteOTAUpdateRequest.getDeleteStream() != null) {
                defaultRequest.addParameter("deleteStream", StringUtils.fromBoolean(deleteOTAUpdateRequest.getDeleteStream()));
            }
            if (deleteOTAUpdateRequest.getForceDeleteAWSJob() != null) {
                defaultRequest.addParameter("forceDeleteAWSJob", StringUtils.fromBoolean(deleteOTAUpdateRequest.getForceDeleteAWSJob()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, replace, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DeleteOTAUpdateRequest)");
    }
}
