package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteJobRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DeleteJobRequestMarshaller implements Marshaller<Request<DeleteJobRequest>, DeleteJobRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteJobRequest> marshall(DeleteJobRequest deleteJobRequest) {
        if (deleteJobRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(deleteJobRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.DELETE);
            String replace = "/jobs/{jobId}".replace("{jobId}", deleteJobRequest.getJobId() == null ? "" : StringUtils.fromString(deleteJobRequest.getJobId()));
            if (deleteJobRequest.getForce() != null) {
                defaultRequest.addParameter("force", StringUtils.fromBoolean(deleteJobRequest.getForce()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, replace, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DeleteJobRequest)");
    }
}
