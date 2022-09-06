package com.amazonaws.services.iot.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.iot.model.DeleteJobExecutionRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class DeleteJobExecutionRequestMarshaller implements Marshaller<Request<DeleteJobExecutionRequest>, DeleteJobExecutionRequest> {
    @Override // com.amazonaws.transform.Marshaller
    public Request<DeleteJobExecutionRequest> marshall(DeleteJobExecutionRequest deleteJobExecutionRequest) {
        if (deleteJobExecutionRequest != null) {
            DefaultRequest defaultRequest = new DefaultRequest(deleteJobExecutionRequest, "AWSIot");
            defaultRequest.setHttpMethod(HttpMethodName.DELETE);
            String str = "";
            String replace = "/things/{thingName}/jobs/{jobId}/executionNumber/{executionNumber}".replace("{jobId}", deleteJobExecutionRequest.getJobId() == null ? str : StringUtils.fromString(deleteJobExecutionRequest.getJobId())).replace("{thingName}", deleteJobExecutionRequest.getThingName() == null ? str : StringUtils.fromString(deleteJobExecutionRequest.getThingName()));
            if (deleteJobExecutionRequest.getExecutionNumber() != null) {
                str = StringUtils.fromLong(deleteJobExecutionRequest.getExecutionNumber());
            }
            String replace2 = replace.replace("{executionNumber}", str);
            if (deleteJobExecutionRequest.getForce() != null) {
                defaultRequest.addParameter("force", StringUtils.fromBoolean(deleteJobExecutionRequest.getForce()));
            }
            if (!GeneratedOutlineSupport1.outline189(defaultRequest, replace2, "Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        }
        throw new AmazonClientException("Invalid argument passed to marshall(DeleteJobExecutionRequest)");
    }
}
